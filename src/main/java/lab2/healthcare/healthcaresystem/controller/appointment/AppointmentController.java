package lab2.healthcare.healthcaresystem.controller.appointment;

import lab2.healthcare.healthcaresystem.models.*;
import lab2.healthcare.healthcaresystem.repository.PatientRepository;
import lab2.healthcare.healthcaresystem.service.AppointmentServiceImpl;
import lab2.healthcare.healthcaresystem.service.DoctorService;
import lab2.healthcare.healthcaresystem.service.PatientService;
import lab2.healthcare.healthcaresystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Controller
public class AppointmentController {

    //patient should see the list of doctors that are available

    @Autowired
    DoctorService doctorService;
    @Autowired
    PatientService patientService;
    @Autowired
    AppointmentServiceImpl appointmentService;
    @Autowired
    UserService userService;
    @Autowired
    PatientRepository patientRepository;
    public Doctor doctorSelected;


    @RequestMapping(value = "/createAppointment/{id}", method = RequestMethod.GET)
    public ModelAndView createAppointment(@PathVariable(name = "id") Long id) {

   /*     LocalDate localDate = new LocalDate.now();
        String currentDateString = currentDate.toString();*/
        doctorSelected = doctorService.get(id);
        Appointment appointment = new Appointment();
        ModelAndView mav = new ModelAndView("appointment/createAppointment");
        appointment.setDoctor(doctorSelected);
        Patient patient = patientService.currentPatient();

        if(patient==null) {
            patient=new Patient();
            mav.addObject("patient", patient);
        }else{
            mav.addObject("patient",patient);
        }
        mav.addObject("doctorSelected", doctorSelected);
        mav.addObject("appointment", appointment);
        /*mav.addObject("currentDate",currentDate);*/

        return mav;
    }


    @RequestMapping(value = "/saveAppointment", method = RequestMethod.POST)
    public String saveAppointment(@ModelAttribute("appointment") Appointment appointment) throws ParseException {


        appointment.setDoctor(doctorSelected);
       /* String date = appointment.getDate().toString();
        Date date1=new SimpleDateFormat("mm/dd/yyyy").parse(date);
        appointment.setDate(date1);*/
        appointment.setPatient(patientService.currentPatient());
        appointment.setStatus(AppointmentStatusEnum.PENDING);
        appointmentService.save(appointment);

        return "redirect:/patientAppointments";
    }



    @RequestMapping("/listOfDoctors")
    public String viewListOfDoctors(Model model) {

        List<Doctor> listDoctors= doctorService.listAll();
        Patient patient = patientService.currentPatient();

        if(patient==null) {
            patient=new Patient();
            model.addAttribute("patient", patient);
        }else{
            model.addAttribute("patient",patient);
        }
        model.addAttribute("listDoctors", listDoctors);
        return "appointment/listOfDoctors";
    }


    @RequestMapping("/list")
    public String viewListOfAppointment(Model model) {
        User user = userService.currentUser();
        List<Appointment> list= appointmentService.list();
        model.addAttribute("appointmentList", list);
        model.addAttribute("user", user);
        return "admin/admin-appointments";
    }

    @RequestMapping("/doctorAppointments")
    public String viewDoctorAppointments(Model model) {

        Doctor doctor = doctorService.currentDoctor();

        List<Appointment> appointments = appointmentService.getAppointmentsByDoctorId(doctor);
        model.addAttribute("listAppointments",appointments);

        if(doctor==null) {
            doctor=new Doctor();
            model.addAttribute("doctor", doctor);
        }else{
            model.addAttribute("doctor",doctor);
        }

        return "doctor/appointment-list";
    }

    @RequestMapping("/patientAppointments")
    public String viewPatientAppointments(Model model) {

        Patient patient = patientService.currentPatient();

        List<Appointment> appointments = appointmentService.getAppointmentsByPatientId(patient);
        model.addAttribute("listAppointments", appointments);
        if(patient==null) {
            patient=new Patient();
            model.addAttribute("patient", patient);
        }else{
            model.addAttribute("patient",patient);
        }


        return "patient/appointment-list";
    }

    @RequestMapping(value = "/acceptAppointment/{id}", method = RequestMethod.GET)
    public String acceptAppointment(@PathVariable(name = "id") Long id,Model model) {

        Appointment appointmentDB = new Appointment();
        appointmentDB = appointmentService.get(id);
        appointmentDB.setDoctor(appointmentDB.getDoctor());
        appointmentDB.setDate(appointmentDB.getDate());
        appointmentDB.setPatient(appointmentDB.getPatient());
        appointmentDB.setDescription(appointmentDB.getDescription());
        appointmentDB.setStatus(AppointmentStatusEnum.ACCEPTED);
        appointmentService.save(appointmentDB);

        Doctor doctor = doctorService.currentDoctor();

        if(doctor==null) {
            doctor=new Doctor();
            model.addAttribute("doctor", doctor);
        }else{
            model.addAttribute("doctor",doctor);
        }

        List<Appointment> appointments = appointmentService.getAppointmentsByDoctorId(doctor);
        model.addAttribute("listAppointments", appointments);

        return "doctor/appointment-list";
    }



    @RequestMapping(value = "/declineAppointment/{id}", method = RequestMethod.GET)
    public String declineAppointment(@PathVariable(name = "id") Long id,Model model) {

        Appointment appointmentDB = new Appointment();
        appointmentDB = appointmentService.get(id);
        appointmentDB.setDoctor(appointmentDB.getDoctor());
        appointmentDB.setDate(appointmentDB.getDate());
        appointmentDB.setPatient(appointmentDB.getPatient());
        appointmentDB.setDescription(appointmentDB.getDescription());
        appointmentDB.setStatus(AppointmentStatusEnum.CANCELED);
        appointmentService.save(appointmentDB);

        Doctor doctor = doctorService.currentDoctor();

        if(doctor==null) {
            doctor=new Doctor();
            model.addAttribute("doctor", doctor);
        }else{
            model.addAttribute("doctor",doctor);
        }

        List<Appointment> appointments = appointmentService.getAppointmentsByDoctorId(doctor);
        model.addAttribute("listAppointments", appointments);

        return "doctor/appointment-list";
    }
}
