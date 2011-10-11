package ar.kennedy.is2011.controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.kennedy.is2011.session.SessionManager;
import ar.kennedy.is2011.utils.WebUtils;

public class SecureFilter implements Filter{

	@SuppressWarnings("unused")
	private FilterConfig filterConfig;
	
	public void destroy() { }

	public void doFilter(ServletRequest sRequest, ServletResponse sResponse, FilterChain chain) throws IOException, ServletException {
		if (sRequest instanceof HttpServletRequest) {
			HttpServletRequest request = (HttpServletRequest) sRequest;
			HttpServletResponse response = (HttpServletResponse) sResponse;
			
			if(!WebUtils.validateUserLogin(request, response, SessionManager.get(request, WebUtils.getSessionIdentificator(request)))){
				response.sendRedirect("errorLogin.jsp");
				return;
			}
			
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig config) throws ServletException {
		this.filterConfig = config;
	}

}