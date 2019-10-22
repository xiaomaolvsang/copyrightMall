package com.copyright.mall.util;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;

import java.util.Collection;
import java.util.List;

/**
 * The type Bean mapper utils.
 *
 * @author zhangyc
 */
public class BeanMapperUtils {

    private static Mapper MAPPER = DozerBeanMapperBuilder.buildDefault();

    /**
     * Map t.
     *
     * @param <T>              the type parameter
     * @param source           the source
     * @param destinationClass the destination class
     * @return the t
     */
    public static <T> T map(Object source, Class<T> destinationClass) {
        if (source == null) {
            return null;
        }
        return MAPPER.map(source, destinationClass);
    }

    /**
     * Map.
     *
     * @param source      the source
     * @param destination the destination
     */
    public static void map(Object source, Object destination) {
        MAPPER.map(source, destination);
    }

    /**
     * Map list list.
     *
     * @param <T>              the type parameter
     * @param sourceList       the source list
     * @param destinationClass the destination class
     * @return the list
     */
    public static <T> List<T> mapList(Collection sourceList, Class<T> destinationClass) {

        if (sourceList == null || CollectionUtils.isEmpty(sourceList)) {
            return Lists.newArrayListWithCapacity(0);
        }

        List<T> destinationList = Lists.newArrayListWithExpectedSize(sourceList.size());
        for (Object sourceObject : sourceList) {
            destinationList.add(MAPPER.map(sourceObject, destinationClass));
        }
        return destinationList;
    }
}