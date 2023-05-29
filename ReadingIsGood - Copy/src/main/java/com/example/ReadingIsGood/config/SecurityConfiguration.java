package com.example.ReadingIsGood.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
  @Autowired
	private final JwtAuthenticationFilter jwtAuthFilter;
	@Autowired
  private final AuthenticationProvider authenticationProvider;
//  private final LogoutHandler logoutHandler;

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().requestMatchers(new AntPathRequestMatcher("/h2-console/**"));
	}

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf()
            //csrf: cross site request forgery : başka bir kullanıcıymış gibi request atmak : token kullanılarak çözülür.

            .disable().cors().disable()
        .authorizeHttpRequests()
        .requestMatchers("/api/v1/auth/**","/swagger-resources/**",
				 "/swagger-ui.html","/swagger-ui/**","/v3/api-docs","/v3/api-docs/**")
          .permitAll()
            .anyRequest()
          .authenticated()
        .and()
            //         .sessionManagement()
            //         .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            //       .and()
			.authenticationProvider(authenticationProvider);
//        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
//        .logout()
//        .logoutUrl("/api/v1/auth/logout")
//        .addLogoutHandler(logoutHandler)
//        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
	return http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class).build();

  }
}
