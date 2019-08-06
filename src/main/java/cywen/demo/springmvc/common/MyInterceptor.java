package cywen.demo.springmvc.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class MyInterceptor implements HandlerInterceptor {
    private static final Log log = LogFactory.getLog(MyInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        MyContext.buildContext(request, response);
        log.info("URI:" + request.getRequestURI());
        log.info("HEADER:" + MyContext.getHeaderParam());
        log.info("PARAMS:" + MyContext.getRequestParam());
        return true;
    }
}
