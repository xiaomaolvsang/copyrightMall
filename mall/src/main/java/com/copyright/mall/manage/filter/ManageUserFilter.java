package com.copyright.mall.manage.filter;

import com.alibaba.fastjson.JSON;
import com.copyright.mall.service.JwtService;
import com.copyright.mall.util.UserUtils;
import com.copyright.mall.util.wrapper.WrapMapper;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebFilter(filterName = "manageUserFilter", urlPatterns = {"/manage/*"})
@Order(2)
public class ManageUserFilter implements Filter {

    @Resource
    private JwtService jwtService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("ManageUserFilter  init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        if (((HttpServletRequest) request).getRequestURL().toString().contains("/login")) {
            chain.doFilter(request, response);
            return;
        }
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
        httpServletResponse.setHeader("Access-Contro产品需求 \n" +
                "\n" +
                "【项目名称】：优惠感知优惠感知需求\n" +
                "\n" +
                "【项目说明】：\n" +
                "\n" +
                "【背景】：\n" +
                "\n" +
                "          优惠券目前在deal详情页没有展示，用户对抵用券的感知力度很低，进而优惠的整体核销率也比较低。希望可以增加用户对优惠的感知力度。\n" +
                "\n" +
                "【预期收益】：将优惠券吐出在deal详情页,加强用户感知\n" +
                "\n" +
                "【开发项】：到餐优惠券发放http接口\n" +
                "\n" +
                "【相关人员】：袁琼琼，张宇辰\n" +
                "\n" +
                "【项目排期】：\n" +
                "\n" +
                "            【开发自测】2PD\n" +
                "\n" +
                "            【联调】 2PD\n" +
                "\n" +
                "            【QA测试】暂无\n" +
                "\n" +
                "【项目资源】：4PD\n" +
                "\n" +
                "【本周进展】\n" +
                "\n" +
                "      批量领券接口上线完成   C端验收中\n" +
                "\n" +
                "      dbus缓存上线完成\n" +
                "\n" +
                "产品需求\n" +
                "\n" +
                "【项目名称】：金融券接入区域限制 【20200518】现金券增加核销门店限制&超期自动退\n" +
                "\n" +
                "项目说明】：\n" +
                "\n" +
                "【背景】：\n" +
                "\n" +
                "政府目前是按照到店核销口径结算营销费用，到餐团单业务本身属于预定类业务，团单过期时间可以超过政府结算时间，此问题会导致资损，目前业务券及通用券都是在用户下单时候改变用户团单截至时间，避免政府结算问题。希望现金券也支持此能力。\n" +
                "\n" +
                "政府券可以具备限制验券门店功能（疫情期间政府发券补贴当地用户去消费，比如深圳龙华区政府，政府肯定只希望用户消费本地商户的商品。但是连锁店的团购deal通常是购买后可以在全国验券，这不符合政府补贴预期。目前业务券及通用券都是在用户到店核销环节限制用户使用，避免政府结算问题。希望现金券也支持此能力。\n" +
                "\n" +
                "【预期收益】：\n" +
                "\n" +
                "避免团单超期核销引起资损问题\n" +
                "\n" +
                "避免跨区核销，产生资损\n" +
                "\n" +
                "【开发项】：金融券接入验券限制可核销区域\n" +
                "\n" +
                "【相关人员】：袁琼琼，张宇辰\n" +
                "\n" +
                "项目排期】：\n" +
                "\n" +
                "            【开发自测】2PD\n" +
                "\n" +
                "            【联调】 1PD\n" +
                "\n" +
                "            【QA测试】暂无\n" +
                "\n" +
                "【项目资源】：3PD\n" +
                "\n" +
                "【本周进展】\n" +
                "\n" +
                "      开发完成 已提测\n" +
                "\n" +
                "技术需求\n" +
                "\n" +
                "【项目名称】：FastJSON inf-bom升级\n" +
                "\n" +
                "【项目说明】：\n" +
                "\n" +
                "【背景】：\n" +
                "\n" +
                "          服务使用存在风险的SDK版本升级 【20200519-高危】 Fastjson远程代码执行漏洞 \n" +
                "\n" +
                "【预期收益】：修复安全漏洞\n" +
                "\n" +
                "【开发项】：\n" +
                "\n" +
                "【相关人员】：张宇辰\n" +
                "\n" +
                "【项目排期】：\n" +
                "\n" +
                "            【开发】2PD\n" +
                "\n" +
                "            【联调】 暂无\n" +
                "\n" +
                "            【QA测试】暂无\n" +
                "\n" +
                "【项目资源】：2PD\n" +
                "\n" +
                "【本周进展】\n" +
                "\n" +
                "     com.sankuai.web.campaign.showcard \n" +
                "\n" +
                "    com.sankuai.web.campaign.point\n" +
                "\n" +
                "    com.sankuai.meishi.merchant.promotion\n" +
                "\n" +
                "升级完成\n" +
                "\n" +
                "资源统计\n" +
                "\n" +
                "人日\n" +
                "\n" +
                "占比\n" +
                "\n" +
                "业务重点项目\n" +
                "\n" +
                "0\n" +
                "\n" +
                "50%\n" +
                "\n" +
                "新平台建设\n" +
                "\n" +
                "0\n" +
                "\n" +
                "0%\n" +
                "\n" +
                "日常产品需求\n" +
                "\n" +
                "3\n" +
                "\n" +
                "60%\n" +
                "\n" +
                "日常技术需求\n" +
                "\n" +
                "2\n" +
                "\n" +
                "40%\n" +
                "\n" +
                "迭代会、沟通会\n" +
                "\n" +
                "0\n" +
                "\n" +
                "40%\n" +
                "\n" +
                "case处理\n" +
                "\n" +
                "0\n" +
                "\n" +
                "0\n" +
                "\n" +
                "休假\n" +
                "\n" +
                "0\n" +
                "\n" +
                "0\n" +
                "\n" +
                "总计\n" +
                "\n" +
                "5\n" +
                "\n" +
                "100%\n" +
                "\n" +
                "认知迭代\n" +
                "\n" +
                "  \n" +
                "\n" +
                "其他工作\n" +
                "\n" +
                "思考和收获\n" +
                "\n" +
                "个人周报wiki\n" +
                "\n" +
                "工作周报-[到店-平台技术部-营销平台研发组-到餐研发组] 20200601 -20200605 张宇辰\n" +
                "\n" +
                "\n" +
                "\nl-Allow-Headers", "x-requested-with,content-type,X-MANAGE-TOKEN");

