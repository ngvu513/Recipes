package recipes.businesslayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfigurerImpl extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceIml userDetailsServiceIml;

    // Acquiring the builder
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // storing users in memory
        auth
                .userDetailsService(userDetailsServiceIml) // user store 1
                .passwordEncoder(getEncoder());
        auth.inMemoryAuthentication()
                .withUser("user1")
                .password("pass1")
                .roles();
    }

    // creating a PasswordEncoder that is needed in two places
    // Between the http and the .csrf(), put .httpBasic().and()

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//                .mvcMatchers("/admin").hasRole("ADMIN")
//                .mvcMatchers("/user").hasAnyRole("ADMIN", "USER")
                .mvcMatchers( "/api/register").permitAll()
                .mvcMatchers(HttpMethod.POST, "/actuator/shutdown").permitAll()
                .mvcMatchers("/**").authenticated() // or .anyRequest().authenticated()
                .and().httpBasic().and()
                .csrf().disable() // disabling CSRF will allow sending POST request using Postman
                .headers().frameOptions().disable();
    }

    @Bean
    public PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }

}