package cl.apirest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = { "cl.apirest" })
@EnableJpaRepositories(basePackages = "cl.apirest.db.repository")
@EnableTransactionManagement
@EntityScan(basePackages = "cl.apirest.db.entity")
public class ApiProductosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiProductosApplication.class, args);
	}

}
