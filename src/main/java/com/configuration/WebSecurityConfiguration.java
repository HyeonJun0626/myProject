package com.configuration;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.service.MyUserDetailsService;

import lombok.RequiredArgsConstructor;

@Configuration
// 웹 보안을 활성화,
// 자체만으로 유효하지 않고 스프링 시큐리티가 WebSecurityConfigurer를 구현하거나 컨텍스트의 WebSecurityConfigurerAdapter를 확장 빈으로 설정해야함
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	private MyUserDetailsService myUserDetailsDervice;
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
	// HttpSecurity를 통해서 HTTP 요청에 대한 웹 기반 보안을 구성할 수 있다.
	protected void configure(HttpSecurity http) throws Exception {
		// SessionCreationPolicy.IF_REQUIRED : 스프링 시큐리티가 필요시 생성(기본)
		// SessionCreationPolicy.STATELESS: Spring Security가 세션을 생성하지도, 기존 세션을 사용하지도 않게함 (jwt같은 토큰방식을 사용할 때)
		// SessionCreationPolicy.ALWAYS : 스프링 시큐리티가 항상 세션을 사용
		// SessionCreationPolicy.NEVER : 스프링 시큐리티가 생성하지 않지만, 기존에 존재하면 사용
		http
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) 
			.and()
			.httpBasic().disable() // 특정 resource에 대한 접근을 요청할 때 브라우저가 사용자에게 username과 password를 확인해 인가를 제한하는 방법
//										심플하며 쿠키, 세션, loginPage도 필요없음 보안에 취약, log out 메서드 제공하지 않음
			.formLogin().disable() // 기본 로그인 페이지 없애기. SpringSequrity에서 제공하는 기본 로그인 페이지를 말하는것 같다.
			.csrf().disable() // csrf(위조 사이트 요청) 보호기능 httpBaic으로 테스트를 위해 잠시 disable
			.cors().disable()
//			.and()
			.authorizeRequests() // 요청에 대한 사용권한 체크
			.antMatchers("/authenticate").permitAll()
			.antMatchers("/").permitAll()
			.antMatchers("/user/**").permitAll() // antMatchers 경로를 지정, permitAll 누구나 접근 가능
//			.antMatchers("/api/signup").permitAll()
//			.antMatchers("/user/getUserInfo").authenticated()
//			.antMatchers("/api/main").authenticated() //authenticated 인증된 사용자만 접근 가능
//			.anyRequest().authenticated() // anyRequest 모든 요청에 대해 authenticated 인중된 사용자만 접근
			.and()
//			.headers().frameOptions().disable() //iframe으로 동작하는 기능이 동작하지 않을때 사용한다고 함 확인헤 볼 것
			.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
//        	JwtAuthenticationFilter를 UsernamePasswordAuthenticationFilter 전에 넣는다
	}
	
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// Cors 에러를 처리 하기위한 부분 
	@Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowCredentials(true);
        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowedHeaders(Arrays.asList("X-Requested-With","Origin","Content-Type","Accept","Authorization"));
        
        configuration.setExposedHeaders(Arrays.asList("Access-Control-Allow-Headers", "Authorization, x-xsrf-token, Access-Control-Allow-Headers, Origin, Accept, X-Requested-With, " +
                "Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
	
	
	
	@Override
	// SpringSecurity에서 모든 인증은 AuthenticationManager를 통해서 이루어짐 AuthenticationManagerBuilder를 통해서 생성
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// inMemory 형식이 아니라 DB를 사용한다면 userDetailService 등을 통해 유저 정보를 가져오면 됨
		// userDetailsService 인터페이스를 implements하여 lodaUserByUsername() 메서드를 구현
//		auth.inMemoryAuthentication()
//			// {noop}을 접두사로 사용하면 NoOpPasswordEncoder 가 됨
//			.withUser("foo").password("{noop}bar").roles("USER");
		auth.userDetailsService(myUserDetailsDervice).passwordEncoder(getPasswordEncoder());
	}
}
