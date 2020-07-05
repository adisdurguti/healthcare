package lab2.healthcare.healthcaresystem.controller.admin;

import lab2.healthcare.healthcaresystem.models.Doctor;
import lab2.healthcare.healthcaresystem.models.Patient;
import lab2.healthcare.healthcaresystem.models.User;
import lab2.healthcare.healthcaresystem.service.DoctorService;
import lab2.healthcare.healthcaresystem.service.PatientService;
import lab2.healthcare.healthcaresystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AdminController {


    @Autowired
    private UserService userService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private PatientService patientService;

    @RequestMapping("/registerDoctor")
    public String registerDoctor(Model model) {

        User user = userService.currentUser();
        model.addAttribute("user", user);

        return "admin/register-doctor";
    }
    @RequestMapping(value = {"/registerDoctorUser"}, method = RequestMethod.POST)
    public ModelAndView createUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView model = new ModelAndView();
        User userExists = userService.findUserByUsername(user.getUsername());

        if (userExists != null) {
            bindingResult.rejectValue("email", "error.user", "This email already exists");
        }
        if (bindingResult.hasErrors()) {
            model.setViewName("admin/register-doctor");
        } else {
            userService.saveUserDoctor(user);
            model.addObject("msg", "User has been registered successfully!");
            model.addObject("user", new User());
            model.setViewName("redirect:/admin");
        }
        return model;
    }

    @RequestMapping("/doctors")
    public String doctors(Model model) {

        User user = userService.currentUser();
        List<Doctor> listDoctors= doctorService.listAll();
        model.addAttribute("user", user);
        model.addAttribute("listDoctors",listDoctors);

        return "admin/list-doctors";
    }

    @RequestMapping("/patients")
    public String patients(Model model) {

        User user = userService.currentUser();
        List<Patient> listPatients= patientService.listAll();
        model.addAttribute("user", user);
        model.addAttribute("listPatients",listPatients);

        return "admin/list-patients";
    }
}
