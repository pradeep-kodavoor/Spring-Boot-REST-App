package com.practice.springboot.basics.SpringBootRESTApp.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	public static final Contact DEFAULT_CONTACT = new Contact("Pradeep Kodavoor", "https://github.com/pradeep-kodavoor",
			"pradeep.kodavoor@gmail.com");

	public static final ApiInfo DEFAULT_API_INFO = new ApiInfo("Book Management Application",
			"App created for managing books", "1.0", "dummy", DEFAULT_CONTACT, "Apache 2.0",
			"http://www.apache.org/licenses/LICENSE-2.0");

	private static final ArrayList<String> list = new ArrayList<>();
	static {
		list.add("application/json");
		list.add("application/xml");
	}

	private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<>(list);

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(DEFAULT_API_INFO).produces(DEFAULT_PRODUCES_AND_CONSUMES)
				.consumes(DEFAULT_PRODUCES_AND_CONSUMES);
	}

}
