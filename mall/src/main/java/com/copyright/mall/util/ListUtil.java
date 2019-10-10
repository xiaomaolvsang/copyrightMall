package com.copyright.mall.util;

import com.copyright.mall.bean.Item;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * ListCopy
 *
 * @author lijian
 * @version 1.0
 * @date 2019/10/10 5:29 下午
 */
public class ListUtil {

  public static List<?> listCopy(Class target, List<?> source) {
    List<Object> list = new ArrayList<>();
    source.forEach(t -> {
      try {
        Object obj = target.newInstance();
        BeanUtils.copyProperties(t,obj);
        list.add(obj);
      } catch (Exception e) {
        e.printStackTrace();
      }

    });
    return list;
  }
}
