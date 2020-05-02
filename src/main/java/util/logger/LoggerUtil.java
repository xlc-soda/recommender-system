package util.logger;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import service.service.UserService;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LoggerUtil {

    private static final String TRACE_MARKER = "[TraceId:{0}] ";

    @Autowired
    private static UserService userService;

    public static String info(String sessionId, String msg) {
//        User user = userService.getUserOnline(sessionId);
//        int userId = user.getId();
        int userId = 0;
        Date date = new Date();
        JSONObject object = new JSONObject();
        object.put("id", userId);
        object.put("msg", msg);
        object.put("sendtime", date.getTime());
        return object.toJSONString();
    }

    /**
     * 生成trace的模板
     *
     * @param template
     * @param parameters
     * @return
     */
    private static String getMessage(String template, Object... parameters) {
        return template;
//        return MessageFormat.format(TRACE_MARKER, getTraceId()) + MessageFormat.format(template, parameters);
    }

    /**
     * <p>
     * 生成调试级别日志
     * </p>
     * <p>
     * 根据带参数的日志模板和参数集合，生成debug级别日志
     * 带参数的日志模板中以{数字}表示待替换为变量的日志点，如a={0}，表示用参数集合中index为0的参数替换{0}处
     * </p>
     *
     * @param logger     logger引用
     * @param template   带参数的日志模板
     * @param parameters 参数集合
     */
    private static void debug(Logger logger, String template, Object... parameters) {
        if (logger.isDebugEnabled()) {
            logger.debug(getMessage(template, parameters));
        }
    }

    /**
     * <p>
     * 生成调试级别日志
     * </p>
     * <p>
     * 根据带参数的日志模板和参数集合，生成info级别日志
     * 带参数的日志模板中以{数字}表示待替换为变量的日志点，如a={0}，表示用参数集合中index为0的参数替换{0}处
     * </p>
     *
     * @param logger     logger引用
     * @param template   带参数的日志模板
     * @param parameters 参数集合
     */
    private static void info(Logger logger, String template, Object... parameters) {
        if (logger.isInfoEnabled()) {
            logger.info(getMessage(template, parameters));
        }
    }

    /**
     * <p>
     * 生成调试级别日志
     * </p>
     * <p>
     * 根据带参数的日志模板和参数集合，生成warn级别日志
     * 带参数的日志模板中以{数字}表示待替换为变量的日志点，如a={0}，表示用参数集合中index为0的参数替换{0}处
     * </p>
     *
     * @param logger     logger引用
     * @param template   带参数的日志模板
     * @param parameters 参数集合
     */
    private static void warn(Logger logger, String template, Object... parameters) {
        logger.warn(getMessage(template, parameters));
    }

    /**
     * <p>
     * 生成调试级别日志
     * </p>
     * <p>
     * 根据带参数的日志模板和参数集合，生成error级别日志
     * 带参数的日志模板中以{数字}表示待替换为变量的日志点，如a={0}，表示用参数集合中index为0的参数替换{0}处
     * </p>
     *
     * @param logger     logger引用
     * @param template   带参数的日志模板
     * @param parameters 参数集合
     */
    private static void error(Logger logger, String template, Object... parameters) {
        logger.error(getMessage(template, parameters));
    }

    /**
     * <p>
     * exception日志
     * </p>
     * <p>
     * 根据带参数的日志模板和参数集合，生成warn级别日志
     * 带参数的日志模板中以{数字}表示待替换为变量的日志点，如a={0}，表示用参数集合中index为0的参数替换{0}处
     * </p>
     *
     * @param logger     logger引用
     * @param template   带参数的日志模板
     * @param parameters 参数集合
     */
    private static void warn(Throwable e, Logger logger, String template, Object... parameters) {
        logger.warn(getMessage(template, parameters), e);
    }

    /**
     * <p>
     * exception日志
     * </p>
     * <p>
     * 根据带参数的日志模板和参数集合，生成error级别日志
     * 带参数的日志模板中以{数字}表示待替换为变量的日志点，如a={0}，表示用参数集合中index为0的参数替换{0}处
     * </p>
     *
     * @param logger     logger引用
     * @param template   带参数的日志模板
     * @param parameters 参数集合
     */
    private static void error(Throwable e, Logger logger, String template, Object... parameters) {
        logger.error(getMessage(template, parameters), e);
    }

    /**
     * traceid
     *
     * @return
     */
    private static String getTraceId() {
        // 格式化对象
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String tempD = sdf.format(new Date());
        return tempD + ((int) (Math.random() * 900) + 100);
    }
}