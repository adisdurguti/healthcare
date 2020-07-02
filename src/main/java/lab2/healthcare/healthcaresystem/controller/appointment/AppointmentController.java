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

        doctorSelected = doctorService.get(id);
        Appointment appointment = new Appointment();
        ModelAndView mav = new ModelAndView("appointment/createAppointment");
        appointment.setDoctor(doctorSelected);
        mav.addObject("doctorSelected", doctorSelected);
        mav.addObject("appointment", appointment);

        return mav;
    }


    @RequestMapping(value = "/saveAppointment", method = RequestMethod.POST)
    public String saveAppointment(@ModelAttribute("appointment") Appointment appointment) {

        Date testDate = new Date();
        testDate.getMonth();
        appointment.setDoctor(doctorSelected);
        appointment.setDate(testDate);
        appointment.setPatient(patientService.currentPatient());
        appointment.setStatus(AppointmentStatusEnum.PENDING);
        appointmentService.save(appointment);

        return "patient/patient";
    }



    @RequestMapping("/listOfDoctors")
    public String viewListOfDoctors(Model model) {

        List<Doctor> listDoctors= doctorService.listAll();
        model.addAttribute("listDoctors", listDoctors);

        return "appointment/listOfDoctors";
    }


    @RequestMapping("/list")
    public String viewListOfAppointment(Model model) {

        List<Appointment> list= appointmentService.list();
        model.addAttribute("appointmentList", list);

        return "admin/admin-appointments";
    }

    @RequestMapping("/doctorAppointments")
    public String viewDoctorAppointments(Model model) {

        Doctor doctor = doctorService.currentDoctor();

        List<Appointment> appointments = appointmentService.getAppointmentsByDoctorId(doctor);
        model.addAttribute("listAppointments", appointmentService.getAppointmentsByDoctorId(doctor));
        model.addAttribute("doctor", doctor);

        return "doctor/appointment-list";
    }

    @RequestMapping("/patientAppointments")
    public String viewPatientAppointments(Model model) {

        Patient patient = patientService.currentPatient();

        List<Appointment> appointments = appointmentService.getAppointmentsByPatientId(patient);
        model.addAttribute("listAppointments", appointments);
        model.addAttribute("patient", patient);

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
        List<Appointment> appointments = appointmentService.getAppointmentsByDoctorId(doctor);
        model.addAttribute("listAppointments", appointments);

        return "doctor/appointment-list";
    }
}
