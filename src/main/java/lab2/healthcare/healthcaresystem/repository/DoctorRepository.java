package lab2.healthcare.healthcaresystem.repository;

import lab2.healthcare.healthcaresystem.models.Doctor;
import lab2.healthcare.healthcaresystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("doctorRepository")
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
/*

   @Query("SELECT d FROM doctor d WHERE d.iddoctor = 1")
   // @Query(" SELECT users.name, doctor.specialization,doctor.address,doctor.phone FROM users INNER JOIN doctor ON users.id = doctor.user_id")
    List<Doctor> getDoctors();
*/

    Doctor findById(long id);

    Doctor findByUser(User user);

    List<Doctor> findAllBySpecialization(String specialization);
}
