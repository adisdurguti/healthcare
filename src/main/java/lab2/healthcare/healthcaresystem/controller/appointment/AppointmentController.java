package lab2.healthcare.healthcaresystem.controller.appointment;

import lab2.healthcare.healthcaresystem.models.*;
import lab2.healthcare.healthcaresystem.repository.DoctorRepository;
import lab2.healthcare.healthcaresystem.repository.PatientRepository;
import lab2.healthcare.healthcaresystem.service.AppointmentServiceImpl;
import lab2.healthcare.healthcaresystem.service.DoctorService;
import lab2.healthcare.healthcaresystem.service.PatientService;
import lab2.healthcare.healthcaresystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.util.List;

@Controller
public class AppointmentController {
    //patient should see the list of doctors that are available
    public Doctor doctorSelected;
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
    @Qualifier("doctorRepository")
    @Autowired
    DoctorRepository doctorRepository;

    @RequestMapping(value = "/createAppointment/{id}", method = RequestMethod.GET)
    public ModelAndView createAppointment(@PathVariable(name = "id") Long id) {

   /*   LocalDate localDate = new LocalDate.now();
        String currentDateString = currentDate.toString();*/
        doctorSelected = doctorService.get(id);
        Appointment appointment = new Appointment();
        ModelAndView mav = new ModelAndView("appointment/createAppointment");
        appointment.setDoctor(doctorSelected);
        Patient patient = patientService.currentPatient();

        if (patient == null) {
            patient = new Patient();
            mav.addObject("patient", patient);
        } else {
            mav.addObject("patient", patient);
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

        List<Doctor> listDoctors = doctorService.listAll();
        Patient patient = patientService.currentPatient();

        if (patient == null) {
            patient = new Patient();
            model.addAttribute("patient", patient);
        } else {
            model.addAttribute("patient", patient);
        }
        model.addAttribute("listDoctors", listDoctors);
        return "appointment/listOfDoctors";
    }

    @RequestMapping("/bookappointment")
    public String bookAppointment(Model model) {

        Patient patient = patientService.currentPatient();

        if (patient == null) {
            patient = new Patient();
            model.addAttribute("patient", patient);
        } else {
            model.addAttribute("patient", patient);
        }

        return "appointment/specializations";
    }

    @RequestMapping("/listCardiologs")
    public String listCardiologs(Model model) {

        List<Doctor> listCardiologs = doctorRepository.findAllBySpecialization("Cardiology");
        Patient patient = patientService.currentPatient();

        if (patient == null) {
            patient = new Patient();
            model.addAttribute("patient", patient);
        } else {
            model.addAttribute("patient", patient);
        }
        model.addAttribute("listCardiologs", listCardiologs);
        return "appointment/listCardiologs";
    }

    @RequestMapping("/listPediatrics")
    public String listPediatrics(Model model) {

        List<Doctor> listPediatrics = doctorRepository.findAllBySpecialization("Pediatrics");
        Patient patient = patientService.currentPatient();

        if (patient == null) {
            patient = new Patient();
            model.addAttribute("patient", patient);
        } else {
            model.addAttribute("patient", patient);
        }
        model.addAttribute("listPediatrics", listPediatrics);
        return "appointment/listPediatrics";
    }

    @RequestMapping("/listVirology")
    public String listVirology(Model model) {

        List<Doctor> listVirology = doctorRepository.findAllBySpecialization("Virology");
        Patient patient = patientService.currentPatient();

        if (patient == null) {
            patient = new Patient();
            model.addAttribute("patient", patient);
        } else {
            model.addAttribute("patient", patient);
        }
        model.addAttribute("listVirology", listVirology);
        return "appointment/listVirology";
    }

    @RequestMapping("/listRadiology")
    public String listRadiology(Model model) {

        List<Doctor> listRadiology = doctorRepository.findAllBySpecialization("Radiology");
        Patient patient = patientService.currentPatient();

        if (patient == null) {
            patient = new Patient();
            model.addAttribute("patient", patient);
        } else {
            model.addAttribute("patient", patient);
        }
        model.addAttribute("listRadiology", listRadiology);
        return "appointment/listRadiology";
    }

    @RequestMapping("/listNeurology")
    public String listNeurology(Model model) {

        List<Doctor> listNeurology = doctorRepository.findAllBySpecialization("Neurology");
        Patient patient = patientService.currentPatient();

        if (patient == null) {
            patient = new Patient();
            model.addAttribute("patient", patient);
        } else {
            model.addAttribute("patient", patient);
        }
        model.addAttribute("listNeurology", listNeurology);
        return "appointment/listNeurology";
    }

    @RequestMapping("/listOrthopedics")
    public String listOrthopedics(Model model) {

        List<Doctor> listOrthopedics = doctorRepository.findAllBySpecialization("Orthopedics");
        Patient patient = patientService.currentPatient();

        if (patient == null) {
            patient = new Patient();
            model.addAttribute("patient", patient);
        } else {
            model.addAttribute("patient", patient);
        }
        model.addAttribute("listOrthopedics", listOrthopedics);
        return "appointment/listOrthopedics";
    }

    @RequestMapping("/listPsychiatry")
    public String listPsychiatry(Model model) {

        List<Doctor> listPsychiatry = doctorRepository.findAllBySpecialization("Psychiatry");
        Patient patient = patientService.currentPatient();

        if (patient == null) {
            patient = new Patient();
            model.addAttribute("patient", patient);
        } else {
            model.addAttribute("patient", patient);
        }
        model.addAttribute("listPsychiatry", listPsychiatry);
        return "appointment/listPsychiatry";
    }

    @RequestMapping("/listObsterics")
    public String listObsterics(Model model) {

        List<Doctor> listObsterics = doctorRepository.findAllBySpecialization("Obsterics");
        Patient patient = patientService.currentPatient();

        if (patient == null) {
            patient = new Patient();
            model.addAttribute("patient", patient);
        } else {
            model.addAttribute("patient", patient);
        }
        model.addAttribute("listObsterics", listObsterics);
        return "appointment/listObsterics";
    }

    @RequestMapping("/listStomatology")
    public String listStomatology(Model model) {

        List<Doctor> listStomatology = doctorRepository.findAllBySpecialization("Stomatology");
        Patient patient = patientService.currentPatient();

        if (patient == null) {
            patient = new Patient();
            model.addAttribute("patient", patient);
        } else {
            model.addAttribute("patient", patient);
        }
        model.addAttribute("listStomatology", listStomatology);
        return "appointment/listStomatology";
    }

    @RequestMapping("/listDermatology")
    public String listDermatology(Model model) {

        List<Doctor> listDermatology = doctorRepository.findAllBySpecialization("Dermatology");
        Patient patient = patientService.currentPatient();

        if (patient == null) {
            patient = new Patient();
            model.addAttribute("patient", patient);
        } else {
            model.addAttribute("patient", patient);
        }
        model.addAttribute("listDermatology", listDermatology);
        return "appointment/listDermatology";
    }

    @RequestMapping("/listOphthalmology")
    public String listOphthalmology(Model model) {

        List<Doctor> listOphthalmology = doctorRepository.findAllBySpecialization("Ophthalmology");
        Patient patient = patientService.currentPatient();

        if (patient == null) {
            patient = new Patient();
            model.addAttribute("patient", patient);
        } else {
            model.addAttribute("patient", patient);
        }
        model.addAttribute("listOphthalmology", listOphthalmology);
        return "appointment/listOphthalmology";
    }

    @RequestMapping("/listOncology")
    public String listOncology(Model model) {

        List<Doctor> listOncology = doctorRepository.findAllBySpecialization("Oncology");
        Patient patient = patientService.currentPatient();

        if (patient == null) {
            patient = new Patient();
            model.addAttribute("patient", patient);
        } else {
            model.addAttribute("patient", patient);
        }
        model.addAttribute("listOncology", listOncology);
        return "appointment/listOncology";
    }

    @RequestMapping("/listPlasticSurgery")
    public String listPlasticSurgery(Model model) {

        List<Doctor> listPlasticSurgery = doctorRepository.findAllBySpecialization("Plastic Surgery");
        Patient patient = patientService.currentPatient();

        if (patient == null) {
            patient = new Patient();
            model.addAttribute("patient", patient);
        } else {
            model.addAttribute("patient", patient);
        }
        model.addAttribute("listPlasticSurgery", listPlasticSurgery);
        return "appointment/listPlasticSurgery";
    }


    @RequestMapping("/list")
    public String viewListOfAppointment(Model model) {
        User user = userService.currentUser();
        List<Appointment> list = appointmentService.list();
        model.addAttribute("appointmentList", list);
        model.addAttribute("user", user);
        return "admin/admin-appointments";
    }

    @RequestMapping("/doctorAppointments")
    public String viewDoctorAppointments(Model model) {

        Doctor doctor = doctorService.currentDoctor();

        List<Appointment> appointments = appointmentService.getAppointmentsByDoctorId(doctor);
        model.addAttribute("listAppointments", appointments);

        if (doctor == null) {
            doctor = new Doctor();
            model.addAttribute("doctor", doctor);
        } else {
            model.addAttribute("doctor", doctor);
        }

        return "doctor/appointment-list";
    }

    @RequestMapping("/patientAppointments")
    public String viewPatientAppointments(Model model) {

        Patient patient = patientService.currentPatient();

        List<Appointment> appointments = appointmentService.getAppointmentsByPatientId(patient);
        model.addAttribute("listAppointments", appointments);
        if (patient == null) {
            patient = new Patient();
            model.addAttribute("patient", patient);
        } else {
            model.addAttribute("patient", patient);
        }


        return "patient/appointment-list";
    }

    @RequestMapping(value = "/acceptAppointment/{id}", method = RequestMethod.GET)
    public String acceptAppointment(@PathVariable(name = "id") Long id, Model model) {

        Appointment appointmentDB = new Appointment();
        appointmentDB = appointmentService.get(id);
        if (appointmentDB.getStatus().equals(AppointmentStatusEnum.PENDING)) {
            appointmentDB.setDoctor(appointmentDB.getDoctor());
            appointmentDB.setDate(appointmentDB.getDate());
            appointmentDB.setPatient(appointmentDB.getPatient());
            appointmentDB.setDescription(appointmentDB.getDescription());
            appointmentDB.setStatus(AppointmentStatusEnum.ACCEPTED);
            appointmentService.save(appointmentDB);
        } else {
            System.out.println("cant do it");
        }

        Doctor doctor = doctorService.currentDoctor();

        if (doctor == null) {
            doctor = new Doctor();
            model.addAttribute("doctor", doctor);
        } else {
            model.addAttribute("doctor", doctor);
        }

        List<Appointment> appointments = appointmentService.getAppointmentsByDoctorId(doctor);
        model.addAttribute("listAppointments", appointments);

        return "doctor/appointment-list";
    }


    @RequestMapping(value = "/declineAppointment/{id}", method = RequestMethod.GET)
    public String declineAppointment(@PathVariable(name = "id") Long id, Model model) {

        Appointment appointmentDB = new Appointment();

        appointmentDB = appointmentService.get(id);
        if (appointmentDB.getStatus().equals(AppointmentStatusEnum.PENDING)) {
            appointmentDB.setDoctor(appointmentDB.getDoctor());
            appointmentDB.setDate(appointmentDB.getDate());
            appointmentDB.setPatient(appointmentDB.getPatient());
            appointmentDB.setDescription(appointmentDB.getDescription());
            appointmentDB.setStatus(AppointmentStatusEnum.CANCELED);
            appointmentService.save(appointmentDB);
        } else {
            System.out.println("cant do it");
        }

        Doctor doctor = doctorService.currentDoctor();

        if (doctor == null) {
            doctor = new Doctor();
            model.addAttribute("doctor", doctor);
        } else {
            model.addAttribute("doctor", doctor);
        }

        List<Appointment> appointments = appointmentService.getAppointmentsByDoctorId(doctor);
        model.addAttribute("listAppointments", appointments);

        return "doctor/appointment-list";
    }
}
