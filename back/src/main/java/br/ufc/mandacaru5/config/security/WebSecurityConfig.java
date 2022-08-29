package br.ufc.mandacaru5.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//@Component
//@EnableWebSecurity	
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http
//		.httpBasic()
//		.and()
//		.authorizeHttpRequests()
//		.antMatchers(HttpMethod.GET,"/api/user","/api/user/**").permitAll()
//		.anyRequest().authenticated()
//		.and()
//		.csrf().disable();
//	}
	@Override
	public void configure(WebSecurity http) throws Exception {
		http
		.ignoring()
		.antMatchers(HttpMethod.POST, "/api/user");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}