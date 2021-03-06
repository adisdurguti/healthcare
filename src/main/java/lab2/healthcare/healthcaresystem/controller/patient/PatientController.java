package lab2.healthcare.healthcaresystem.controller.patient;

import lab2.healthcare.healthcaresystem.models.Patient;
import lab2.healthcare.healthcaresystem.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class PatientController {

    @Autowired
    private PatientService patientService;

    @RequestMapping("/patientData")
    public String patientData(Model model) {

        Patient patient = patientService.currentPatient();
        if (patient == null) {
            patient = new Patient();
            model.addAttribute("patient", patient);
        } else {
            model.addAttribute("patient", patient);
        }
        model.addAttribute("patient", patient);
        return "patient/patient-data";
    }

    @RequestMapping(value = {"/registerPatientData"}, method = RequestMethod.POST)
    public String insertPatientData(@ModelAttribute("patient") @Valid Patient patient, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "patient/patient-data";
        } else {
            patientService.savePatientData(patient);
            return "patient/patient";
        }
    }
}
