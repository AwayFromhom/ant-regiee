package com.itheima.reggie.filter;

import com.itheima.reggie.common.BaseContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author
 * @version 1.0
 * @since 1.0
 * @param
 */
@WebFilter(filterName = "loginfilter",urlPatterns = "/*")
@Slf4j
public class LoginFilter implements Filter {

    //路径匹配器，支持通配符
    public static final AntPathMatcher PATTERN = new AntPathMatcher();
    /*
    * 路径匹配判断本次请求是否放行
    * @param requestURI
    * */
    public static boolean matches(String uri, String[] urls){
        for (String url : urls) {
            boolean match = PATTERN.match(url, uri);
            if (match) {

                return true;
            }
        }
        return false;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //1.获取请求的URI
        String uri = request.getRequestURI();

        //定义不需要处理的URL
        String [] urls = new String[]{
                "/api/employee/logout",
               "/api/employee/login",
//                "/api/employee/**"

        };
        //2.判断请求是否处理？
        boolean matches = matches(uri, urls);
        if (matches) {
            filterChain.doFilter(request, response);
            log.info(" {} 放行 " ,uri);
            return;
        }
       Integer empid = (Integer) request.getSession().getAttribute("employee");
        if(empid!=null) {
            BaseContext.setCurrentId(empid);
            filterChain.doFilter(request, response);
            log.info(" 用户{}已登录url:{}",request.getSession().getAttribute("employee"),uri );
            return;
        };
        log.info("拦截 请求 {} " ,uri);

    }
}
