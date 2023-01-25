package br.ufsm.poow2.biblioteca_rest.exception;

import br.ufsm.poow2.biblioteca_rest.DTO.BookDto;
import br.ufsm.poow2.biblioteca_rest.common.ApiResponse;
import br.ufsm.poow2.biblioteca_rest.model.Author;
import br.ufsm.poow2.biblioteca_rest.model.Book;
import br.ufsm.poow2.biblioteca_rest.model.Loan;
import br.ufsm.poow2.biblioteca_rest.model.User;
import br.ufsm.poow2.biblioteca_rest.repository.BookRepository;
import br.ufsm.poow2.biblioteca_rest.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Map;
import java.util.Optional;

@ControllerAdvice
public class BookException {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorService authorService;

    public Map<String, String> handleAddBookErrors(BookDto bookDto, Author author) {
        ApiResponse apiResponse = new ApiResponse();

        if (!(doesBookAlredyExists(bookDto, author)))
        {
            //apiResponse.addError("title", "O livro registrado já existe no banco de dados. Altere a edição ou a data de publicação");
        }
        if (!(isNameValid(bookDto.getTitle())))
        {
            apiResponse.addError("title", "O título do livro é inválido ou está vazio. O tamanho mínimo é de 3 caracteres.");
        }
        if (!(isDescriptionValid(bookDto.getDescription())))
        {
            apiResponse.addError("description", "A descrição do livro é inválida ou tem menos de 10 caracteres.");
        }
        if (authorService.getAuthorById(bookDto.getAuthorId()) == null)
        {
            apiResponse.addError("author", "O autor do livro é inválido ou não foi preenchido.");
        }
        if (!(isTotalQuantityValid(bookDto.getTotalQuantity())))
        {
            apiResponse.addError("total_quantity", "A quantidade total do livro é inválida ou é menor que zero.");
        }
        if (!(isInUseQuantityValid(bookDto.getInUseQuantity(), bookDto.getTotalQuantity())))
        {
            apiResponse.addError("total_quantity", "A quantidade em uso do livro é inválida ou é menor que zero ou maior que a quantidade total.");
        }

        return apiResponse.getErrors();
    }

    public Map<String, String> handleUpdateBookErrors(BookDto dto, Integer id) {
        ApiResponse apiResponse = new ApiResponse();
        Book book = bookRepository.findById(id).orElse(null);
        Optional<Author> author = authorService.getOneAuthor(dto.getAuthorId());

        if (!(doesBookAlredyExists(dto, author.get())))
        {
            apiResponse.addError("title", "O livro registrado já existe no banco de dados. Altere a edição ou a data de publicação");
        }
        if (book == null)
        {
            apiResponse.addError("title", "O livro selecionado não existe ou não foi encontrado");
        }
        if (!(isNameValid(dto.getTitle())))
        {
            apiResponse.addError("title", "O título do livro é inválido ou está vazio. O tamanho mínimo é de 3 caracteres.");
        }
        if (!(isDescriptionValid(dto.getDescription())))
        {
            apiResponse.addError("description", "A descrição do livro é inválida ou tem menos de 10 caracteres.");
        }
        if (!(author.isPresent()))
        {
            apiResponse.addError("author", "O autor do livro é inválido ou não foi preenchido.");
        }
        if (!(isTotalQuantityValid(dto.getTotalQuantity())))
        {
            apiResponse.addError("totalQuantity", "A quantidade total do livro é inválida ou é menor que zero.");
        }
        if (!(isInUseQuantityValid(dto.getInUseQuantity(), dto.getTotalQuantity())))
        {
            apiResponse.addError("inUseQuantity", "A quantidade em uso do livro é inválida ou é menor que zero ou maior que a quantidade total.");
        }

        return apiResponse.getErrors();
    }

    public Map<String, String> handleDeleteBookErrors(Integer id) {
        ApiResponse apiResponse = new ApiResponse();
        Book bookOptional = bookRepository.findById(id).orElse(null);

        boolean doesBookExists = bookOptional != null;

        if (!(doesBookExists))
        {
            apiResponse.addError("idBook", "O livro não existe ou não foi encontrado!");
        }

        return apiResponse.getErrors();
    }

    public Map<String, String> handleGetBookErrors(Integer id) {
        ApiResponse apiResponse = new ApiResponse();
        Book book = bookRepository.findById(id).orElse(null);

        boolean doesBookExists = (book == null) ? false : true;

        if (!(doesBookExists))
        {
            apiResponse.addError("idBook", "Livro não encontrado");
        }

        return apiResponse.getErrors();
    }

        /*
    Testes de validação de campos
     */

    public boolean isNameValid(String name) {
        return name != null && !name.trim().isEmpty() && name.trim().length() >= 3;
    }

    public boolean isDescriptionValid(String description) {
        return description == null || !description.trim().isEmpty() && description.trim().length() >= 10;
    }

    public boolean isTotalQuantityValid(int totalQuantity) {
        return totalQuantity >= 0;
    }

    public boolean isInUseQuantityValid(int inUseQuantity, int totalQuantity) {
        return inUseQuantity >= 0 && inUseQuantity <= totalQuantity;
    }

    public boolean doesBookAlredyExists(BookDto bookDto, Author author){
        return bookRepository.findBookByTitleAndAuthorAndEditionAndPublicationDate(bookDto.getTitle(), author, bookDto.getEdition(), bookDto.getPublicationDate()) != null;
    }

}
