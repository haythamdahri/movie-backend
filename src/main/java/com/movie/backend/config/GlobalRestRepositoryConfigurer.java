package com.movie.backend.config;

import com.movie.backend.entities.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@Configuration
public class GlobalRestRepositoryConfigurer extends RepositoryRestConfigurerAdapter {


    /*
     * @Expose the id to be sent in the request
     * @Allow origins
     * @Allow mapping
     * @Allow Headers
     * @Before adding JWT Authentication
     */
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(User.class, Role.class);
        config.getCorsRegistry()
                .addMapping("/**")
                .allowedOrigins("*")
                .allowedHeaders("*")
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS", "PATCH", "TRACE");
                .allowedMethods("*");

    }


}
