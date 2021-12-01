package com.springboot.administradores.app;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.springboot.administradores.app.auth.handler.LoginSuccessHandler;
import com.springboot.administradores.app.service.JpaUserDetailsService;



@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {	
	
	@Autowired
	private LoginSuccessHandler loginSuccesHandler;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;	
	
	
	@Autowired
	@Qualifier("jpaUserDetailsService")
	private JpaUserDetailsService jpaUserDetailsService;

	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception{
		
		build.userDetailsService(jpaUserDetailsService)		
		.passwordEncoder(passwordEncoder);			
		
	}	
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests().antMatchers("/css/**", "/js/**", "/images/**").permitAll()
		.antMatchers("/administrador/**").hasAnyRole("OWNER")
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.successHandler(loginSuccesHandler)
		.loginPage("/login").permitAll()		
		.and()
		.logout().permitAll()
		.and()
		.exceptionHandling().accessDeniedPage("/error_403");
	}
	
	
}
