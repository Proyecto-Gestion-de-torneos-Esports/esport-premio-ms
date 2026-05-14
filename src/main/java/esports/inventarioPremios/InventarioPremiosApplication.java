package esports.inventarioPremios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class InventarioPremiosApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventarioPremiosApplication.class, args);
	}

}
