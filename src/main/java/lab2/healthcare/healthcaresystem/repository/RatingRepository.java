package lab2.healthcare.healthcaresystem.repository;

import lab2.healthcare.healthcaresystem.models.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RatingRepository extends JpaRepository<Rating,Long> {

}
