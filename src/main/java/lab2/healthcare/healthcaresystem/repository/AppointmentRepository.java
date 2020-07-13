package lab2.healthcare.healthcaresystem.repository;

import lab2.healthcare.healthcaresystem.models.Appointment;
import lab2.healthcare.healthcaresystem.models.AppointmentStatusEnum;
import lab2.healthcare.healthcaresystem.models.Doctor;
import lab2.healthcare.healthcaresystem.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    //  @Query("SELECT Patient.firstName,Patient .lastName,Patient.phone,Patient.country,Appointment.date,Appointment.description FROM Appointment INNER JOIN Patient ON Appointment.patient = Patient.idpatient")
    // @Query(" SELECT users.name, doctor.specialization,doctor.address,doctor.phone FROM users INNER JOIN doctor ON users.id = doctor.user_id")
    List<Appointment> findAll();

    List<Appointment> findAllByDoctor(Doctor doctor);

    List<Appointment> findAllByPatient(Patient patient);

    Appointment findById(long id);

    List<Appointment> findAllByStatus(AppointmentStatusEnum status);


}
