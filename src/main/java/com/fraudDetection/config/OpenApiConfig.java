package com.fraudDetection.config;

import com.fraudDetection.constants.channels;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.Parameter;
import org.springdoc.core.customizers.OperationCustomizer;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(apiInfo());
    }

    @Bean
    @SuppressWarnings("unchecked")
    public OperationCustomizer customize() {
        Schema<?> channel = new Schema<>().type("string")
                ._enum(Arrays.stream(channels.values()).map(Enum::name).collect(Collectors.toList()));
        Schema<?> enc = new Schema<>().type("string")._enum(List.of("Yes", "No"));
        return (operation, handlerMethod) -> operation.addParametersItem(
                        new Parameter().in("header").description("Channel Name")
                                .required(true).name("Channel").schema(channel))
                .addParametersItem(
                        new Parameter().in("header").description("Encrypted Request/Response")
                                .required(true).name("enc").schema(enc));
    }

    private Info apiInfo() {
        Contact contact = new Contact()
                .email("contact@axisbank.com")
                .name("Axis Bank")
                .url("https://www.axisbank.com");

        License license = new License()
                .name("Axis Bank")
                .url("https://www.axisbank.com");

        return new Info()
                .title("Fraud-Detection")
                .version("1.0")
                .contact(contact)
                .description("The API checks the fraud status of an account by retrieving its details, such as whether it's blacklisted, suspicious, or pending. ")
                .termsOfService("https://www.axisbank.com")
                .license(license);
    }
}
