package br.ufsm.poow2.biblioteca_rest.service;

import br.ufsm.poow2.biblioteca_rest.common.ApiResponse;
import br.ufsm.poow2.biblioteca_rest.model.Autor;
import br.ufsm.poow2.biblioteca_rest.repository.AutorRepo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AutorService {

    @Autowired
    AutorRepo autorRepo;

    public ResponseEntity<ApiResponse> criarAutor(Autor autor){
        if (autor.getDataMorteAutor()!=null) {
            if (!(checaDataMorte(autor.getDataMorteAutor(), autor.getDataNascAutor()))) {
                return new ResponseEntity<>(new br.ufsm.poow2.biblioteca_rest.common.ApiResponse(true, "Falha ao criar o autor. A data de morte ocorre depois da data de nascimento."), HttpStatus.CREATED);
            }
        }
        autorRepo.save(autor);
        return new ResponseEntity<>(new br.ufsm.poow2.biblioteca_rest.common.ApiResponse(true, "Novo autor criado com sucesso!"), HttpStatus.CREATED);
    }

    public List<Autor> listarAutor(){
        return autorRepo.findAll();
    }

    public ResponseEntity<ApiResponse> editarAutor(Integer idAutor, Autor editarAutor) {
        Optional<Autor> autorOptional = autorRepo.findById(idAutor);
        if (autorOptional.isPresent()) {
            if (editarAutor.getDataMorteAutor()!=null) {
                if (!(checaDataMorte(editarAutor.getDataMorteAutor(), editarAutor.getDataNascAutor()))) {
                    return new ResponseEntity<>(new br.ufsm.poow2.biblioteca_rest.common.ApiResponse(true, "Falha ao criar o autor. A data de morte ocorre depois da data de nascimento."), HttpStatus.CREATED);
                }
            }
            Autor autor = autorOptional.get();
            autor.update(editarAutor);
            autorRepo.save(autor);
            return new ResponseEntity<>(new ApiResponse(true, "O autor foi editado com sucesso!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse(false, "O autor não existe ou não foi encontrado!"), HttpStatus.NOT_FOUND);
    }

    public boolean findById(Integer idAutor) {
        return autorRepo.findById(idAutor).isPresent();
    }

    public static boolean checaDataMorte(Date dataMorte, Date dataNasc) {
        return !dataMorte.before(dataNasc);
    }

    public ResponseEntity<ApiResponse> deletarAutor(Integer idAutor) {
        Optional<Autor> autor = autorRepo.findById(idAutor);
        if (!autor.isPresent()) {
            return new ResponseEntity<>(new ApiResponse(false, "O autor não existe ou não foi encontrado!"), HttpStatus.NOT_FOUND);
        }
        autorRepo.deleteById(idAutor);
        return new ResponseEntity<>(new ApiResponse(true, "O autor foi excluido com sucesso!"), HttpStatus.OK);
    }

}
