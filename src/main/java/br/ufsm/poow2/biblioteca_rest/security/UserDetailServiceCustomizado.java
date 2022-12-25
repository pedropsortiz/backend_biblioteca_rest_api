package br.ufsm.poow2.biblioteca_rest.security;

import br.ufsm.poow2.biblioteca_rest.model.Usuario;
import br.ufsm.poow2.biblioteca_rest.repository.UsuarioRepo;
import br.ufsm.poow2.biblioteca_rest.service.UsuarioService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class UserDetailServiceCustomizado implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String emailUsuario) throws UsernameNotFoundException {
        Usuario usuario = new UsuarioService(new UsuarioRepo() {
            @Override
            public Usuario findByEmailUsuario(String emailUsuario) {
                return null;
            }

            @Override
            public List<Usuario> findAll() {
                return null;
            }

            @Override
            public List<Usuario> findAll(Sort sort) {
                return null;
            }

            @Override
            public List<Usuario> findAllById(Iterable<Integer> integers) {
                return null;
            }

            @Override
            public <S extends Usuario> List<S> saveAll(Iterable<S> entities) {
                return null;
            }

            @Override
            public void flush() {

            }

            @Override
            public <S extends Usuario> S saveAndFlush(S entity) {
                return null;
            }

            @Override
            public <S extends Usuario> List<S> saveAllAndFlush(Iterable<S> entities) {
                return null;
            }

            @Override
            public void deleteAllInBatch(Iterable<Usuario> entities) {

            }

            @Override
            public void deleteAllByIdInBatch(Iterable<Integer> integers) {

            }

            @Override
            public void deleteAllInBatch() {

            }

            @Override
            public Usuario getOne(Integer integer) {
                return null;
            }

            @Override
            public Usuario getById(Integer integer) {
                return null;
            }

            @Override
            public Usuario getReferenceById(Integer integer) {
                return null;
            }

            @Override
            public <S extends Usuario> List<S> findAll(Example<S> example) {
                return null;
            }

            @Override
            public <S extends Usuario> List<S> findAll(Example<S> example, Sort sort) {
                return null;
            }

            @Override
            public Page<Usuario> findAll(Pageable pageable) {
                return null;
            }

            @Override
            public <S extends Usuario> S save(S entity) {
                return null;
            }

            @Override
            public Optional<Usuario> findById(Integer integer) {
                return Optional.empty();
            }

            @Override
            public boolean existsById(Integer integer) {
                return false;
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public void deleteById(Integer integer) {

            }

            @Override
            public void delete(Usuario entity) {

            }

            @Override
            public void deleteAllById(Iterable<? extends Integer> integers) {

            }

            @Override
            public void deleteAll(Iterable<? extends Usuario> entities) {

            }

            @Override
            public void deleteAll() {

            }

            @Override
            public <S extends Usuario> Optional<S> findOne(Example<S> example) {
                return Optional.empty();
            }

            @Override
            public <S extends Usuario> Page<S> findAll(Example<S> example, Pageable pageable) {
                return null;
            }

            @Override
            public <S extends Usuario> long count(Example<S> example) {
                return 0;
            }

            @Override
            public <S extends Usuario> boolean exists(Example<S> example) {
                return false;
            }

            @Override
            public <S extends Usuario, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
                return null;
            }
        }).getUsuarioByEmail(emailUsuario);
        if (usuario == null){
            throw new UsernameNotFoundException("Usuário ou senha inválidos");
        }else{
            UserDetails user = User.withUsername(usuario.getEmailUsuario())
                    .password(usuario.getSenhaUsuario())
                    .authorities(usuario.getPermissaoUsuario()).build();
            return user;
        }
    }
}
