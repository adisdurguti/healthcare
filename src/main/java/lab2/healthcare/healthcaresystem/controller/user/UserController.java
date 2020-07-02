package lab2.healthcare.healthcaresystem.controller.user;


import lab2.healthcare.healthcaresystem.models.Doctor;
import lab2.healthcare.healthcaresystem.models.Patient;
import lab2.healthcare.healthcaresystem.models.User;
import lab2.healthcare.healthcaresystem.service.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.print.Doc;
import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class  UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    DoctorService doctorService;

    @Autowired
    PatientService patientService;

    @Autowired
    DiagnoseService diagnoseService;


    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
     public ModelAndView login() {

         ModelAndView model = new ModelAndView();
         model.setViewName("auth/login");

        return model;
    }


    @RequestMapping(value = {"/register"}, method = RequestMethod.GET)
    public ModelAndView signup() {
        ModelAndView model = new ModelAndView();
        User user = new User();
        model.addObject("user", user);
        model.setViewName("auth/register");

        return model;
    }

    @RequestMapping(value = {"/register"}, method = RequestMethod.POST)
    public ModelAndView createUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView model = new ModelAndView();
        User userExists = userService.findUserByUsername(user.getUsername());

        if (userExists != null) {
            bindingResult.rejectValue("email", "error.user", "This email already exists");
        }
        if (bindingResult.hasErrors()) {
            model.setViewName("auth/register");
        } else {
            userService.saveUser(user);
            model.addObject("msg", "User has been registered successfully!");
            model.addObject("user", new User());
            model.setViewName("auth/login");
        }
        return model;
    }

    @RequestMapping(value = {"/patient"}, method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(auth.getName());
        Patient patient = patientService.currentPatient();

        model.addObject("userName", user.getUsername() + " " + user.getId());
        model.addObject("patient", patient);
        model.setViewName("patient/patient");
        return model;
    }
    @RequestMapping(value = {"/doctor"}, method = RequestMethod.GET)
    public ModelAndView doctor(){
        ModelAndView model = new ModelAndView();
        Doctor doctor = doctorService.currentDoctor();
        model.addObject("doctor", doctor);
        model.setViewName("doctor/doctor");

        return model;
    }

    @RequestMapping(value = {"/admin"}, method = RequestMethod.GET)
    public String admin(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(auth.getName());

        Map<String,Integer> appointmentGraph = new LinkedHashMap<>();

        appointmentGraph.put("January",appointmentService.januaryAppointments());
        appointmentGraph.put("February",appointmentService.februaryAppointments());
        appointmentGraph.put("March",appointmentService.marchAppointments());
        appointmentGraph.put("April",appointmentService.aprilAppointments());
        appointmentGraph.put("May",appointmentService.mayAppointments());
        appointmentGraph.put("June",appointmentService.juneAppointments());
        appointmentGraph.put("July",appointmentService.julyAppointments());
        appointmentGraph.put("August",appointmentService.augustAppointments());
        appointmentGraph.put("September",appointmentService.septemberAppointments());
        appointmentGraph.put("October",appointmentService.octoberAppointments());
        appointmentGraph.put("November",appointmentService.novemberAppointments());
        appointmentGraph.put("December",appointmentService.decemberAppointments());


        int countDoctor = doctorService.listAll().size();
        int countPatient = patientService.listAll().size();
        int countAppointment = appointmentService.list().size();
        int countDiagnoses = diagnoseService.listAll().size();

        model.addAttribute("appointmentGraph",appointmentGraph);
        model.addAttribute("user",user);
        model.addAttribute("countDoctor",countDoctor);
        model.addAttribute("countPatient",countPatient);
        model.addAttribute("countAppointment",countAppointment);
        model.addAttribute("countDiagnoses",countDiagnoses);
        model.addAttribute("accepted",appointmentService.acceptedAppointment());
        model.addAttribute("declined",appointmentService.declinedAppointment());
        model.addAttribute("pending",appointmentService.pendingAppointment());

        return "admin/admin-dashboard";
    }
}