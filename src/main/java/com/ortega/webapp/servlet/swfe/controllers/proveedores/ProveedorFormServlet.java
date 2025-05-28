package com.ortega.webapp.servlet.swfe.controllers.proveedores;

import com.ortega.webapp.servlet.swfe.models.ProveedorEntity;
import com.ortega.webapp.servlet.swfe.models.TablaIsrEntity;
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
import java.util.Optional;

@WebServlet("/proveedores/proveedor-form")
public class ProveedorFormServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("connection");
        ProveedorService proveedorService = new ProveedorServiceImpl(connection);
        ProveedorEntity proveedor = new ProveedorEntity();

        int proveedorId;

        try {
            proveedorId = Integer.parseInt(req.getParameter("proveedor-id"));
        } catch (NumberFormatException e) {
            proveedorId = 0;
        }

        if (proveedorId > 0) {
            Optional<ProveedorEntity> tablaBd = proveedorService.obtenerProveedor(proveedorId);
            if (tablaBd.isPresent()) {
                proveedor = tablaBd.get();
            }
        }

        req.setAttribute("proveedor", proveedor);
        getServletContext().getRequestDispatcher("/proveedor-form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("connection");
        ProveedorService proveedorService = new ProveedorServiceImpl(connection);
        ProveedorEntity proveedor = new ProveedorEntity();

        proveedor.setProveedorId(Integer.parseInt(req.getParameter("proveedorId")));
        proveedor.setRfcProveedor(req.getParameter("rfc"));
        proveedor.setNombreProveedor(req.getParameter("nombre"));
        proveedor.setClienteId(Integer.parseInt(req.getParameter("clienteId")));

        proveedorService.actualizarGuardarProveedor(proveedor);

        resp.sendRedirect(req.getContextPath() + "/proveedores");
    }
}
