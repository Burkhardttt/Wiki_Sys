//package com.burkhardt.wiki.filter;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//
//@Component
//public class LogFilter implements Filter {
//
//	private static final Logger LOG = LoggerFactory.getLogger(LogFilter.class);
//
//	@Override
//	public void init(FilterConfig filterConfig) throws ServletException {
//
//	}
//
//	@Override
//	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//		// 打印请求信息
//		HttpServletRequest request = (HttpServletRequest) servletRequest;
//		LOG.info("------------- LogFilter Start(开始) -------------");
//		LOG.info("请求地址(Request Address): {} {}", request.getRequestURL().toString(), request.getMethod());
//		LOG.info("远程地址(Remote Address): {}", request.getRemoteAddr());
//
//		long startTime = System.currentTimeMillis();
//		filterChain.doFilter(servletRequest, servletResponse);
//		LOG.info("------------- LogFilter End (结束) Time Spent(耗时)：{} ms -------------", System.currentTimeMillis() - startTime);
//	}
//}
//
