package br.ufsm.poow2.biblioteca_rest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

//    public DaoAuthenticationProvider authProvider(){
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(this.userDetailsService);
//        authProvider.setPasswordEncoder(new BCryptPasswordEncoder());
//        return authProvider;
//    }

    @Autowired
    public void configureAutenticacao(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(this.userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception{
        return super.authenticationManager();
    }

    @Bean
    public FiltroAutenticacao filtroAutenticacao() throws Exception {
        return new FiltroAutenticacao();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // Desativa a proteção contra ataques CSRF
                .csrf().disable()
                // Configura a criação de sessão como estadoless
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                // Configura as autorizações para as requisições HTTP
                .authorizeRequests()
                // Permite acesso a todas as requisições GET na raiz do site
                .antMatchers(HttpMethod.GET, "/").permitAll()
                // Permite acesso a todas as requisições POST no caminho "/login"
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                // Exige autoridade "adm" para as requisições POST nos caminhos "/genero" e "/genero/criar"
                .antMatchers(HttpMethod.POST, "/genero").hasAuthority("adm")
                .antMatchers(HttpMethod.POST, "/genero/criar").hasAuthority("adm");
        // Adiciona o filtro de autenticação antes do filtro de autenticação de senha do usuário
        http.addFilterBefore(this.filtroAutenticacao(), UsernamePasswordAuthenticationFilter.class);
    }

}
