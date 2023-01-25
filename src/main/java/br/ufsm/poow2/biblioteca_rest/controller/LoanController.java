package br.ufsm.poow2.biblioteca_rest.controller;

import br.ufsm.poow2.biblioteca_rest.DTO.LoanDto;
import br.ufsm.poow2.biblioteca_rest.common.ApiResponse;
import br.ufsm.poow2.biblioteca_rest.model.Book;
import br.ufsm.poow2.biblioteca_rest.model.Loan;
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
@RequestMapping("/loan")
@CrossOrigin
public class LoanController {

    @Autowired
    LoanService loanService;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addLoan(@RequestBody LoanDto loanDto){
        Optional<Book> optionalBook = bookRepository.findById(loanDto.getBookId());
        Optional<User> usuarioOptional = userRepository.findById(loanDto.getUserId());
        return loanService.addLoan(loanDto, optionalBook.get(), usuarioOptional.get());
    }

    @GetMapping("/list")
    public ResponseEntity<List<Loan>>  listLoans(){
        List<Loan> allEmprestimos = loanService.findAllLoans();
        return new ResponseEntity<>(allEmprestimos, HttpStatus.OK);
    }

    @PostMapping("/listOne/{id}")
    public ResponseEntity<ApiResponse> extendLoan(@PathVariable("id") Integer id) throws Exception {
        return loanService.extendLoan(id);
    }

    @PostMapping("/return")
    public ResponseEntity<ApiResponse> returnBookLoan(@RequestBody LoanDto loanDto) throws Exception {
        return loanService.returnBook(loanDto);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteLoan(@PathVariable("id") Integer id) throws Exception {
        loanService.deleteLoanById(id);
        return new ResponseEntity<>(new ApiResponse(true, "Empréstimo deletado com sucesso!"), HttpStatus.OK);
    }

}
