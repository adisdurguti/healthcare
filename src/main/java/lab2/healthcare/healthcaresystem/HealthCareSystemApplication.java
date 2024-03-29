package lab2.healthcare.healthcaresystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("lab2.healthcare.healthcaresystem.repository")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class HealthCareSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(HealthCareSystemApplication.class, args);
    }

}
