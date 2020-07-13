package lab2.healthcare.healthcaresystem.service;

import lab2.healthcare.healthcaresystem.models.Doctor;
import lab2.healthcare.healthcaresystem.models.Rating;
import lab2.healthcare.healthcaresystem.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class DoctorServiceImpl implements DoctorService {

    @Qualifier("doctorRepository")
    @Autowired
    private DoctorRepository doctorRepository;


    @Autowired
    private UserServiceImpl userService;


    public List<Doctor> listAll() {
        return doctorRepository.findAll();
    }


    public Doctor get(long id) {
        return doctorRepository.findById(id);
    }

    @Override
    public Doctor currentDoctor() {
        return doctorRepository.findByUser(userService.currentUser());
    }

    @Override
    public List<Rating> ratings(Doctor doctor) {
        return ratings(currentDoctor());
    }


    @Override
    public void saveDoctorData(Doctor doctor) {


        if (doctorRepository.findByUser(userService.currentUser()) == null) {
            doctor.setFirstName(doctor.getFirstName());
            doctor.setLastName(doctor.getLastName());
            doctor.setAddress(doctor.getAddress());
            doctor.setPhone(doctor.getPhone());
            doctor.setCity(doctor.getCity());
            doctor.setCountry(doctor.getCountry());
            doctor.setSex(doctor.getSex());
            doctor.setUser(userService.currentUser());
            doctor.setSpecialization(doctor.getSpecialization());
            doctorRepository.save(doctor);
        } else {

        }
    }
}
