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
    public ResponseEntity<ApiResponse> addLoan(@RequestBody LoanDto loanDto){
        Optional<Book> optionalBook = bookRepository.findById(loanDto.getBookId());
        Optional<User> usuarioOptional = userRepository.findById(loanDto.getUserId());
        return loanService.addLoan(loanDto, optionalBook.get(), usuarioOptional.get());
    }

    @GetMapping("/")
    public ResponseEntity<List<LoanDto>> listLoans(){
        List<LoanDto> allEmprestimos = loanService.findAllLoans();
        return new ResponseEntity<>(allEmprestimos, HttpStatus.OK);
    }

    @PostMapping("/extender/{idEmprestimo}")
    public ResponseEntity<ApiResponse> extendLoan(@PathVariable("idEmprestimo") Integer idEmprestimo) throws Exception {
        return loanService.extendLoan(idEmprestimo);
    }

    @PostMapping("/return")
    public ResponseEntity<ApiResponse> returnBookLoan(@RequestBody LoanDto loanDto) throws Exception {
        return loanService.returnBook(loanDto);
    }

    @PostMapping("/deletar/{idEmprestimo}")
    public ResponseEntity<ApiResponse> deleteLoan(@PathVariable("idEmprestimo") Integer idEmprestimo) throws Exception {
        loanService.deleteLoanById(idEmprestimo);
        return new ResponseEntity<>(new ApiResponse(true, "Empr??stimo deletado com sucesso!"), HttpStatus.OK);
    }

}
