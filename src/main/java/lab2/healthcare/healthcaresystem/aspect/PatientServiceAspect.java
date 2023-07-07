package lab2.healthcare.healthcaresystem.aspect;

import lab2.healthcare.healthcaresystem.models.Patient;
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
public class PatientServiceAspect {
    private static final Logger logger = LoggerFactory.getLogger(AdminServiceAspect.class);

    @Before(value = "execution(* lab2.healthcare.healthcaresystem.controller.patient..*(..))")
    public void beforeAdvice(JoinPoint joinPoint) {
        logger.info("Before, {}", joinPoint.getArgs());
    }

    @After(value ="execution(* lab2.healthcare.healthcaresystem.controller.patient..*(..))")
    public void afterAdvice(JoinPoint joinPoint) {
        BindingAwareModelMap map = (BindingAwareModelMap) joinPoint.getArgs()[0];
        Patient patient = (Patient) map.get("patient");
        logger.info("After, {}", patient.getFirstName());
        logger.info("Arguments, {}", joinPoint.getArgs());
        logger.info("Signature, {}", joinPoint.getSignature());
    }
}
