package spring.resttemplate.example.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author Ilnur Nasybullin
 * @since 31.10.2022
 */
@EnableWebMvc
@SpringBootApplication
@ComponentScan("spring.resttemplate.example.client")
public class Client {
    public static void main(String[] args) {
        SpringApplication.run(Client.class, args);
    }
}
