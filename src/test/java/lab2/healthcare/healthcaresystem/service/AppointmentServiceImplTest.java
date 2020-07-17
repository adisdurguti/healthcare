package lab2.healthcare.healthcaresystem.service;

import lab2.healthcare.healthcaresystem.models.*;
import lab2.healthcare.healthcaresystem.repository.AppointmentRepository;
import lab2.healthcare.healthcaresystem.repository.RoleRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.parameters.P;

import java.util.*;


import static org.mockito.Mockito.*;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
import static org.springframework.test.web.client.ExpectedCount.times;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class AppointmentServiceImplTest {

    @Mock
    AppointmentRepository appointmentRepository;

    @InjectMocks
    AppointmentServiceImpl appointmentServiceImpl;

    //@Qualifier("roleRepository")
    @Mock
    RoleRepository roleRepository;

    @Mock
    UserServiceImpl userService;


    public User setUpUserPatient() {
        Role rolePatient = roleRepository.findByName("ROLE_PATIENT");
        HashSet<Role> rolePatientSet = new HashSet<Role>(Arrays.asList(rolePatient));
        User user = new User();
        user.setId((long) 1);
        user.setUsername("drin");
        user.setEmail("drin@gmail.com");
        user.setPassword("12345678D");
        user.setAccountNonExpired(true);
        user.setActive(1);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        user.setRoles(rolePatientSet);
        return user;
    }

    public User setUpUserDoctor() {
        Role roleDoctor = roleRepository.findByName("ROLE_DOCTOR");
        HashSet<Role> roleDoctorSet = new HashSet<Role>(Arrays.asList(roleDoctor));
        User user = new User();
        user.setId((long) 1);
        user.setUsername("drin");
        user.setEmail("drin@gmail.com");
        user.setPassword("12345678D");
        user.setAccountNonExpired(true);
        user.setActive(1);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        user.setRoles(roleDoctorSet);
        return user;
    }


    public Patient setUpPatient() {
        Patient patient = new Patient();
        patient.setUser(setUpUserPatient());
        patient.setFirstName("patient");
        patient.setLastName("lastname");
        patient.setAddress("address");
        patient.setCity("city");
        patient.setCountry("country");
        patient.setPhone("123456");
        patient.setSex("M");
        return patient;
    }

    public Doctor setUpDoctor() {
        Doctor doctor = new Doctor();
        doctor.setUser(setUpUserDoctor());
        doctor.setFirstName("patient");
        doctor.setLastName("lastname");
        doctor.setAddress("address");
        doctor.setCity("city");
        doctor.setCountry("country");
        doctor.setPhone("123456");
        doctor.setSpecialization("Pediatrics");
        doctor.setSex("M");
        return doctor;
    }


    @Test
    public void listTest() {
        Date date = new Date();
        //create appointments
        List<Appointment> appointmentlist = new ArrayList<Appointment>();
        Appointment appointmentOne = new Appointment((long) 1, date, "09:00 - 10:00", "description1", AppointmentStatusEnum.PENDING, setUpPatient(), setUpDoctor());
        Appointment appointmentTwo = new Appointment((long) 2, date, "10:00 - 11:00", "description2", AppointmentStatusEnum.ACCEPTED, setUpPatient(), setUpDoctor());
        Appointment appointmentThree = new Appointment((long) 3, date, "11:00 - 12:00", "description3", AppointmentStatusEnum.CANCELED, setUpPatient(), setUpDoctor());
        //add appointments to list
        appointmentlist.add(appointmentOne);
        appointmentlist.add(appointmentTwo);
        appointmentlist.add(appointmentThree);

        when(appointmentRepository.findAll()).thenReturn(appointmentlist);
        //test method list
        List<Appointment> ExpectedAppointmentList = appointmentServiceImpl.list();

        Assert.assertEquals(ExpectedAppointmentList, appointmentlist);
        Assert.assertEquals(3, ExpectedAppointmentList.size());
        //verify that method find all is mocked at least one time
        verify(appointmentRepository, atLeast(1)).findAll();
    }


    @Test
    public void getAppointmentByIDTest() {
        Date date = new Date();
        Patient patient = setUpPatient();
        Doctor doctor = setUpDoctor();
        when(appointmentRepository.findById(1)).thenReturn(new Appointment((long) 1, date, "09:00 - 10:00", "description1", AppointmentStatusEnum.PENDING, patient,doctor));
        Appointment ExpectedAppointment = appointmentServiceImpl.get(1);
        Assert.assertNotNull(ExpectedAppointment);
        Assert.assertEquals("description1", ExpectedAppointment.getDescription());
        Assert.assertEquals("09:00 - 10:00", ExpectedAppointment.getTime());
        Assert.assertEquals(AppointmentStatusEnum.PENDING, ExpectedAppointment.getStatus());
    }


    @Test
    public void saveTest() {
        Date date = new Date();
        Appointment appointment = new Appointment((long) 1, date, "09:00 - 10:00", "description1", AppointmentStatusEnum.PENDING, setUpPatient(), setUpDoctor());
        appointmentServiceImpl.save(appointment);
        //verify that method save is mocked at least one time
        verify(appointmentRepository, atLeast(1)).save(appointment);
    }


}
