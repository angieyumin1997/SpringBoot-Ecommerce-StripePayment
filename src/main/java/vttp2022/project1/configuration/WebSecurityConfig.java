package vttp2022.project1.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import vttp2022.project1.service.ShopService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	ShopService shopSvc;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	   BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	   return bCryptPasswordEncoder;
	}
	
	@Override
   public void configure(AuthenticationManagerBuilder auth) throws Exception {

      // Setting Service to find User in the database.
      // And Setting PassswordEncoder
      auth.userDetailsService(shopSvc).passwordEncoder(passwordEncoder());

   }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
        http
            .authorizeRequests().antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')");
		
		http
            .authorizeRequests().antMatchers("/cart").access("hasAnyRole('ROLE_ADMIN','ROLE_USER')");


		http.csrf().disable();

        http
			.authorizeRequests()
				.antMatchers("/", "/shop/**","/register/**").permitAll()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
			.logout()
				.permitAll();
	}

	@Override
    public void configure(WebSecurity web) throws Exception {
        web
           .ignoring()
           .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }

}