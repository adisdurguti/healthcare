package lab2.healthcare.healthcaresystem.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "patient")
public class Patient
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpatient")
    private Long idpatient;

    @NotBlank
    @Size(min = 3, max = 100)
    private String firstName;

    @NotBlank
    @Size(min = 3,max = 100)
    private String lastName;

    @NotBlank
    @Size(min = 10,max = 100)
    private String address;

    @NotBlank
    @Size(min = 10 , max = 100)
    private String phone;

    @NotBlank
    @Size(min = 4,max = 50)
    private String country;

    @NotBlank
    @Size(min=4,max = 50)
    private String city;

    @NotBlank
    @Size(max = 1)
    private String sex;

    @OneToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
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
/* private long user_id;

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }*/

   /* @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
*/
   /* @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "users",
            joinColumns =
                    { @JoinColumn(name = "user_id", referencedColumnName = "id")})
    private User user;*/


    /*public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }*/

    public Patient(){}

    public Patient(Long idpatient, @NotBlank @Size(max = 100) String firstName, @NotBlank @Size(max = 100) String lastName, @NotBlank @Size(max = 100) String address, @NotBlank @Size(max = 100) String phone, @NotBlank @Size(max = 50) String country, @NotBlank @Size(max = 50) String city, @NotBlank @Size(max = 5) String sex, User user) {
        this.idpatient = idpatient;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.country = country;
        this.city = city;
        this.sex = sex;
        this.user = user;
    }

    public Long getIdpatient() {
        return idpatient;
    }

    public void setIdpatient(Long idpatient) {
        this.idpatient = idpatient;
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

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
