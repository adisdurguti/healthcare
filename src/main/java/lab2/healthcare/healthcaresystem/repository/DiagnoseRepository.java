package lab2.healthcare.healthcaresystem.repository;

import lab2.healthcare.healthcaresystem.models.Appointment;
import lab2.healthcare.healthcaresystem.models.Diagnose;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiagnoseRepository extends JpaRepository<Diagnose,Long> {

    Diagnose findDiagnoseByAppointment(Appointment appointment);

}
