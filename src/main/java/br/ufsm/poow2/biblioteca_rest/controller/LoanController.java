package br.ufsm.poow2.biblioteca_rest.controller;

import br.ufsm.poow2.biblioteca_rest.DTO.LoanDto;
import br.ufsm.poow2.biblioteca_rest.common.ApiResponse;
import br.ufsm.poow2.biblioteca_rest.model.Book;
import br.ufsm.poow2.biblioteca_rest.model.User;
import br.ufsm.poow2.biblioteca_rest.repository.BookRepository;
import br.ufsm.poow2.biblioteca_rest.repository.UserRepository;
import br.ufsm.poow2.biblioteca_rest.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/emprestimo")
public class LoanController {

    @Autowired
    LoanService loanService;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/criar")
    public ResponseEntity<ApiResponse> criarEmprestimo(@RequestBody LoanDto loanDto){
        Optional<Book> optionalBook = bookRepository.findById(loanDto.getBookId());
        Optional<User> usuarioOptional = userRepository.findById(loanDto.getUserId());
        if (!optionalBook.isPresent() || !usuarioOptional.isPresent()){
            return new ResponseEntity<>(new ApiResponse(false, "O empréstimo selecionado não existe!"), HttpStatus.BAD_REQUEST);
        }
        loanService.addLoan(loanDto, optionalBook.get(), usuarioOptional.get());
        return new ResponseEntity<>(new ApiResponse(true, "Novo empréstimo criado com sucesso!"), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<LoanDto>> listarEmprestimos(){
        List<LoanDto> allEmprestimos = loanService.findAllLoans();
        return new ResponseEntity<>(allEmprestimos, HttpStatus.OK);
    }

    @PostMapping("/editar/{idEmprestimo}")
    public ResponseEntity<ApiResponse> editarEmprestimo(@PathVariable("idEmprestimo") Integer idEmprestimo, @RequestBody LoanDto loanDto) throws Exception {
        Optional<Book> optionalBook = bookRepository.findById(loanDto.getBookId());
        Optional<User> usuarioOptional = userRepository.findById(loanDto.getUserId());

        if (!optionalBook.isPresent() || !usuarioOptional.isPresent()){
            return new ResponseEntity<>(new ApiResponse(false, "O empréstimo selecionado não existe!"), HttpStatus.BAD_REQUEST);
        }
        loanService.updateLoan(loanDto, idEmprestimo);
        return new ResponseEntity<>(new ApiResponse(true, "Empréstimo editado com sucesso!"), HttpStatus.CREATED);
    }

    @PostMapping("/deletar/{idEmprestimo}")
    public ResponseEntity<ApiResponse> deletarBook(@PathVariable("idEmprestimo") Integer idEmprestimo) throws Exception {
        loanService.deleteLoanById(idEmprestimo);
        return new ResponseEntity<>(new ApiResponse(true, "Empréstimo deletado com sucesso!"), HttpStatus.OK);
    }

}
