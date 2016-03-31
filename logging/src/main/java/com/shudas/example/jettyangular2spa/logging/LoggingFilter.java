package com.shudas.example.jettyangular2spa.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Simply log the inbound and outbound request
 */
@Singleton
public class LoggingFilter implements Filter {
    private static final Logger LOG = LoggerFactory.getLogger(LoggingFilter.class);

    public void init(FilterConfig filterConfig) throws ServletException {}

    /**
     * Simple filter that logs all incoming and outgoing requests.
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String msg = String.format("%s %s [%s] \"%s %s %s\" %d %d",
                request.getRemoteAddr(),
                request.getRemoteUser(),
                getCurrentTime(),
                request.getMethod(),
                getFullURL(request),
                request.getProtocol(),
                response.getStatus(),
                response.getBufferSize());
        LOG.info(msg);

        filterChain.doFilter(servletRequest, servletResponse);

        msg = String.format("%s %s [%s] \"%s %s %s\" %d %d",
                request.getRemoteAddr(),
                request.getRemoteUser(),
                getCurrentTime(),
                request.getMethod(),
                getFullURL(request),
                request.getProtocol(),
                response.getStatus(),
                response.getBufferSize());
        LOG.info(msg);
    }

    /**
     * Get the current time in a nice format
     * @return
     */
    private String getCurrentTime() {
        String format = "dd/MMM/yyyy:HH:mm:ss %Z";
        SimpleDateFormat dateFormatGmt = new SimpleDateFormat(format);
        return dateFormatGmt.format(new Date());
    }

    /**
     * Get the full URL from the given request. Appends the query string to the URL.
     * @param request
     * @return
     */
    public static String getFullURL(HttpServletRequest request) {
        StringBuffer requestURL = request.getRequestURL();
        String queryString = request.getQueryString();
        if (queryString != null) {
            requestURL.append('?').append(queryString).toString();
        }
        return requestURL.toString();
    }

    @Override
    public void destroy() {}
}
