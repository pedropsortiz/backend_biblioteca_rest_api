package br.ufsm.poow2.biblioteca_rest.service;

import br.ufsm.poow2.biblioteca_rest.DTO.EmprestimoDto;
import br.ufsm.poow2.biblioteca_rest.model.Livro;
import br.ufsm.poow2.biblioteca_rest.model.Usuario;
import br.ufsm.poow2.biblioteca_rest.model.Emprestimo;
import br.ufsm.poow2.biblioteca_rest.repository.EmprestimoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmprestimoService {

    @Autowired
    EmprestimoRepo emprestimoRepo;

    public void criarEmprestimo(EmprestimoDto emprestimoDto, Livro livro, Usuario usuario) {
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setUsuario(usuario);
        emprestimo.setLivro(livro);
        emprestimo.setStatusEmprestimo(emprestimoDto.getStatusEmprestimo());
        emprestimo.setDataEmprestimoUsuarioLivro(emprestimoDto.getDataEmprestimoUsuarioLivro());
        emprestimo.setDataDevolucaoUsuarioLivro(emprestimoDto.getDataDevolucaoUsuarioLivro());
        emprestimoRepo.save(emprestimo);
    }

    public EmprestimoDto getEmprestimo(Emprestimo emprestimo){
        EmprestimoDto emprestimoDto = new EmprestimoDto();
        emprestimo.setUsuario(emprestimo.getUsuario());
        emprestimo.setLivro(emprestimo.getLivro());
        emprestimo.setStatusEmprestimo(emprestimoDto.getStatusEmprestimo());
        emprestimo.setDataEmprestimoUsuarioLivro(emprestimoDto.getDataEmprestimoUsuarioLivro());
        emprestimo.setDataDevolucaoUsuarioLivro(emprestimoDto.getDataDevolucaoUsuarioLivro());
        return emprestimoDto;
    }

    public List<EmprestimoDto> getEmprestimos() {
        List<Emprestimo> emprestimoList = emprestimoRepo.findAll();

        List<EmprestimoDto> emprestimoDtos = new ArrayList<>();
        for (Emprestimo emprestimo : emprestimoList
        ) {
            emprestimoDtos.add(getEmprestimo(emprestimo));
        }
        return emprestimoDtos;
    }

    public void editarEmprestimo(EmprestimoDto emprestimoDto, Integer idUsuario) throws Exception {
        Optional<Emprestimo> usuarioLivroOptional = emprestimoRepo.findById(idUsuario);
        if (!usuarioLivroOptional.isPresent()){
            throw new Exception("O empréstimo não existe!");
        }
        Emprestimo emprestimo = usuarioLivroOptional.get();

        emprestimo.setUsuario(emprestimo.getUsuario());
        emprestimo.setLivro(emprestimo.getLivro());
        emprestimo.setStatusEmprestimo(emprestimoDto.getStatusEmprestimo());
        emprestimo.setDataEmprestimoUsuarioLivro(emprestimoDto.getDataEmprestimoUsuarioLivro());
        emprestimo.setDataDevolucaoUsuarioLivro(emprestimoDto.getDataDevolucaoUsuarioLivro());
        emprestimoRepo.save(emprestimo);
    }
}
