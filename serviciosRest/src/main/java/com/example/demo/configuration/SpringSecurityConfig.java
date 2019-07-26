package com.example.demo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	/*
	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    auth.jdbcAuthentication()
	    	.dataSource(dataSource)
	    	.usersByUsernameQuery("select name, password, enabled from users where name=?")
	        .authoritiesByUsernameQuery("select name, rol from users where name=?")
	        .passwordEncoder(new BCryptPasswordEncoder());
	}
	*/
	
    // Create 2 users for demo	
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("user").password("{noop}123").roles("USER")
                .and()
                .withUser("admin").password("{noop}123").roles("USER", "ADMIN");
    }

    // Secure the endpoins with HTTP Basic authentication
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                //HTTP Basic authentication
                .httpBasic()
                .and()
	                .authorizeRequests()
	                .antMatchers("/inseguros/**").permitAll()
	                //.antMatchers(HttpMethod.GET, "/ejemplo").authenticated()
	                .antMatchers(HttpMethod.GET, "/ejemplo/**").hasRole("USER")
	                .antMatchers(HttpMethod.POST, "/ejemplo/**").hasRole("ADMIN")
	                //.antMatchers(HttpMethod.PUT, "/ejemplo/**").hasRole("ADMIN")
	                //.antMatchers(HttpMethod.PATCH, "/ejemplo/**").hasRole("ADMIN")
	                //.antMatchers(HttpMethod.DELETE, "/ejemplo/**").hasRole("ADMIN")
                .and()
                .csrf().disable()
                //.anonymous().disable()
                .formLogin().disable();
    }

    /*@Bean
    public UserDetailsService userDetailsService() {
        //ok for demo
        User.UserBuilder users = User.withDefaultPasswordEncoder();

        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(users.username("user").password("password").roles("USER").build());
        manager.createUser(users.username("admin").password("password").roles("USER", "ADMIN").build());
        return manager;
    }*/

}
