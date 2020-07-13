package lab2.healthcare.healthcaresystem.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "patient")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
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

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
