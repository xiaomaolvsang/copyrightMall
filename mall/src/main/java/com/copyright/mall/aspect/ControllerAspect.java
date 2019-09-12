package com.copyright.mall.aspect;

import com.alibaba.fastjson.JSON;
import com.copyright.mall.message.ApiResult;
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
            Long time = System.currentTimeMillis();
            LOGGER.info("运行开始");
            Object obj = pjp.proceed();
            LOGGER.info("运行结束:"+(System.currentTimeMillis() - time));
            return obj;
        } catch (Throwable ex) {

            // 打印错误日志
            LOGGER.error("controller异常,url:{"+getUri()+"} reqMsg:{"+JSON.toJSONString(pjp.getArgs())+"},ex:{"+ex+"}");

            ApiResult apiResult = new ApiResult();
            apiResult.setErrorCode(-1);
            apiResult.setErrorMessage("后台处理异常");
            return apiResult;
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
