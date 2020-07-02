package lab2.healthcare.healthcaresystem.repository;

import lab2.healthcare.healthcaresystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String username);

}

