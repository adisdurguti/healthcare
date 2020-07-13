package lab2.healthcare.healthcaresystem.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "doctor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    public String avgRatings() {
        double totali = 0;
        double iterimi = 0;

        Set<Double> ratingValues = new HashSet<>();

        for (Rating rating : getRatings()) {
            ratingValues.add(rating.getRatingValue());
            iterimi++;
            totali += rating.getRatingValue();
        }
        return totali / iterimi + "/5";
    }
}
