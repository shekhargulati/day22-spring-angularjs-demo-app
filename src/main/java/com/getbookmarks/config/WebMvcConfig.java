package com.getbookmarks.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.getbookmarks.rest.PingResource;

@EnableWebMvc
@ComponentScan(basePackageClasses = PingResource.class)
@Configuration
public class WebMvcConfig {

}
