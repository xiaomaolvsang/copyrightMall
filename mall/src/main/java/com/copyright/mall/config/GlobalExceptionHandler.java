package com.copyright.mall.config;

import com.copyright.mall.domain.exception.BusinessException;
import com.copyright.mall.util.wrapper.WrapMapper;
import com.copyright.mall.util.wrapper.Wrapper;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author : zhangyuchen
 * @date : 2019/11/18 10:53
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 绑定异常
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Wrapper<?> BindException(BindException e){
        return WrapMapper.wrap(400, e.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(",")));
    }


    /**
     * 参数非法异常.
     *
     * @param e the e
     * @return the wrapper
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Wrapper<?> illegalArgumentException(IllegalArgumentException e) {
        log.error("参数非法异常={}", e.getMessage());
        return WrapMapper.wrap(400, e.getMessage());
    }

    /**
     * 业务异常.
     *
     * @param e the e
     * @return the wrapper
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Wrapper<?> businessException(BusinessException e) {
        log.warn("业务异常={}", e.getMessage());
        return WrapMapper.wrap(e.getCode() == 0 ? Wrapper.ERROR_CODE : e.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object methodArgumentNotValidHandler(MethodArgumentNotValidException exception){
        //按需重新封装需要返回的错误信息
        List<String> paramValidationResults = Lists.newArrayList();
        //解析原错误信息，封装后返回，此处返回非法的字段名称，错误信息
        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
            paramValidationResults.add(error.getDefaultMessage());
        }
        return WrapMapper.wrap(Wrapper.ERROR_CODE, paramValidationResults.toString());
    }

    /**
     * 全局异常.
     *
     * @param e the e
     * @return the wrapper
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Wrapper<?> exception(Exception e) {
        String uuid = UUID.randomUUID().toString();
        log.error("全局异常信息 ex={}, uuid={}", e.getMessage(), uuid, e);
        return WrapMapper.error(e.getMessage());
    }





}
