package lab2.healthcare.healthcaresystem;

import lab2.healthcare.healthcaresystem.models.Doctor;
import lab2.healthcare.healthcaresystem.models.Patient;
import lab2.healthcare.healthcaresystem.models.User;
import lab2.healthcare.healthcaresystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("lab2.healthcare.healthcaresystem.repository")
public class HealthCareSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(HealthCareSystemApplication.class, args);

    }

}
