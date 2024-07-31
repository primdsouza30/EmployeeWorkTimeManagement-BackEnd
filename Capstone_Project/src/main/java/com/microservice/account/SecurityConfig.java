package com.microservice.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.microservice.account.service.UserInfoService;

@Configuration
@EnableWebSecurity
public class SecurityConfig { 

	@Autowired
	private UserInfoService userInfoService;
	/* configure filter chain for apis */
	@Bean //<-- to register this filterchain with spring 
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer:: disable)
            .authorizeHttpRequests(authorize -> authorize
            	.antMatchers(HttpMethod.GET,"/capstone/login").authenticated()	
            	.antMatchers(HttpMethod.POST,"/api/customer/add/{regionId}").permitAll()
            	.antMatchers(HttpMethod.GET,"/capstone/employee/getall").permitAll()	
            	.antMatchers(HttpMethod.GET,"/capstone/employee/getall").hasAnyAuthority("HR","MANAGER")	
            	.antMatchers(HttpMethod.POST,"/api/employee/add").hasAuthority("EMPLOYEE")
            	.antMatchers(HttpMethod.POST,"/api/country/add").authenticated()
            	.antMatchers(HttpMethod.POST,"/capstone/project/").hasAuthority("MANAGER")
            	.antMatchers(HttpMethod.GET,"/api/customer/getall").permitAll()	
            	.antMatchers(HttpMethod.POST,"/capstone/hr/add").permitAll()
            	.antMatchers(HttpMethod.POST,"/capstone/manager/add").hasAuthority("HR")
            	.antMatchers(HttpMethod.POST,"/capstone/employee/add/{managerId}").hasAuthority("HR")
            	.antMatchers(HttpMethod.POST,"/capstone/employee/project/assign/{employeeId}/{projectId}").hasAuthority("MANAGER")
            	.antMatchers(HttpMethod.POST,"/api/region/add/{countryId}").permitAll()
            	.antMatchers(HttpMethod.GET,"/api/region/all").permitAll()
            	.antMatchers(HttpMethod.GET,"/capstone/hr/stat").hasAuthority("HR")
            	.antMatchers(HttpMethod.GET,"/capstone/hr/manager/employee").hasAuthority("HR")
            	.antMatchers(HttpMethod.GET,"/capstone/jobtype").hasAuthority("HR")
            	.antMatchers(HttpMethod.GET,"/capstone/taskstatus").hasAnyAuthority("EMPLOYEE","MANAGER")
            	.antMatchers(HttpMethod.GET,"/capstone/manager/all").hasAuthority("HR")
            	.antMatchers(HttpMethod.GET,"/api/search/employee/manager/{searchStr}").permitAll()
            	.antMatchers(HttpMethod.GET,"/capstone/manager/employee").hasAuthority("MANAGER")
            	.antMatchers(HttpMethod.POST,"/capstone/task/employee/{projectId}/{eid}").hasAuthority("MANAGER")
            	.antMatchers(HttpMethod.GET,"/capstone/task/{eid}").hasAnyAuthority("MANAGER","EMPLOYEE")
            	.antMatchers(HttpMethod.GET,"/capstone/employee/notification/{eid}").hasAnyAuthority("MANAGER","EMPLOYEE")
            	.antMatchers(HttpMethod.GET,"/capstone/task/employee").hasAnyAuthority("MANAGER","EMPLOYEE")
            	.antMatchers(HttpMethod.GET,"/capstone/notification/employee").hasAnyAuthority("MANAGER","EMPLOYEE")
            	.antMatchers(HttpMethod.GET,"/capstone/task/archive/{tid}").hasAuthority("MANAGER")
            	.antMatchers(HttpMethod.GET,"/capstone/notification/archive/{nid}").hasAuthority("EMPLOYEE")
            	.antMatchers(HttpMethod.GET,"/capstone/project/all").hasAnyAuthority("MANAGER","EMPLOYEE")
            	.antMatchers(HttpMethod.POST,"/capstone/notification/employee/{eid}").hasAuthority("MANAGER")
            	.antMatchers(HttpMethod.POST,"/capstone/record/{tid}").hasAnyAuthority("EMPLOYEE","MANAGER")
            	.antMatchers(HttpMethod.GET,"/capstone/task/update/{tid}/{status}").hasAnyAuthority("EMPLOYEE")
            	.antMatchers(HttpMethod.GET,"/capstone/employee/notification/all").hasAnyAuthority("MANAGER","EMPLOYEE")
            	.antMatchers(HttpMethod.POST,"/capstone/record/employee/{tid}").hasAuthority("EMPLOYEE")
            	
            	.anyRequest().permitAll()
            )
            .httpBasic(Customizer.withDefaults());
        return http.build();
    }
	
	/* AuthenticationManager : in-memory / jdbc */
	@Bean
	public AuthenticationManager authenticationManager(){
		DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
		dao.setPasswordEncoder(getEncoder());
		dao.setUserDetailsService(userInfoService);
		ProviderManager manager = new ProviderManager(dao);
		return manager; 
	}
	@Bean
	public PasswordEncoder getEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder; 
	}
	 
}