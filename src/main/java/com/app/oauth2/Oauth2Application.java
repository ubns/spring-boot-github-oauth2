package com.app.oauth2;

import com.app.oauth2.filter.CsrfCookieFilter;
import com.app.oauth2.handler.AuthenticationSuccessHandlerImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.web.csrf.CsrfFilter;

@SpringBootApplication
@EnableWebSecurity
@EnableOAuth2Sso
public class Oauth2Application extends WebSecurityConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(Oauth2Application.class, args);
	}

	@Bean
	public OAuth2RestTemplate oAuth2RestTemplate(OAuth2ClientContext auth2ClientContext, OAuth2ProtectedResourceDetails details) {
		return new OAuth2RestTemplate(details, auth2ClientContext);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
                .csrf().disable()
				.authorizeRequests()
				.antMatchers("/xxxx").permitAll()
				.anyRequest().authenticated()
				.and().formLogin()
				.defaultSuccessUrl("/").permitAll()
				.and().logout().logoutSuccessUrl("/").invalidateHttpSession(true).deleteCookies("JSESSIONID").permitAll();
		http
				.addFilterAfter(new CsrfCookieFilter(), CsrfFilter.class);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(
				"/css/**",
				"/js/**",
				"/img/**"
		);
	}
}
