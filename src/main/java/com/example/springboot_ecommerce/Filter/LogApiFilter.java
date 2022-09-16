package com.example.springboot_ecommerce.Filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

public class LogApiFilter extends OncePerRequestFilter {
    private static Logger log=LoggerFactory.getLogger(LogApiFilter.class);
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        ContentCachingRequestWrapper contentCachingRequestWrapper=new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper contentCachingResponseWrapper=new ContentCachingResponseWrapper(response);

        filterChain.doFilter(contentCachingRequestWrapper,contentCachingResponseWrapper);
        //Print API
        logAPI(request,response);
        //Print Requestbody and ResponseBody
        logBody(contentCachingRequestWrapper, contentCachingResponseWrapper);
        //Using copyBodyToResponse() to copy the original data back to the responseBody
        contentCachingResponseWrapper.copyBodyToResponse();


    }

    //Get Json Request and response data
    private String getContent(byte [] content) {
        String body = new String(content);
        return body.replaceAll("[\n\t]", "");
    }
    //Get byte [] from Wrapper and turn it into a String using getContent()
    private void logBody(ContentCachingRequestWrapper requestWrapper,ContentCachingResponseWrapper responseWrapper) {
        String requestBody=getContent(requestWrapper.getContentAsByteArray());
        String responseBody = getContent(responseWrapper.getContentAsByteArray());
        log.info("Request:{}",requestBody);
        log.info("Response:{}",responseBody);


    }

    private void logAPI(HttpServletRequest request,HttpServletResponse response) {
        int httpstatus=response.getStatus();
        String httpmethod= request.getMethod();
        String url=request.getRequestURI();
        String params=request.getQueryString();
        if(params!=null){
            url+="?"+params;
        }
        log.info("{} {} {}",httpstatus,httpmethod,url);



    }
}

