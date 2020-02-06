package com.oagsate.hibro.config;

import com.oagsate.hibro.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {
    @Bean
    public LoginInterceptor getLoginInterceptor() {
        return new LoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(getLoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/static/**");;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //获取文件的真实路径
        String path = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\";
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {
            registry.addResourceHandler("/static/**").
                    addResourceLocations("file:"+path);
        }else{//linux和mac系统 可以根据逻辑再做处理
            registry.addResourceHandler("/static/**").
                    addResourceLocations("file:"+path);
        }
        super.addResourceHandlers(registry);
    }
}
