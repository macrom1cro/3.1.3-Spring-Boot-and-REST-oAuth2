package crud.security;

import crud.model.Role;
import crud.model.User;
import crud.repository.RoleRepository;
import crud.repository.UserRepository;
import crud.security.handler.LoginSuccessHandler;
import crud.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2SsoProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoRestTemplateFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.preauth.x509.X509PrincipalExtractor;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableOAuth2Sso
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final LoginSuccessHandler loginSuccessHandler;

    public SecurityConfig(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService, LoginSuccessHandler loginSuccessHandler) {
        this.userDetailsService = userDetailsService;
        this.loginSuccessHandler = loginSuccessHandler;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
//        указываем страницу с формой логина
                .loginPage("/login2")
//        указываем логику обработки при логине
                .successHandler(loginSuccessHandler)
//        указываем action с формы логина
                .loginProcessingUrl("/login2")
                // Указываем параметры логина и пароля с формы логина
                .usernameParameter("username")
                .passwordParameter("password")
//        даем доступ к форме логина всем
                .permitAll();

        http.logout()
                // разрешаем делать логаут всем
                .permitAll()
                // указываем URL логаута
                .deleteCookies("JSESSIONID", "XSRF-TOKEN")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .invalidateHttpSession(true)
                // указываем URL при удачном логауте
                .logoutSuccessUrl("/login2")
                //выклчаем кроссдоменную секьюрность (на этапе обучения неважна)
                .and().csrf().disable();
        http
                // делаем страницу регистрации недоступной для авторизированных пользователей
                .authorizeRequests()
                //страницы аутентификаци доступна всем
                .antMatchers("/login2").anonymous()
                // защищенные URL
                .antMatchers("/admin**").access("hasAnyRole('ADMIN')")
                .antMatchers("/user").access("hasAnyRole('USER')")
                //Все остальные страницы требуют аутентификации
                .anyRequest().authenticated();
        http.addFilterAfter(oAuth2ClientAuthenticationProcessingFilter(), LogoutFilter.class);
    }

    @Bean
    public PrincipalExtractor principalExtractor(UserServiceImpl userServiceImpl, RoleRepository roleRepository) {

        return map -> {
            User user = userServiceImpl.getUserByName((String) map.get("email"));
            if (user == null) {
                User newUser = new User();
                Set<Role> roles = new HashSet<>();
                roles.add(roleRepository.findById(2L).get());
                roles.add(roleRepository.findById(1L).get());
                newUser.setFirstName((String) map.get("name"));
                newUser.setEmail((String) map.get("email"));
                newUser.setPassword("user");
                newUser.setRoles(roles);
//                newUser.setLastName("123");
                return userServiceImpl.saveUser(newUser);
            } else {
                return userServiceImpl.saveUser(user);
            }
        };
    }

        private OAuth2ClientAuthenticationProcessingFilter oAuth2ClientAuthenticationProcessingFilter() {
        OAuth2SsoProperties sso = (OAuth2SsoProperties)this.getApplicationContext().getBean(OAuth2SsoProperties.class);
        OAuth2RestOperations restTemplate = ((UserInfoRestTemplateFactory)this.getApplicationContext().getBean(UserInfoRestTemplateFactory.class)).getUserInfoRestTemplate();
        ResourceServerTokenServices tokenServices = (ResourceServerTokenServices)this.getApplicationContext().getBean(ResourceServerTokenServices.class);
        OAuth2ClientAuthenticationProcessingFilter filter = new OAuth2ClientAuthenticationProcessingFilter(sso.getLoginPath());
        filter.setRestTemplate(restTemplate);
        filter.setTokenServices(tokenServices);
        filter.setApplicationEventPublisher(this.getApplicationContext());
        filter.setAuthenticationSuccessHandler(loginSuccessHandler);
        return filter;
    }
}
