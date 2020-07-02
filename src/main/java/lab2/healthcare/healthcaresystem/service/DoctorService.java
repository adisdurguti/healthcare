package lab2.healthcare.healthcaresystem.service;

import lab2.healthcare.healthcaresystem.models.Appointment;
import lab2.healthcare.healthcaresystem.models.Doctor;
import lab2.healthcare.healthcaresystem.models.Rating;

import java.util.List;


public interface DoctorService {

    void saveDoctorData(Doctor doctor);
    List<Doctor> listAll();
    Doctor get(long id);
    Doctor currentDoctor();
    List<Rating> ratings(Doctor doctor);
}
