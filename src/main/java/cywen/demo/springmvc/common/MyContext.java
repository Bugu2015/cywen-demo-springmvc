package cywen.demo.springmvc.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

public class MyContext {

    private final static Log log = LogFactory.getLog(MyContext.class);

    private final static ObjectMapper objectMapper = new ObjectMapper();

    public final static InheritableThreadLocal<String> traceId = new InheritableThreadLocal<>();

    public final static InheritableThreadLocal<Map> paramContext = new InheritableThreadLocal<>();

    public final static InheritableThreadLocal<Map> headerContext = new InheritableThreadLocal<>();

    public static void buildContext(HttpServletRequest request, HttpServletResponse response){
        Map<String, String> param = new HashMap<>(16);
        if (Objects.nonNull(request.getContentType())
                && request.getContentType().contains("json")) {
            try {
                String jsonStr = IOUtils.toString(request.getInputStream(), Charset.defaultCharset());
                Map map = objectMapper.readValue(jsonStr, HashMap.class);
                for (Object key:map.keySet()) {
                    param.put((String) key, (String) map.get(key));
                }
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        } else {
            Enumeration<String> parameterNames = request.getParameterNames();
            while (parameterNames.hasMoreElements()) {
                String key = parameterNames.nextElement();
                param.put(key, request.getParameter(key));
            }
        }
        MyContext.paramContext.set(param);

        // save header
        Map<String, String> headers = new HashMap<>(32);
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            headers.put(key, request.getHeader(key));
        }
        MyContext.headerContext.set(headers);

        // save trace id
        String traceId = request.getParameter("_trace");
        if (!StringUtils.hasText(traceId)) {
            traceId = UUID.randomUUID().toString();
        }
        MyContext.traceId.set(traceId);
    }

    public static String getRequestParam(){
        return toJSONString(paramContext.get());
    }

    public static String getHeaderParam(){
        return toJSONString(headerContext.get());
    }

    private static String toJSONString(Object object){
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
            return "";
        }
    }

}
