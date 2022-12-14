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
                // Desativa a prote????o contra ataques CSRF
                .csrf().disable()
                // Configura a cria????o de sess??o como estadoless
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                // Configura as autoriza????es para as requisi????es HTTP
                .authorizeRequests()
                // Permite acesso a todas as requisi????es GET na raiz do site
                .antMatchers(HttpMethod.GET, "/").permitAll()
                // Permite acesso a todas as requisi????es POST no caminho "/login"
                .antMatchers(HttpMethod.POST, "/login").permitAll();
                // Exige autoridade "adm" para as requisi????es POST nos caminhos "/genero" e "/genero/criar"
//                .antMatchers(HttpMethod.POST, "/genero").hasAuthority("adm")
//                .antMatchers(HttpMethod.POST, "/genero/criar").hasAuthority("adm");
        // Adiciona o filtro de autentica????o antes do filtro de autentica????o de senha do usu??rio
        http.addFilterBefore(this.filtroAutenticacao(), UsernamePasswordAuthenticationFilter.class);
    }

}
