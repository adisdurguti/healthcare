package lab2.healthcare.healthcaresystem.service;

import lab2.healthcare.healthcaresystem.models.Rating;
import lab2.healthcare.healthcaresystem.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    public RatingRepository ratingRepository;

    @Override
    public Rating save(Rating rating) {
        return ratingRepository.save(rating);
    }
}
