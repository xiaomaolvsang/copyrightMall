package com.copyright.mall.domain.dto.copyright;

import com.alibaba.fastjson.JSON;
import com.copyright.mall.util.BaseUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @author zhangyuchen
 */
@Data
@ApiModel
public class TimeLineDTO {

    List<TimeLineDTO.TimelineItem> items;

    @Data
    @ApiModel
    public static class TimelineItem{
        @ApiModelProperty(value = "创建时间",required = true)
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
        private Date time;

        @ApiModelProperty(value = "事件",required = true)
        private String event;
    }

    public void appendItem(TimelineItem timelineItem){
        if(items == null){
            items = Lists.newArrayList();
        }
        items.add(timelineItem);
    }

    public String toBaseString(){
        return BaseUtil.encode(JSON.toJSONString(this));
    }

    public static TimeLineDTO fromBaseStr(String baseStr){
        return JSON.parseObject(BaseUtil.decode(baseStr),TimeLineDTO.class);
    }

    public static String fromBaseStrToJSON(String baseStr){
        return BaseUtil.decode(baseStr);
    }
}
