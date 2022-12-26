package br.ufsm.poow2.biblioteca_rest.service;

import br.ufsm.poow2.biblioteca_rest.DTO.EmprestimoDto;
import br.ufsm.poow2.biblioteca_rest.DTO.EmprestimoDto;
import br.ufsm.poow2.biblioteca_rest.model.Livro;
import br.ufsm.poow2.biblioteca_rest.model.Usuario;
import br.ufsm.poow2.biblioteca_rest.model.Emprestimo;
import br.ufsm.poow2.biblioteca_rest.repository.EmprestimoRepo;
import br.ufsm.poow2.biblioteca_rest.repository.GeneroRepo;
import br.ufsm.poow2.biblioteca_rest.repository.LivroRepo;
import br.ufsm.poow2.biblioteca_rest.repository.UsuarioRepo;
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
        emprestimo.setDataEmprestimo(emprestimoDto.getDataEmprestimo());
        emprestimo.setDataDevolucao(emprestimoDto.getDataDevolucao());
        emprestimoRepo.save(emprestimo);
    }

    public EmprestimoDto getEmprestimo(Emprestimo emprestimo){
        EmprestimoDto emprestimoDto = new EmprestimoDto();
        emprestimoDto.setIdEmprestimo(emprestimo.getIdEmprestimo());
        emprestimoDto.setIdLivro(emprestimo.getLivro().getIdLivro());
        emprestimoDto.setIdUsuario(emprestimo.getUsuario().getIdUsuario());
        emprestimoDto.setStatusEmprestimo(emprestimo.getStatusEmprestimo());
        emprestimoDto.setDataEmprestimo(emprestimo.getDataEmprestimo());
        emprestimoDto.setDataDevolucao(emprestimo.getDataDevolucao());
        return emprestimoDto;
    }

    public List<EmprestimoDto> getAllEmprestimos() {
        List<Emprestimo> emprestimoList = emprestimoRepo.findAll();

        List<EmprestimoDto> emprestimoDtoList = new ArrayList<>();
        for (Emprestimo emprestimo: emprestimoList
        ) {
            emprestimoDtoList.add(getEmprestimo(emprestimo));
        }
        return emprestimoDtoList;
    }

    public void editarEmprestimo(EmprestimoDto emprestimoDto, Integer idEmprestimo) throws Exception {
        Optional<Emprestimo> emprestimoOptional = emprestimoRepo.findById(idEmprestimo);
        if (!emprestimoOptional.isPresent()){
            throw new Exception("O empréstimo não existe!");
        }
        Emprestimo emprestimo = emprestimoOptional.get();

        emprestimo.setStatusEmprestimo(emprestimoDto.getStatusEmprestimo());
        emprestimo.setDataEmprestimo(emprestimoDto.getDataEmprestimo());
        emprestimo.setDataDevolucao(emprestimoDto.getDataDevolucao());
        emprestimoRepo.save(emprestimo);
    }

    public void deletarEmprestimo(Integer idEmprestimo) {
        emprestimoRepo.deleteById(idEmprestimo);
    }
}
