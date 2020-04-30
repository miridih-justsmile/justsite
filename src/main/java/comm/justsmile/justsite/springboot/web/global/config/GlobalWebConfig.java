package comm.justsmile.justsite.springboot.web.global.config;

import comm.justsmile.justsite.springboot.web.global.config.auth.LoginUserArgumentResolver;
import comm.justsmile.justsite.springboot.web.interceptor.LoggingInterceptor;
import comm.justsmile.justsite.springboot.web.interceptor.VisitorInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class GlobalWebConfig implements WebMvcConfigurer {
    private final LoginUserArgumentResolver loginUserArgumentResolver;
    private final VisitorInterceptor visitorInterceptor;

    @Autowired
    public GlobalWebConfig(VisitorInterceptor visitorInterceptor, LoginUserArgumentResolver loginUserArgumentResolver) {
        this.loginUserArgumentResolver = loginUserArgumentResolver;
        this.visitorInterceptor = visitorInterceptor;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(loginUserArgumentResolver);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoggingInterceptor()).addPathPatterns("/*");
        registry.addInterceptor(visitorInterceptor).addPathPatterns("/*");
    }
}
