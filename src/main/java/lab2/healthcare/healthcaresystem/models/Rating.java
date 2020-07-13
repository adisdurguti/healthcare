package lab2.healthcare.healthcaresystem.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "rating")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRating")
    private Long idRating;
    private Date date;
    @NotBlank
    @Size(min = 5, max = 100)
    private String feedback;
    @NotNull
    private double ratingValue;
    @ManyToOne
    @JoinColumn(name = "idpatient")
    private Patient patient;
    @ManyToOne
    @JoinColumn(name = "iddoctor")
    private Doctor doctor;

    @Override
    public String toString() {
        return "";
    }
}