        // 如果是option请求，直接返回200
        if (httpServletRequest.getMethod().equals(HttpMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            return;
        }


        String token = httpServletRequest.getHeader("X-MANAGE-TOKEN");
        if (StringUtils.isBlank(token)) {
            httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            httpServletResponse.getOutputStream().println(JSON.toJSONString(WrapMapper.wrap(HttpStatus.UNAUTHORIZED.value()
                    , "Invalid authentication")));
            httpServletResponse.getOutputStream().close();
            return;
        }

        String userId = null;
        try {
            userId = jwtService.getClaimFromToken(token).getSubject();
        } catch (Exception e) {
            httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            httpServletResponse.getOutputStream().println(JSON.toJSONString(WrapMapper.wrap(HttpStatus.UNAUTHORIZED.value(),
                    "Invalid authentication")));
            httpServletResponse.getOutputStream().close();
            return;
        }
        if (userId != null) {
            UserUtils.setUserId(Long.valueOf(userId));
        }
        Claims claims = jwtService.getClaimFromToken(token);
        if (claims != null) {
            if (claims.get("shop") != null) {
                UserUtils.setShopIds((List<Long>) claims.get("shop"));
            }
            if (claims.get("roles") != null) {
                UserUtils.setRoleIds((List<Long>) claims.get("roles"));
            }
            if (claims.get("mallId") != null) {
                UserUtils.setMallId(Long.valueOf(claims.get("mallId").toString()));
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
