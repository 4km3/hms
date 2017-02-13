package io.ari.schemaValidations.configuration;

import com.google.common.collect.ImmutableMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class JsonValidatorConfiguration {

	@Bean(name = "jsonSchemas")
	public Map<String, Map<String, String>> jsonSchemas() {

        Map<String, Map<String,String>> schemas = new HashMap<>();

       	schemas.put("customers", ImmutableMap.of("POST", "/schemas/customerPost.json"));
		schemas.put("me", ImmutableMap.of("PUT", "/schemas/mePut.json"));

		return schemas;
	}

}
