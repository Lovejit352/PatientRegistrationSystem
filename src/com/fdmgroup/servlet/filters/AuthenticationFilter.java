package com.fdmgroup.servlet.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
@WebFilter("/*")
public class AuthenticationFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {	
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession(false);
		String uri = req.getRequestURI();
		if(uri.equals(req.getContextPath() + "/") 
                || uri.contains(req.getContextPath() + "/resources") 
                || uri.contains(req.getContextPath() + "/login") 
                ) {
			chain.doFilter(request, response);
			return;
		}
        if(session == null || session.getAttribute("loggedUser") == null){
            res.sendRedirect(req.getContextPath() + "/");
            return;
        }
		chain.doFilter(request, response);
		return;
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
