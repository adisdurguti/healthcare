package lab2.healthcare.healthcaresystem.aspect;

import lab2.healthcare.healthcaresystem.models.Doctor;
import lab2.healthcare.healthcaresystem.models.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.support.BindingAwareModelMap;

/**
 * @author Adis Durguti
 */
@Aspect
@Component
public class DoctorServiceAspect {

    private static final Logger logger = LoggerFactory.getLogger(DoctorServiceAspect.class);

    @Before(value = "execution(* lab2.healthcare.healthcaresystem.controller.doctor..*(..))")
    public void beforeAdvice(JoinPoint joinPoint) {
        logger.info("Before, {}", joinPoint.getArgs());
    }

    @After(value ="execution(* lab2.healthcare.healthcaresystem.controller.doctor..*(..))")
    public void afterAdvice(JoinPoint joinPoint) {
        BindingAwareModelMap map = (BindingAwareModelMap) joinPoint.getArgs()[0];
        Doctor doctor = (Doctor) map.get("doctor");
        logger.info("After, {}", doctor.getFirstName());
        logger.info("Arguments, {}", joinPoint.getArgs());
        logger.info("Signature, {}", joinPoint.getSignature());
    }
}
