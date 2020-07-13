package lab2.healthcare.healthcaresystem.service;

import lab2.healthcare.healthcaresystem.models.Patient;

import java.util.List;


public interface PatientService {

    void savePatientData(Patient patient);

    Patient currentPatient();

    Patient get(long id);

    List<Patient> listAll();
}
