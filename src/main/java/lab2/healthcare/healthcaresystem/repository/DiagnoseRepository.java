package lab2.healthcare.healthcaresystem.repository;

import lab2.healthcare.healthcaresystem.models.Appointment;
import lab2.healthcare.healthcaresystem.models.Diagnose;
import lab2.healthcare.healthcaresystem.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiagnoseRepository extends JpaRepository<Diagnose,Long> {

    Diagnose findDiagnoseByAppointment(Appointment appointment);
    List<Diagnose> findDiagnoseByDoctor(Doctor doctor);
}
