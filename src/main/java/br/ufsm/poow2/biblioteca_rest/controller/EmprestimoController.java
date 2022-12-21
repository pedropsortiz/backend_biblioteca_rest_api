package br.ufsm.poow2.biblioteca_rest.controller;

import br.ufsm.poow2.biblioteca_rest.DTO.EmprestimoDto;
import br.ufsm.poow2.biblioteca_rest.DTO.LivroDto;
import br.ufsm.poow2.biblioteca_rest.common.ApiResponse;
import br.ufsm.poow2.biblioteca_rest.model.Autor;
import br.ufsm.poow2.biblioteca_rest.model.Emprestimo;
import br.ufsm.poow2.biblioteca_rest.model.Livro;
import br.ufsm.poow2.biblioteca_rest.model.Usuario;
import br.ufsm.poow2.biblioteca_rest.repository.AutorRepo;
import br.ufsm.poow2.biblioteca_rest.repository.LivroRepo;
import br.ufsm.poow2.biblioteca_rest.repository.UsuarioRepo;
import br.ufsm.poow2.biblioteca_rest.service.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/emprestimo")
public class EmprestimoController {

    @Autowired
    EmprestimoService emprestimoService;

    @Autowired
    LivroRepo livroRepo;

    @Autowired
    UsuarioRepo usuarioRepo;

    @PostMapping("/criar")
    public ResponseEntity<ApiResponse> criarEmprestimo(@RequestBody EmprestimoDto emprestimoDto){
        Optional<Livro> optionalLivro = livroRepo.findById(emprestimoDto.getIdLivro());
        Optional<Usuario> usuarioOptional = usuarioRepo.findById(emprestimoDto.getIdUsuario());

        if (!optionalLivro.isPresent() || !usuarioOptional.isPresent()){
            return new ResponseEntity<>(new ApiResponse(false, "O empréstimo selecionado não existe!"), HttpStatus.BAD_REQUEST);
        }
        emprestimoService.criarEmprestimo(emprestimoDto, optionalLivro.get(), usuarioOptional.get());
        return new ResponseEntity<>(new ApiResponse(true, "Novo empréstimo criado com sucesso!"), HttpStatus.CREATED);

    }

    @GetMapping("/")
    public ResponseEntity<List<EmprestimoDto>> listarEmprestimos(){
        List<EmprestimoDto> emprestimoDtoList = emprestimoService.getEmprestimos();
        return new ResponseEntity<>(emprestimoDtoList, HttpStatus.OK);
    }

    @PostMapping("/editar/{idEmprestimo}")
    public ResponseEntity<ApiResponse> editarEmprestimo(@PathVariable("idEmprestimo") Integer idEmprestimo, @RequestBody EmprestimoDto emprestimoDto) throws Exception {
        Optional<Livro> optionalLivro = livroRepo.findById(emprestimoDto.getIdLivro());
        Optional<Usuario> usuarioOptional = usuarioRepo.findById(emprestimoDto.getIdUsuario());

        if (!optionalLivro.isPresent() || !usuarioOptional.isPresent()){
            return new ResponseEntity<>(new ApiResponse(false, "O empréstimo selecionado não existe!"), HttpStatus.BAD_REQUEST);
        }
        emprestimoService.editarEmprestimo(emprestimoDto, idEmprestimo);
        return new ResponseEntity<>(new ApiResponse(true, "Empréstimo editado com sucesso!"), HttpStatus.CREATED);
    }

}
