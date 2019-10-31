package com.copyright.mall.util.wrapper;

import java.util.Collection;
import java.util.Map;

/**
 * @author : zhangyuchen
 * @date : 2019/10/22 14:17
 */
public class WrapMapper {
    private WrapMapper() {
    }

    public static <E> Wrapper<E> wrap(int code, String message, E o) {
        return new Wrapper(code, message, o);
    }

    public static <E> Wrapper<E> wrap(int code, String message) {
        return wrap(code, message,null);
    }

    public static <E> Wrapper<E> wrap(int code) {
        return wrap(code, (String)null);
    }

    public static <E> Wrapper<E> wrap(Exception e) {
        return new Wrapper(500, e.getMessage());
    }

    public static <E> E unWrap(Wrapper<E> wrapper) {
        return wrapper.getResult();
    }

    public static <E> Wrapper<E> illegalArgument() {
        return wrap(100, "参数非法");
    }

    public static <E> Wrapper<E> error() {
        return wrap(500, "操作失败");
    }

    public static <E> Wrapper<E> error(String message) {
        return wrap(500, isEmpty(message) ? "操作失败" : message);
    }

    public static <E> Wrapper<E> ok() {
        return new Wrapper();
    }

    public static <E> Wrapper<E> ok(E o) {
        return new Wrapper(200, "操作成功", o);
    }

    public static <T> Wrapper<T> handleResult(T result) {
        return handleResult(result, (String)null);
    }

    public static <E> Wrapper<E> handleResult(E result, String message) {
        boolean flag = isFlag(result);
        return flag ? wrap(200, "操作成功", result) : wrap(500, isEmpty(message) ? "操作失败" : message, result);
    }

    private static boolean isFlag(Object result) {
        boolean flag = false;
        if (result instanceof Integer) {
            flag = (Integer)result > 0;
        } else if (result instanceof Boolean) {
            flag = (Boolean)result;
        } else if (result instanceof String) {
            flag = ((String)result).length() != 0;
        } else if (result instanceof Collection) {
            flag = !((Collection)result).isEmpty();
        } else if (result instanceof Map) {
            flag = ((Map)result).size() != 0;
        }

        return flag;
    }

    private static boolean isEmpty(String value) {
        return value == null || value.length() == 0;
    }
}
