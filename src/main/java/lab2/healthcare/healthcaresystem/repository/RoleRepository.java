package lab2.healthcare.healthcaresystem.repository;


import lab2.healthcare.healthcaresystem.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Long> {

   Role findByName(String name);
}
