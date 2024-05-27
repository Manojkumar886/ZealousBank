package account_transaction.ZealousBank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class Authentication {

    @Autowired
    accountservice service;

    @Bean // collection of object
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer customeurl() {
        return web -> web.ignoring().requestMatchers("/zealousbank/accountcreate");
    }

    @Bean
    public InMemoryUserDetailsManager myuserdetails() {

        UserDetails user1 = User.withUsername("Manojkumar")
                .password(encoder().encode("Maddy123"))
                .roles("manager").build();
        UserDetails user2 = User.withUsername("Praveen")
                .password(encoder().encode("Praveen123"))
                .roles("Teamlead")
                .build();

        return new InMemoryUserDetailsManager(user1, user2);
    }

    @Bean
    @Deprecated(forRemoval = true)
    public SecurityFilterChain httpfilter(HttpSecurity hp) throws Exception {

        // hp.authorizeHttpRequests().anyRequest().authenticated();
        // hp.authorizeHttpRequests().anyRequest().permitAll();

        // hp.authorizeHttpRequests()
        // .requestMatchers("/zealousbank/**")
        // .authenticated();
        // hp.csrf().disable();// Disables Cross-Site Request Forgery protection.
        // hp.cors();// Enables Cross-Origin Resource Sharing.
        // hp.httpBasic();
        // hp.formLogin();

        hp.authorizeHttpRequests()
                .requestMatchers("/zealousbank/**")
                .authenticated()
                .and()
                .csrf().disable() // Disables Cross-Site Request Forgery protection
                .cors() // Enables Cross-Origin Resource Sharing
                .and()
                .httpBasic() // Enables HTTP Basic Authentication
                .and()
                .formLogin();

        AuthenticationManagerBuilder builder = hp.getSharedObject(AuthenticationManagerBuilder.class);

        builder.userDetailsService(service).passwordEncoder(encoder());

        hp.authenticationManager(builder.build());

        return hp.build();

    }
}
