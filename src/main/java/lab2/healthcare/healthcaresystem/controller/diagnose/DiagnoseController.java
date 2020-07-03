package lab2.healthcare.healthcaresystem.controller.diagnose;

import lab2.healthcare.healthcaresystem.models.Appointment;
import lab2.healthcare.healthcaresystem.models.Diagnose;
import lab2.healthcare.healthcaresystem.models.Doctor;
import lab2.healthcare.healthcaresystem.models.Patient;
import lab2.healthcare.healthcaresystem.repository.AppointmentRepository;
import lab2.healthcare.healthcaresystem.repository.DiagnoseRepository;
import lab2.healthcare.healthcaresystem.service.AppointmentService;
import lab2.healthcare.healthcaresystem.service.DiagnoseService;
import lab2.healthcare.healthcaresystem.service.DoctorService;
import lab2.healthcare.healthcaresystem.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Controller
public class DiagnoseController {

    @Autowired
    AppointmentService appointmentService;
    @Autowired
    PatientService patientService;
    @Autowired
    DoctorService doctorService;
    @Autowired
    DiagnoseService diagnoseService;
    @Autowired
    DiagnoseRepository diagnoseRepository;
    @Autowired
    AppointmentRepository appointmentRepository;
    public Appointment appointmentDiagnose;
    public Doctor doctorDiagnose;
    public Patient patientDiagnose;





/*
    @RequestMapping(value = "/addDiagnose/{idAppointment}/{idDoctor}/{idPatient}", method = RequestMethod.POST)
    public String addDiagnose(@PathVariable(name = "idAppointment") Long idAppointment, @PathVariable(name = "idDoctor") Long idDoctor, @PathVariable(name = "idPatient") Long idPatient, @ModelAttribute("diagnose") Diagnose diagnose, Model model) {


       *//* String username;

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        User currentUser = new User();

        currentUser = userService.findUserByUsername(username);

        Patient currentPatient = new Patient();

        currentPatient = patientRepository.findByUser(currentUser);
*//*






        Appointment appointment = new Appointment();

        appointment = appointmentService.get(idAppointment);

        Doctor doctor = new Doctor();

        doctor = doctorService.get(idDoctor);

        Patient patient = new Patient();

        patient = patientService.get(idPatient);

       // Diagnose diagnose = new Diagnose();

        Date date = new Date();

        diagnose.setPatient(patient);
        diagnose.setDateCreated(date);
        diagnose.setDoctor(doctor);
        diagnose.setAppointment(appointment);
        diagnoseService.save(diagnose);


    *//*    ModelAndView mav = new ModelAndView("doctor/createAppointment");*//*

        Doctor doctor1 = appointmentService.currentDoctor();

        List<Appointment> appointments = appointmentService.getAppointmentsByDoctorId(doctor1);

        model.addAttribute("listAppointments", appointments);




        return "doctor/appointment-list";
    }*/


    @RequestMapping(value = "/addDiagnose/{idAppointment}/{idDoctor}/{idPatient}", method = RequestMethod.GET)
    public ModelAndView createAppointment(@PathVariable(name = "idAppointment") Long idAppointment, @PathVariable(name = "idDoctor") Long idDoctor, @PathVariable(name = "idPatient") Long idPatient) {

        appointmentDiagnose = appointmentService.get(idAppointment);
        doctorDiagnose = doctorService.get(idDoctor);
        patientDiagnose = patientService.get(idPatient);
        Doctor doctor = doctorService.currentDoctor();
        ModelAndView mav = new ModelAndView("doctor/add-diagnose");
        Diagnose diagnose = new Diagnose();

        mav.addObject("diagnose", diagnose);
        mav.addObject("appointmentDiagnose", appointmentDiagnose);
        mav.addObject("doctorDiagnose", doctorDiagnose);
        mav.addObject("patientDiagnose", patientDiagnose);
        if(doctor==null) {
            doctor=new Doctor();
            mav.addObject("doctor", doctor);
        }else{
            mav.addObject("doctor",doctor);
        }

        return mav;
    }

    /*@GetMapping("/add-diagnose")
    @ResponseBody
    public Appointment findDiagnose(Appointment appointment, Doctor doctor , Patient patient){
        appointmentDiagnose = appointmentService.get(appointment.getIdAppointment());
        doctorDiagnose = doctorService.get(doctor.getIddoctor());
        patientDiagnose = patientService.get(patient.getIdpatient());
        return appointmentRepository.findByIds(appointmentDiagnose,doctorDiagnose,patientDiagnose);
    }*/



    @RequestMapping(value = "/saveDiagnose", method = RequestMethod.POST)
    public String saveDiagnose(@ModelAttribute("diagnose") Diagnose diagnose, Model model) {

        Date date = new Date();
        diagnose.setDateCreated(date);
        diagnose.setAppointment(appointmentDiagnose);
        diagnose.setDoctor(doctorDiagnose);
        diagnose.setPatient(patientDiagnose);
        diagnoseService.save(diagnose);

        Doctor doctor = doctorService.currentDoctor();
        List<Appointment> appointments = appointmentService.getAppointmentsByDoctorId(doctor);
        model.addAttribute("listAppointments", appointments);

        return "redirect:/doctorAppointments";
    }


    @RequestMapping(value = "/viewDiagnose/{id}", method = RequestMethod.GET)
    public ModelAndView viewDiagnose(@PathVariable(name = "id") Long id) {

        Appointment appointment = new Appointment();
        appointment = appointmentService.get(id);
        Patient patient = patientService.currentPatient();
        Diagnose diagnose = diagnoseService.getDiagnoseByAppointment(appointment);
        ModelAndView mav = new ModelAndView("patient/view-diagnose");
        mav.addObject("diagnose", diagnose);
        if(patient==null) {
            patient=new Patient();
            mav.addObject("patient", patient);
        }else{
            mav.addObject("patient",patient);
        }


        return mav;
    }
}