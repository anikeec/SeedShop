/*
 * 
 * 
 */
package com.apu.seedshop;

public class AppConfig {
    
}
		// Set user password to "password" for demo purposes only
//		new SpringApplicationBuilder(WebApplication.class).properties(
//				"security.user.password=password").run(args);
//
//	@Override
//	public void addViewControllers(ViewControllerRegistry registry) {
//		registry.addViewController("/login").setViewName("login");
//	}
//
//	@Bean
//	public ApplicationSecurity applicationSecurity() {
//		return new ApplicationSecurity();
//	}
//
//	@Order(Ordered.LOWEST_PRECEDENCE - 8)
//	protected static class ApplicationSecurity extends WebSecurityConfigurerAdapter {
//		@Override
//		protected void configure(HttpSecurity http) throws Exception {
//			http.authorizeRequests().anyRequest().fullyAuthenticated().and().formLogin()
//					.loginPage("/login").failureUrl("/login?error").permitAll();
//		}
//	}