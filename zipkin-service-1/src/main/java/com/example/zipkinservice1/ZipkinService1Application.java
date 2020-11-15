package com.example.zipkinservice1;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ZipkinService1Application {

	public static void main(String[] args) {
		SpringApplication.run(ZipkinService1Application.class, args);
	}
}

@RestController
class ZipkinController{
	
	@Autowired
	RestTemplate restTemplate;
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public AlwaysSampler alwaysSampler() {
		return new AlwaysSampler();
	}
	
	private static final Logger LOG = Logger.getLogger(ZipkinController.class.getName());
	
	@GetMapping(value="/zipkin")
	public String zipkinService1() {
		LOG.info("Inside zipkinService 1..");
		LOG.debug("Inside zipkinService 1..");
		LOG.fatal("Inside zipkinService 1..");
					
		String response = null;
		 try {
			 response = (String) restTemplate.exchange("http://localhost:8082/zipkin2", HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
		        }).getBody();
		} catch (Exception e) {
			System.err.println("SKATA");
		}

		return "Hi...";
	}
}
