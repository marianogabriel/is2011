package ar.kennedy.is2011.theClouds.manager;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionFilter implements Filter{

	private FilterConfig filterConfig;
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		
		if (req instanceof HttpServletRequest) {
			HttpServletRequest hReq = (HttpServletRequest) req;
			HttpServletResponse hRes = (HttpServletResponse) res;
			
			HttpSession session = hReq.getSession();
			if(session.getAttribute("usuarioLogeado")== null 
					&& !hReq.getRequestURI().endsWith("registracionRapida.jsp")
					&& !hReq.getRequestURI().endsWith("restablecerClave.jsp")){
				String targetUrl = hReq.getContextPath() + "/errores/errorLogin.jsp";
				System.out.println(targetUrl);
				hRes.sendRedirect(targetUrl);
				return;
			}
			
		chain.doFilter(req, res);
		}
		
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.filterConfig = config;
		
	}

}
