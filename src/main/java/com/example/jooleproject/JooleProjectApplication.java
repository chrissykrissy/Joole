package com.example.jooleproject;

import com.example.jooleproject.Filters.JwtRequestFilter;
import com.example.jooleproject.Service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableJpaAuditing
@SpringBootApplication
public class JooleProjectApplication {
    public static void main(String[] args) {

        SpringApplication.run(JooleProjectApplication.class, args);
    }
}

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MyUserDetailsService myUserDetailsService;
    @Autowired
    private JwtRequestFilter jwtRequestFilter;


    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder (){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    //Authentication
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Configure database-based user password query.
        // Password uses BCryptEncoder (combined with random salt and encryption algorithm) that comes with security.
        //Override the UserdatailsService class
        auth.userDetailsService(myUserDetailsService)
                //Override the default password verification class
                .passwordEncoder(passwordEncoder());
    }


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {


        httpSecurity.csrf().disable()
                //Cross-origin-resource-sharing
                .cors().and()
                .authorizeRequests()
                .antMatchers("/UserController/authenticate").permitAll() //login
                .antMatchers("/UserController/users").permitAll()
                .antMatchers("/UserController/create").permitAll() //sigin
                .antMatchers("/UserController/admin/**").hasRole("ADMIN")
                .anyRequest().fullyAuthenticated();// others need to be accessed after authentication


        httpSecurity
                .exceptionHandling().and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

    }


    @Bean
    public WebMvcConfigurer corsConfigure() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {

                registry.addMapping("/**").allowedOrigins("*").allowedMethods("*");
            }
        };
    }
}
