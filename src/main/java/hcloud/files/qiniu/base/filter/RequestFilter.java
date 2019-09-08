package hcloud.files.qiniu.base.filter;

import hcloud.files.qiniu.base.handler.RequestReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.logging.LogRecord;

/**
 * description 描述这个类的主要功能、用途
 * 创建时间 2019/9/8
 *
 * @author 杨丁辉
 */

@Component
@WebFilter(filterName = "RequestFilter", urlPatterns = {"/*"})
public class RequestFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(RequestFilter.class);


    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        RequestReader requestReader = new RequestReader(httpServletRequest);
        chain.doFilter(requestReader, response);
    }

    public void destroy() {
    }
}
