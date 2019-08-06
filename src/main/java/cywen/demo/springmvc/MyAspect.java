package cywen.demo.springmvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Aspect
@Component
public class MyAspect {

    private static final Log log = LogFactory.getLog(MyAspect.class);

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Pointcut("execution(public * cywen.demo.springmvc.controller.*Controller.*(..))")
    public void declareJoinPointExpression(){}

    @Before("@annotation(obj)")
    public void before(JoinPoint joinPoint, PreventRepeat obj){
        String methodName = joinPoint.getSignature().getName();
        // 校验是否重复提交
    }

    @Around(value="declareJoinPointExpression()")
    public Object aroundMethod(ProceedingJoinPoint point){
        Object result = new Object();
        try {
            result = point.proceed();
        } catch (Throwable e) {
            result = BaseResult.buildFailure("system error; try again");
        } finally {
            log.info("RESPONSE:" + toJSONString(result));
        }
        return result;
    }

    private String toJSONString(Object object){
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
            return "";
        }
    }

}
