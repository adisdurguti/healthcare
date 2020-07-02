package lab2.healthcare.healthcaresystem.repository;

import lab2.healthcare.healthcaresystem.models.Patient;
import lab2.healthcare.healthcaresystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {

    Patient findById(long id);
    Patient findByUser(User user);

}
