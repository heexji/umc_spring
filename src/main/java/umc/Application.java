package umc;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableJpaAuditing
@SpringBootApplication
@EntityScan(basePackages = "umc")

public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
