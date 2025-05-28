package com.ortega.webapp.servlet.swfe.controllers.impuestos;

import com.ortega.webapp.servlet.swfe.models.EjercicioEntity;
import com.ortega.webapp.servlet.swfe.models.ImpuestoEntity;
import com.ortega.webapp.servlet.swfe.models.MesEntity;
import com.ortega.webapp.servlet.swfe.services.ImpuestoService;
import com.ortega.webapp.servlet.swfe.services.ImpuestoServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/impuestos/calcular")
public class ImpuestoCalcularServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("connection");
        ImpuestoService impuestoService = new ImpuestoServiceImpl(connection);

        HttpSession httpSession = req.getSession();

        try {
            ImpuestoEntity impuesto = new ImpuestoEntity();
            impuesto.setClienteId(Integer.parseInt(httpSession.getAttribute("clienteId").toString()));

            EjercicioEntity ejercicio = (EjercicioEntity) httpSession.getAttribute("ejercicioSeleccionado");
            impuesto.setEjercicioId(ejercicio.getEjercicioFiscalId());

            MesEntity mes = (MesEntity) httpSession.getAttribute("mesSeleccionado");
            impuesto.setMesId(mes.getMesId());

            impuestoService.calcularImpuesto(impuesto);

            System.out.println("Impuesto calculado" + impuesto);
            req.setAttribute("impuesto", impuesto);

            getServletContext().getRequestDispatcher("/impuestos.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
