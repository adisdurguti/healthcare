package lab2.healthcare.healthcaresystem.service;

import lab2.healthcare.healthcaresystem.models.Patient;
import lab2.healthcare.healthcaresystem.models.Role;
import lab2.healthcare.healthcaresystem.models.User;
import lab2.healthcare.healthcaresystem.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;


public interface PatientService {

     void savePatientData(Patient patient);
     Patient currentPatient();
     Patient get(long id);
     List<Patient> listAll();
}
