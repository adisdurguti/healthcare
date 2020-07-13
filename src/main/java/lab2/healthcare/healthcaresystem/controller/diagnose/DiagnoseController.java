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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
public class DiagnoseController {

    public Appointment appointmentDiagnose;
    public Doctor doctorDiagnose;
    public Patient patientDiagnose;
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
        if (doctor == null) {
            doctor = new Doctor();
            mav.addObject("doctor", doctor);
        } else {
            mav.addObject("doctor", doctor);
        }

        return mav;
    }

    @RequestMapping(value = "/saveDiagnose", method = RequestMethod.POST)
    public ModelAndView saveDiagnose(@ModelAttribute("diagnose") @Valid Diagnose diagnose, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            ModelAndView model = new ModelAndView();
            Doctor doctor = doctorService.currentDoctor();

            if (doctor == null) {
                doctor = new Doctor();
                model.addObject("doctor", doctor);
            } else {
                model.addObject("doctor", doctor);
            }
            model.addObject("patientDiagnose", patientDiagnose);
            model.setViewName("doctor/add-diagnose");
            return model;

        } else {
            ModelAndView model = new ModelAndView();
            model.setViewName("redirect:/doctorAppointments");
            Date date = new Date();
            diagnose.setDateCreated(date);
            diagnose.setAppointment(appointmentDiagnose);
            diagnose.setDoctor(doctorDiagnose);
            diagnose.setPatient(patientDiagnose);
            diagnoseService.save(diagnose);

            Doctor doctor = doctorService.currentDoctor();
            List<Appointment> appointments = appointmentService.getAppointmentsByDoctorId(doctor);
            model.addObject("listAppointments", appointments);

            return model;
        }
    }

    @RequestMapping(value = "/viewDiagnose/{id}", method = RequestMethod.GET)
    public ModelAndView viewDiagnose(@PathVariable(name = "id") Long id) {

        Appointment appointment = new Appointment();
        appointment = appointmentService.get(id);
        Patient patient = patientService.currentPatient();
        Diagnose diagnose = diagnoseService.getDiagnoseByAppointment(appointment);
        ModelAndView mav = new ModelAndView("patient/view-diagnose");
        if (diagnose == null) {
            diagnose = new Diagnose();
            mav.addObject("diagnose", diagnose);
        } else {
            mav.addObject("diagnose", diagnose);
        }
        if (patient == null) {
            patient = new Patient();
            mav.addObject("patient", patient);
        }

        return mav;
    }
}