package com.example.demo.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import com.example.demo.model.User;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	UserService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// http.authorizeRequests()
		// .antMatchers("/index").hasAnyRole("USER", "ADMIN")
		// .antMatchers("/produits").hasAnyRole("USER", "ADMIN")
		// .antMatchers("/dist/**","/fonts/**","/img/**","/inc/**","/css/**",
		// "/js/**", "/vendor/**", "/sass/**", "/style/**").permitAll()
		// .antMatchers("/marques").hasAnyRole("ADMIN")
		// .anyRequest().authenticated().and().formLogin()
		// .loginPage("/forget.html").permitAll()
		// .loginPage("/login.html").permitAll()
		// .and().logout().permitAll();

		http.csrf()
		.disable()
		.exceptionHandling()
		.authenticationEntryPoint(new DeniedAuthenticationEntryPoint())
		.and().authenticationProvider(getProvider())
		.formLogin()
		.loginProcessingUrl("/login")
		.successHandler(new AuthentificationLoginSuccessHandler())
		.failureHandler(new UrlAuthenticationFailureHandler())
		.and().logout().logoutUrl("/logout")
		.logoutSuccessHandler(new AuthentificationLogoutSuccessHandler())
		.invalidateHttpSession(true)
		.and()
		.authorizeRequests().antMatchers("/login").permitAll()
		.antMatchers("/logout").permitAll()
		.antMatchers("/index.html").authenticated()
		.antMatchers("/").hasAnyRole("USER", "ADMIN")
		//.antMatchers("/resto/all").access("hasRole('ADMIN')")
		//.antMatchers("/statistiques/**").access("hasRole('ADMIN')")
		//.antMatchers("/serie/**").authenticated()
		//.antMatchers("/specialite/**").authenticated()
		//.antMatchers("/photo/**").authenticated()
		//.antMatchers("/ville/**").authenticated()
		//.antMatchers("/zone/**").authenticated()
		//.antMatchers("/resto/**").authenticated()
		.antMatchers("/dist/**", "/fonts/**", "/img/**", "/inc/**",
						"/css/**", "/js/**", "/vendor/**", "/sass/**", "/style/**")
		.permitAll()
		.anyRequest().permitAll();
		
		

	}
	
   
	public class DeniedAuthenticationEntryPoint extends Http403ForbiddenEntryPoint{
		@Override
		public void commence(HttpServletRequest request, HttpServletResponse response,
				AuthenticationException authException) throws IOException, ServletException {
			// TODO Auto-generated method stub
			response.sendRedirect("/login.html");
		}
		
	}

	private class UrlAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
		@Override
		public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
				AuthenticationException exception) throws IOException, ServletException {
			// TODO Auto-generated method stub
		    super.onAuthenticationFailure(request, response, exception);
			response.sendRedirect("/login.html");
		}
	}

	private class AuthentificationLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
		@Override
		public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
				Authentication authentication) throws IOException, ServletException {
			response.setStatus(HttpServletResponse.SC_OK);
			UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;
			String username = auth.getName();
			User u = userDetailsService.loadUserByUsername(username);
			
			if(u.getRole().toString()=="ROLE_ADMIN")
				
				response.sendRedirect("/index.html");
			else
				response.sendRedirect("/index1.html");
				
			//String email = request.getParameter("username");
			//if(response.getOutputStream().equals(userDetailsService.loadUserByUsername(email).))
			
			//response.sendRedirect("/index.html");
		}
	}
	


	private class AuthentificationLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {
		@Override
		public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
				Authentication authentication) throws IOException, ServletException {
			response.setStatus(HttpServletResponse.SC_OK);
			response.sendRedirect("/login.html");
		}
	}

	@Bean
	public AuthenticationProvider getProvider() {
		AppAuthProvider provider = new AppAuthProvider();
		provider.setUserDetailsService(userDetailsService);
		return provider;
	}
}