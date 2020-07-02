package lab2.healthcare.healthcaresystem.service;

import lab2.healthcare.healthcaresystem.models.Appointment;
import lab2.healthcare.healthcaresystem.models.Diagnose;

import java.util.List;

public interface DiagnoseService {

    void save(Diagnose diagnose);
    Diagnose getDiagnoseByAppointment(Appointment appointment);
    List<Diagnose> listAll();
}
