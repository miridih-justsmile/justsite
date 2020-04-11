package comm.justsmile.justsite.springboot.web.global.config.auth;

import comm.justsmile.justsite.springboot.web.global.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(final HttpSecurity security) throws Exception {
        security.csrf().disable()
                .headers().frameOptions().disable()
                .and()
                    .authorizeRequests()
                    .antMatchers("/api/v1/**", "/crud/posts/**").hasRole(Role.USER.name())
                    .anyRequest().permitAll()
                .and()
                    .logout()
                    .logoutSuccessUrl("/crud")
                .and()
                    .oauth2Login()
                    .defaultSuccessUrl("/crud", true)
                    .userInfoEndpoint()
                    .userService(customOAuth2UserService);
    }
}