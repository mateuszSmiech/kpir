package pl.kpir.kpir;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource( value = {"application-ext.properties"})
public class KpirApplication {

	public static void main(String[] args) {
		SpringApplication.run(KpirApplication.class, args);
	}
}
