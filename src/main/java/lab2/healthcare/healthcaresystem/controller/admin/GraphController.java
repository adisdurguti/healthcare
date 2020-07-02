package lab2.healthcare.healthcaresystem.controller.admin;

import lab2.healthcare.healthcaresystem.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class GraphController {

    @Autowired
    AppointmentService appointmentService;


    @GetMapping("/appointmentGraph")
    public String appointmentGraph(Model model) {

        Map<String,Integer> appointmentGraph = new LinkedHashMap<>();

        appointmentGraph.put("Accepted",appointmentService.acceptedAppointment());
        appointmentGraph.put("Declined",appointmentService.declinedAppointment());
        appointmentGraph.put("Pending",appointmentService.pendingAppointment());

        model.addAttribute("appointmentGraph",appointmentGraph);

        return "appointmentGraph";
    }

}