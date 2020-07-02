package lab2.healthcare.healthcaresystem.controller.patient;


import lab2.healthcare.healthcaresystem.models.Doctor;
import lab2.healthcare.healthcaresystem.models.Patient;
import lab2.healthcare.healthcaresystem.models.User;
import lab2.healthcare.healthcaresystem.service.PatientService;
import lab2.healthcare.healthcaresystem.service.UserService;
import lab2.healthcare.healthcaresystem.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PatientController {


    @Autowired
    private PatientService patientService;



    @RequestMapping("/patientData")
    public String patientData(Model model) {

        Patient patient = patientService.currentPatient();
        model.addAttribute("patient", patient);

        return "patient/patient-data";
    }

    @RequestMapping(value = {"/registerPatientData"}, method = RequestMethod.POST)
    public String insertPatientData(@ModelAttribute("patient") Patient patient) {
        patientService.savePatientData(patient);
        return "patient/patient";
    }





}
