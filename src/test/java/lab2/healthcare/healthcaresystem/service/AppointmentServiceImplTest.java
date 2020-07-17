package lab2.healthcare.healthcaresystem.service;

import lab2.healthcare.healthcaresystem.models.*;
import lab2.healthcare.healthcaresystem.repository.AppointmentRepository;
import lab2.healthcare.healthcaresystem.repository.RoleRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

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






    @Test
    public void listTest(){
        Date date = new Date();
        //create role patient
        Role rolePatient = roleRepository.findByName("ROLE_PATIENT");
        HashSet<Role> rolePatientSet = new HashSet<Role>(Arrays.asList(rolePatient));
        //create user patient
        User userPatient = new User((long) 1,"drin","drin14dalipi@gmail.com","D12345678",1,rolePatientSet);
        //create patient
        Patient patient = new Patient((long) 1,"drin","dalipi","veterrnik","049123123","Kosove","Prishtine","M",userPatient);
        //create user doctor
        Role roleDoctor = roleRepository.findByName("ROLE_DOCTOR");
        HashSet<Role> roleDoctorSet = new HashSet<Role>(Arrays.asList(roleDoctor));
        //create user patient
        User userDoctor = new User((long) 1,"doki","doki@gmail.com","D12345678",1,roleDoctorSet);
        //list of ratings
        List<Rating> rating = new ArrayList<>();
        //create doctor
        Doctor doctor = new Doctor((long) 1,"doki","dokinjo","veterrnik","049123123","Kosove","Prishtine","M","ortoped",userDoctor,rating);
        //create appointments
        List<Appointment> appointmentlist = new ArrayList<Appointment>();
        Appointment appointmentOne = new Appointment((long) 1,date, "09:00 - 10:00", "description1", AppointmentStatusEnum.PENDING,patient,doctor);
        Appointment appointmentTwo = new Appointment((long) 2, date, "10:00 - 11:00", "description2",AppointmentStatusEnum.ACCEPTED,patient,doctor);
        Appointment appointmentThree = new Appointment((long) 3, date, "11:00 - 12:00", "description3",AppointmentStatusEnum.CANCELED,patient,doctor);
        //add appointments to list
        appointmentlist.add(appointmentOne);
        appointmentlist.add(appointmentTwo);
        appointmentlist.add(appointmentThree);

        when(appointmentRepository.findAll()).thenReturn(appointmentlist);
        //test method list
        List<Appointment> ExpectedAppointmentList = appointmentServiceImpl.list();

        Assert.assertEquals(ExpectedAppointmentList,appointmentlist);
        Assert.assertEquals(3, ExpectedAppointmentList.size());
        //verify that method find all is mocked at least one time
        verify(appointmentRepository, atLeast(1)).findAll();


    }


    @Test
    public void getAppointmentByIDTest()
    {
        //create role patient
        Role rolePatient = roleRepository.findByName("ROLE_PATIENT");
        HashSet<Role> rolePatientSet = new HashSet<Role>(Arrays.asList(rolePatient));
        //create user patient
        User userPatient = new User((long) 1,"drin","drin14dalipi@gmail.com","D12345678",1,rolePatientSet);
        //create patient
        Patient patient = new Patient((long) 1,"drin","dalipi","veterrnik","049123123","Kosove","Prishtine","M",userPatient);
        //create role doctor
        Role roleDoctor = roleRepository.findByName("ROLE_DOCTOR");
        HashSet<Role> roleDoctorSet = new HashSet<Role>(Arrays.asList(roleDoctor));
        //create user doctor
        User userDoctor = new User((long) 1,"doki","doki@gmail.com","D12345678",1,roleDoctorSet);
        //list of ratings
        List<Rating> rating = new ArrayList<>();
        Doctor doctor = new Doctor((long) 1,"doki","dokinjo","veterrnik","049123123","Kosove","Prishtine","M","ortoped",userDoctor,rating);
        //create test date
        Date date = new Date();

        when(appointmentRepository.findById(1)).thenReturn(new Appointment((long) 1,date, "09:00 - 10:00", "description1", AppointmentStatusEnum.PENDING,patient,doctor));

        Appointment ExpectedAppointment = appointmentServiceImpl.get(1);

        Assert.assertNotNull(ExpectedAppointment);

        Assert.assertEquals("description1", ExpectedAppointment.getDescription());
        Assert.assertEquals("09:00 - 10:00", ExpectedAppointment.getTime());
        Assert.assertEquals(AppointmentStatusEnum.PENDING, ExpectedAppointment.getStatus());
    }


    @Test
    public void saveTest()
    {

        //create role patient
        Role rolePatient = roleRepository.findByName("ROLE_PATIENT");
        HashSet<Role> rolePatientSet = new HashSet<Role>(Arrays.asList(rolePatient));
        //create user patient
        User userPatient = new User((long) 1,"drin","drin14dalipi@gmail.com","D12345678",1,rolePatientSet);
        //create patient
        Patient patient = new Patient((long) 1,"drin","dalipi","veterrnik","049123123","Kosove","Prishtine","M",userPatient);
        //create role doctor
        Role roleDoctor = roleRepository.findByName("ROLE_DOCTOR");
        HashSet<Role> roleDoctorSet = new HashSet<Role>(Arrays.asList(roleDoctor));
        //create user doctor
        User userDoctor = new User((long) 1,"doki","doki@gmail.com","D12345678",1,roleDoctorSet);
        //list of ratings
        List<Rating> rating = new ArrayList<>();
        Doctor doctor = new Doctor((long) 1,"doki","dokinjo","veterrnik","049123123","Kosove","Prishtine","M","ortoped",userDoctor,rating);
        //create test date

        //create user doctor

        Date date = new Date();

        Appointment appointment = new Appointment((long) 1,date, "09:00 - 10:00", "description1", AppointmentStatusEnum.PENDING,patient,doctor);

        appointmentServiceImpl.save(appointment);

        //verify that method save is mocked at least one time


        verify(appointmentRepository, atLeast(1)).save(appointment);

    }


}
