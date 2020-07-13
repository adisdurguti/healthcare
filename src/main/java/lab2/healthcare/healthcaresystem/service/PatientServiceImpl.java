package lab2.healthcare.healthcaresystem.service;

import lab2.healthcare.healthcaresystem.models.Patient;
import lab2.healthcare.healthcaresystem.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private UserServiceImpl userService;

    @Override
    public Patient currentPatient() {
        return patientRepository.findByUser(userService.currentUser());
    }

    @Override
    public Patient get(long id) {
        return patientRepository.findById(id);
    }

    @Override
    public List<Patient> listAll() {
        return patientRepository.findAll();
    }

    @Override
    public void savePatientData(Patient patient) {

        patient.setFirstName(patient.getFirstName());
        patient.setLastName(patient.getLastName());
        patient.setAddress(patient.getAddress());
        patient.setPhone(patient.getPhone());
        patient.setCity(patient.getCity());
        patient.setCountry(patient.getCountry());
        patient.setSex(patient.getSex());
        patient.setUser(userService.currentUser());
        patientRepository.save(patient);
    }


}
