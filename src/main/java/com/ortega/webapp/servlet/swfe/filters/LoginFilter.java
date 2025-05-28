package com.ortega.webapp.servlet.swfe.filters;

import com.ortega.webapp.servlet.swfe.services.LoginServiceSessionImpl;
import jakarta.servlet.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebFilter({"/*"})
@MultipartConfig
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LoginServiceSessionImpl service = new LoginServiceSessionImpl();
        Optional<String> username = service.getUsername((HttpServletRequest) request);

        String uri = ((HttpServletRequest) request).getRequestURI();

        if (uri.endsWith(".css") || uri.endsWith(".jpg") || uri.endsWith(".png") || uri.endsWith(".ico")) {
            chain.doFilter(request, response);
            return;
        }

        if (uri.endsWith("login.jsp") || uri.endsWith("login")) {
            chain.doFilter(request, response);
            return;
        }

        if (username.isPresent()) {
            chain.doFilter(request, response);
        } else {
            ((HttpServletResponse)response).sendError(HttpServletResponse.SC_UNAUTHORIZED,
                    "Lo sentimos no estas autorizado para ingresar a esta pagina!");
        }
    }
}
