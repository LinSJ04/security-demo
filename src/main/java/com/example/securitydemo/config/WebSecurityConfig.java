package com.example.securitydemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
// @EnableWebSecurity // 开启SpringSecurity的自定义配置(在SpringBoot项目中可省略)
public class WebSecurityConfig {

	// 默认配置(可省)
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		//authorizeRequests()：开启授权保护 用户认证
		//anyRequest()：对所有请求开启授权保护
		//authenticated()：已认证请求会自动被授权
		http
				// 开启授权保护
				.authorizeRequests(authorize -> authorize.anyRequest().authenticated())
				// 默认的登录表单和默认的登出页面
				.formLogin( form -> {
					form
							.loginPage("/login").permitAll() //登录页面无需授权即可访问
							.usernameParameter("username") //自定义表单用户名参数，默认是username
							.passwordParameter("password") //自定义表单密码参数，默认是password
							.failureUrl("/login?error") //登录失败的返回地址，默认是error
							.successHandler(new MyAuthenticationSuccessHandler()) // 认证成功时的处理
					;
				}); //表单授权方式
				// 浏览器自带的登录框，没有登录表单，没有默认的登出页 一般不使用
//				.httpBasic(withDefaults());//基本授权方式

		// 关闭csrf攻击防御 方便测试
		// .../demo/doc.html
		http.csrf((csrf) -> {
			csrf.disable(); // 需要前端提供
		});
		return http.build();
	}

//	@Bean
//	public UserDetailsService userDetailsService() {
//		// 创建基于内存的用户信息管理器 自定义配置会替换默认配置(包括application.properties)
//		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//		// 使用manager管理UserDetails对象
//		manager.createUser(
//				// 创建UserDetails对象，用于管理用户名、用户密码、用户角色、用户权限等内容
//				User.withDefaultPasswordEncoder().username("user").password("123").roles("USER").build());
//		return manager;
//	}
}