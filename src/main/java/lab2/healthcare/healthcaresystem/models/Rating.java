package lab2.healthcare.healthcaresystem.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "rating")
@Getter
@Setter
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRating")
    private Long idRating;

    private Date date;

    private String feedback;


    private double ratingValue;

    @ManyToOne
    @JoinColumn(name = "idpatient")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "iddoctor")
    private Doctor doctor;

    public Rating() {
    }

    @Override
    public String toString() {
        return "";
    }
}
