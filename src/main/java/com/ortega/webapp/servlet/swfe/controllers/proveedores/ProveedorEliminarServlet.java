package com.ortega.webapp.servlet.swfe.controllers.proveedores;

import com.ortega.webapp.servlet.swfe.services.ProveedorService;
import com.ortega.webapp.servlet.swfe.services.ProveedorServiceImpl;
import com.ortega.webapp.servlet.swfe.services.TablaIsrService;
import com.ortega.webapp.servlet.swfe.services.TablaIsrServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/proveedores/eliminar")
public class ProveedorEliminarServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("connection");
        ProveedorService proveedorService = new ProveedorServiceImpl(connection);
        int proveedorId;

        System.out.println("Cliente Id: " + req.getParameter("cliente-id"));
        try{
            proveedorId = Integer.parseInt(req.getParameter("proveedor-id"));
        }catch (NumberFormatException e){
            proveedorId = 0;
        }

        proveedorService.eliminarProveedor(proveedorId);

        getServletContext().getRequestDispatcher("/proveedores").forward(req, resp);
    }
}
