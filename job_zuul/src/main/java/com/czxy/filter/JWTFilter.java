package com.czxy.filter;

import com.czxy.util.JWTUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 鉴权
 */
@Component
public class JWTFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 编写权限过滤的核心业务：
     * 主要任务：确认是否放行
     * 根据URL确认是否放行
     * 有些URL是需要登陆之后才能访问，有些URL不需要登陆就可以访问
     * 如果需要登陆的URL，需要获取token，并且解析token，成功了，就放行，不成功，就拦截
     */
    @Override
    public Object run() throws ZuulException {
        //1 获取Request
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        //2 获取URL http://localhost:9000/goods-search/search
        StringBuffer url = request.getRequestURL();
        //3 判断URL是否需要登陆
        // 如果/login 路径  和  /register需要放行
        if (url.toString().contains("login")||
                url.toString().contains("register")||
                url.toString().contains("getVerify")||
                url.toString().contains("forgetLogin")||
                url.toString().contains("test")) {
            return null;
        }
        //4 必须拦截，判断token：token放在request请求的header中，而且名字/key 一定为authorization
        //5 从request请求头header中获取authorization
        String token = request.getHeader("authorization");
        //6 判断token是否为空
        if (token != null && (!"".equals(token.trim()))) {
            // 7 校验解析token
            Claims claims = JWTUtil.parseToken(token, "czgz");
            // 8 判断解析是否成功
            if (claims != null) {
                //  已经通过yml配置将token续传到下一个服务
                // 9 验证通过， 放行
                return null;
            }
        }
        // 10 拦截
        ctx.setSendZuulResponse(false);
        ctx.setResponseStatusCode(401);
        ctx.setResponseBody("{'msg':'校验失败'}");
        ctx.getResponse().setContentType("text/html;charset=utf-8");//  不设置的话，中文乱码
        return null;
    }

}
