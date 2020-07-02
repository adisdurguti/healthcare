package lab2.healthcare.healthcaresystem.service;

import lab2.healthcare.healthcaresystem.models.Appointment;
import lab2.healthcare.healthcaresystem.models.Doctor;
import lab2.healthcare.healthcaresystem.models.Patient;
import lab2.healthcare.healthcaresystem.models.User;
import org.springframework.stereotype.Component;

import java.util.List;

public interface AppointmentService {

    void save(Appointment appointment);
    List<Appointment> list();
    List<Appointment> getAppointmentsByDoctorId(Doctor doctor);
    List<Appointment> getAppointmentsByPatientId(Patient patient);
    Appointment get(long id);


    int acceptedAppointment();

    int declinedAppointment();

    int pendingAppointment();


    int januaryAppointments();

    int februaryAppointments();

    int marchAppointments();

    int aprilAppointments();

    int mayAppointments();

    int juneAppointments();

    int julyAppointments();

    int augustAppointments();

    int septemberAppointments();

    int octoberAppointments();

    int novemberAppointments();

    int decemberAppointments();
}
