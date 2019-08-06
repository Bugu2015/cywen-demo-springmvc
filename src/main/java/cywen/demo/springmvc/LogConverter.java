package cywen.demo.springmvc;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.pattern.ConverterKeys;
import org.apache.logging.log4j.core.pattern.LogEventPatternConverter;
import org.apache.logging.log4j.core.pattern.PatternConverter;
import org.springframework.util.StringUtils;

@ConverterKeys({"traceId"})
@Plugin(name = "logConverter", category = PatternConverter.CATEGORY)
public class LogConverter extends LogEventPatternConverter {

    private static LogConverter logConverter = new LogConverter();

    public static LogConverter newInstance(final String[] options){
        return logConverter;
    }

    private LogConverter() {
        super("id", "id");
    }

    @Override
    public void format(LogEvent logEvent, StringBuilder stringBuilder) {
        String traceId = MyContext.traceId.get();
        stringBuilder.append(StringUtils.hasText(traceId) ? traceId : "");
    }
}
