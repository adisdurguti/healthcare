package lab2.healthcare.healthcaresystem.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table(name = "doctor")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddoctor")
    private Long iddoctor;

    @NotBlank
    @Size(min = 3,max = 100)
    private String firstName;

    @NotBlank
    @Size(min = 3,max = 100)
    private String lastName;

    @NotBlank
    @Size(min = 10,max = 100)
    private String address;

    @NotBlank
    @Size(min = 10,max = 100)
    private String phone;

    @NotBlank
    @Size(min = 4,max = 50)
    private String country;

    @NotBlank
    @Size(min = 4,max = 50)
    private String city;

    @NotBlank
    @Size(max = 1)
    private String sex;

    @NotBlank
    @Size(max = 50)
    private String specialization;


    @OneToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "doctor")
    private List<Rating> ratings;

    public Doctor() {

    }

    public Doctor(Long iddoctor, @NotBlank @Size(max = 100) String firstName, @NotBlank @Size(max = 100) String lastName, @NotBlank @Size(max = 100) String address, @NotBlank @Size(max = 100) String phone, @NotBlank @Size(max = 50) String country, @NotBlank @Size(max = 50) String city, @NotBlank @Size(max = 5) String sex, @NotBlank @Size(max = 50) String specialization, User user) {
        this.iddoctor = iddoctor;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.country = country;
        this.city = city;
        this.sex = sex;
        this.specialization = specialization;
        this.user = user;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getIddoctor() {
        return iddoctor;
    }

    public void setIddoctor(Long iddoctor) {
        this.iddoctor = iddoctor;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    @Override
    public String toString() {
        return firstName+" "+lastName;
    }

    public String avgRatings() {

        double totali=0;
        double iterimi=0;

       Set<Double> ratingValues = new HashSet<>();

       for (Rating rating : getRatings()) {
           ratingValues.add(rating.getRatingValue());
           iterimi++;
           totali += rating.getRatingValue();
       }
       return totali/iterimi + "/5";
   }
}
