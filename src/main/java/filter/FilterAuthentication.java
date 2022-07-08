package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



import JpaUtil.JpaUtil;
import entidades.Pessoa;

@WebFilter(urlPatterns = {"/*"})
public class FilterAuthentication implements Filter  {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		 
		JpaUtil.getEntityManager();
		
	}

	/*todas as request e response passsam por aqui*/
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		
		HttpServletRequest req =(HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		Pessoa  usuaruioLogado = (Pessoa) session.getAttribute("usuarioLogado");
		
		String url = req.getServletPath();
		
		if(!url.equalsIgnoreCase("index.jsf") && usuaruioLogado == null  ){
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsf");
			dispatcher.forward(request, response);
			return;
			
		}else {
		
		/*executa as açoes de request e response*/
		chain.doFilter(request, response);		
		}
	}

	@Override
	public void destroy() {
		
		
	}
   

}
