package com.copyright.mall.aspect;

import com.alibaba.fastjson.JSON;
import com.copyright.mall.util.wrapper.WrapMapper;
import com.google.common.base.Stopwatch;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * ControllerAspect
 *
 * @author lijian
 * @version 1.0
 * @date 2019/9/12 5:34 下午
 */
@Aspect
@Component
public class ControllerAspect {

    private static final Logger LOGGER =  Logger.getLogger(ControllerAspect.class);

    @Around("@annotation(controllerErro)")
    public Object around(ProceedingJoinPoint pjp, ControllerErro controllerErro)throws Throwable{
        try {

            // 执行业务操作
            Stopwatch stopwatch = Stopwatch.createStarted();
            LOGGER.info("运行开始");
            Object obj = pjp.proceed();
            LOGGER.info("运行结束:"+stopwatch.elapsed(TimeUnit.MILLISECONDS));
            return obj;
        } catch (Throwable ex) {

            // 打印错误日志
            LOGGER.error("controller异常,url:{"+getUri()+"} reqMsg:{"+JSON.toJSONString(pjp.getArgs())+"},ex:{"+ex+"}");
            return WrapMapper.error("后台处理异常");
        }
    }

    private String getUri() {

        try {
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
            HttpServletRequest request = servletRequestAttributes.getRequest();

            String uri = request.getRequestURI();

            if (StringUtils.isEmpty(uri)) {
                return "";
            }
            return uri;
        } catch (Exception ex) {
            // ignore
        }
        return "";
    }
}
