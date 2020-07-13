package lab2.healthcare.healthcaresystem.service;

import lab2.healthcare.healthcaresystem.models.Appointment;
import lab2.healthcare.healthcaresystem.models.Diagnose;
import lab2.healthcare.healthcaresystem.repository.DiagnoseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiagnoseServiceImpl implements DiagnoseService {

    @Autowired
    DiagnoseRepository diagnoseRepository;

    public void save(Diagnose diagnose) {
        diagnoseRepository.save(diagnose);
    }

    @Override
    public Diagnose getDiagnoseByAppointment(Appointment appointment) {
        return diagnoseRepository.findDiagnoseByAppointment(appointment);
    }

    @Override
    public List<Diagnose> listAll() {
        return diagnoseRepository.findAll();
    }
}
