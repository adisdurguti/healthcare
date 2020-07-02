package lab2.healthcare.healthcaresystem.service;


import lab2.healthcare.healthcaresystem.models.*;
import lab2.healthcare.healthcaresystem.repository.AppointmentRepository;
import lab2.healthcare.healthcaresystem.repository.DoctorRepository;
import lab2.healthcare.healthcaresystem.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    @Qualifier("doctorRepository")
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private UserServiceImpl userService;

    @Override
    public void save(Appointment appointment) {appointmentRepository.save(appointment);}

    @Override
    public List<Appointment> list() {return appointmentRepository.findAll();}

    @Override
    public List<Appointment> getAppointmentsByDoctorId(Doctor doctor) {
        List<Appointment> doctorAppointments = new ArrayList<>();
        doctorAppointments=appointmentRepository.findAllByDoctor(doctorService.currentDoctor());
        Collections.sort(doctorAppointments,Collections.reverseOrder());
        return doctorAppointments;
    }

    @Override
    public List<Appointment> getAppointmentsByPatientId(Patient patient) {
        return appointmentRepository.findAllByPatient(patientService.currentPatient());
    }

    @Override
    public Appointment get(long id) {
        return appointmentRepository.findById(id);
    }

    @Override
    public int acceptedAppointment() {
        return appointmentRepository.findAllByStatus(AppointmentStatusEnum.ACCEPTED).size();
    }

    @Override
    public int declinedAppointment() {
        return appointmentRepository.findAllByStatus(AppointmentStatusEnum.CANCELED).size();
    }

    @Override
    public int pendingAppointment() {
        return appointmentRepository.findAllByStatus(AppointmentStatusEnum.PENDING).size();
    }

    @Override
    public int januaryAppointments() {

        Calendar calendar = Calendar.getInstance();

        int countJanuary = 0;

        for (Appointment appointment:appointmentRepository.findAll()) {
            calendar.setTime(appointment.getDate());
            if (calendar.get(Calendar.MONTH) == Calendar.JANUARY){
                countJanuary++;
            }

        }

        return countJanuary;
    }

    @Override
    public int februaryAppointments() {
        Calendar calendar = Calendar.getInstance();

        int countFebruary = 0;

        for (Appointment appointment:appointmentRepository.findAll()) {
            calendar.setTime(appointment.getDate());
            if (calendar.get(Calendar.MONTH) == Calendar.FEBRUARY){
                countFebruary++;
            }

        }

        return countFebruary;
    }

    @Override
    public int marchAppointments() {
        Calendar calendar = Calendar.getInstance();

        int countMarch = 0;

        for (Appointment appointment:appointmentRepository.findAll()) {
            calendar.setTime(appointment.getDate());
            if (calendar.get(Calendar.MONTH) == Calendar.MARCH){
                countMarch++;
            }

        }

        return countMarch;
    }

    @Override
    public int aprilAppointments() {
        Calendar calendar = Calendar.getInstance();

        int countApril = 0;

        for (Appointment appointment:appointmentRepository.findAll()) {
            calendar.setTime(appointment.getDate());
            if (calendar.get(Calendar.MONTH) == Calendar.APRIL){
                countApril++;
            }

        }

        return countApril;
    }

    @Override
    public int mayAppointments() {
        Calendar calendar = Calendar.getInstance();

        int countMay = 0;

        for (Appointment appointment:appointmentRepository.findAll()) {
            calendar.setTime(appointment.getDate());
            if (calendar.get(Calendar.MONTH) == Calendar.MAY){
                countMay++;
            }

        }

        return countMay;
    }

    @Override
    public int juneAppointments() {
        Calendar calendar = Calendar.getInstance();

        int countJune = 0;

        for (Appointment appointment:appointmentRepository.findAll()) {
            calendar.setTime(appointment.getDate());
            if (calendar.get(Calendar.MONTH) == Calendar.JUNE){
                countJune++;
            }

        }

        return countJune;
    }

    @Override
    public int julyAppointments() {
        Calendar calendar = Calendar.getInstance();

        int countJuly = 0;

        for (Appointment appointment:appointmentRepository.findAll()) {
            calendar.setTime(appointment.getDate());
            if (calendar.get(Calendar.MONTH) == Calendar.JULY){
                countJuly++;
            }

        }

        return countJuly;
    }

    @Override
    public int augustAppointments() {
        Calendar calendar = Calendar.getInstance();

        int countAugust = 0;

        for (Appointment appointment:appointmentRepository.findAll()) {
            calendar.setTime(appointment.getDate());
            if (calendar.get(Calendar.MONTH) == Calendar.AUGUST){
                countAugust++;
            }

        }

        return countAugust;
    }

    @Override
    public int septemberAppointments() {
        Calendar calendar = Calendar.getInstance();

        int countSeptember = 0;

        for (Appointment appointment:appointmentRepository.findAll()) {
            calendar.setTime(appointment.getDate());
            if (calendar.get(Calendar.MONTH) == Calendar.SEPTEMBER){
                countSeptember++;
            }

        }

        return countSeptember;
    }

    @Override
    public int octoberAppointments() {
        Calendar calendar = Calendar.getInstance();

        int countOctober = 0;

        for (Appointment appointment:appointmentRepository.findAll()) {
            calendar.setTime(appointment.getDate());
            if (calendar.get(Calendar.MONTH) == Calendar.OCTOBER){
                countOctober++;
            }

        }

        return countOctober;
    }

    @Override
    public int novemberAppointments() {
        Calendar calendar = Calendar.getInstance();

        int countNovember = 0;

        for (Appointment appointment:appointmentRepository.findAll()) {
            calendar.setTime(appointment.getDate());
            if (calendar.get(Calendar.MONTH) == Calendar.NOVEMBER){
                countNovember++;
            }

        }

        return countNovember;
    }

    @Override
    public int decemberAppointments() {
        Calendar calendar = Calendar.getInstance();

        int countDecember = 0;

        for (Appointment appointment:appointmentRepository.findAll()) {
            calendar.setTime(appointment.getDate());
            if (calendar.get(Calendar.MONTH) == Calendar.DECEMBER){
                countDecember++;
            }

        }

        return countDecember;
    }
}






