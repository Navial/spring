package cours.vinci.amazing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @EnableEurekaClient
// @EnableFeignClients
public class AmazingApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmazingApplication.class, args);
    }

}
