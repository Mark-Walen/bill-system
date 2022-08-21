package vlue.tech.billsystem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import vlue.tech.billsystem.interceptor.LoginInterceptor;

import javax.annotation.Resource;

//@Configuration
public class BillSystemWebMvcConfigurer implements WebMvcConfigurer {

    @Resource
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).excludePathPatterns("/api/auth/**");
    }
}
