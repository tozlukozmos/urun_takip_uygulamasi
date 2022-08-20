package halicmobilya.urun_takip_uygulamasi.core.advice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


@Aspect
@Component
public class LoggingAdvice {
    private final Logger logger = LogManager.getLogger(LoggingAdvice.class);

    @Around("execution(* halicmobilya.urun_takip_uygulamasi.*.*.*.*(..)))")
    public Object applicationLogger(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();

        final StopWatch stopWatch = new StopWatch();

        String[] argsKey = methodSignature.getParameterNames();
        Object[] argsValue = pjp.getArgs();

        HashMap<String, Object> data = new HashMap();

        for (int index = 0; index < argsKey.length; index++){
            data.put(argsKey[index], argsValue[index]);
        }

        // before
        logger.info("method started "
                + methodSignature.getDeclaringType().getSimpleName() // Class Name
                + "." + methodSignature.getName() + " " // Method Name
                + "- arguments = " + data);

        // calculate method execution time
        stopWatch.start();
        Object result = pjp.proceed();
        stopWatch.stop();

        // after
        logger.info("method completed "
                + methodSignature.getDeclaringType().getSimpleName() // Class Name
                + "." + methodSignature.getName() + " " // Method Name
                + ":: " + stopWatch.getTotalTimeMillis() + " ms - " + "response = " + data);

        return result;
    }

}
