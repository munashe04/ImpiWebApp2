/*package com.thondo.whatsappconnect.security;

import com.thondo.whatsappconnect.service.external.AdminServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    AdminServiceImpl adminService;

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/")
                .permitAll()
                .anyRequest()
               .authenticated().and()
                .formLogin();
        // .loginPage("/login")
        //  .failureForwardUrl("/login?error=true")
        // .defaultSuccessUrl("/home")
        // .usernameParameter("email")
        // .passwordParameter("password");
        // .and().logout()
        // .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        // .logoutSuccessUrl("/").permitAll();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(adminService);
        return provider;
    }
}


 */