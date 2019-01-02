package com.hateapple.myspringboot.base.filter;

import javax.servlet.*;
import java.io.IOException;

public class MyFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        System.out.println("过滤器 - doFilter before  ");
        servletRequest.setAttribute("name", "tom");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("过滤器 - doFilter after  ");
    }

    @Override
    public void destroy() {

    }
}
