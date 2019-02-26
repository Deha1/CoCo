package com.coco.commom;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;


/**
 * @Author Jaa [tigerjaa@163.com]
 * @Date 2019/2/26 0:06
 */

public class KyExpireFilter  implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        chain.doFilter(req, res);
    }
    @Override
    public void destroy() {

    }
}
