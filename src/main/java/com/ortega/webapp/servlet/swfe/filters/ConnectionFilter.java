package com.ortega.webapp.servlet.swfe.filters;

import com.ortega.webapp.servlet.swfe.services.ServiceJdbcException;
import com.ortega.webapp.servlet.swfe.utils.ConnectionBd;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebFilter("/*")
public class ConnectionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try (Connection connection = ConnectionBd.getConnection()) {
            if(connection.getAutoCommit()){
                connection.setAutoCommit(false);
            }

            try{
                servletRequest.setAttribute("connection", connection);
                filterChain.doFilter(servletRequest, servletResponse);
                connection.commit();
            } catch (SQLException | ServiceJdbcException exception) {
                connection.rollback();
                exception.printStackTrace();
                HttpServletResponse response = (HttpServletResponse) servletResponse;
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

        }
    }
}
