package com.ortega.webapp.servlet.swfe.controllers.facturas.egresos;

import com.ortega.webapp.servlet.swfe.models.EjercicioEntity;
import com.ortega.webapp.servlet.swfe.models.FacturaEntity;
import com.ortega.webapp.servlet.swfe.models.MesEntity;
import com.ortega.webapp.servlet.swfe.services.EgresoService;
import com.ortega.webapp.servlet.swfe.services.EgresoServiceImpl;
import com.ortega.webapp.servlet.swfe.services.MesService;
import com.ortega.webapp.servlet.swfe.services.MesServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/facturas/egresos")
public class EgresoListarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("connection");
        EgresoService egresoService = new EgresoServiceImpl(connection);
        MesService mesService = new MesServiceImpl(connection);

        HttpSession httpSession = req.getSession();

        List<MesEntity> meses = mesService.obtenerListaMeses("");
        req.setAttribute("meses", meses);

        System.out.println("Antes de validar mesId");
        if (httpSession.getAttribute("clienteId") != null && httpSession.getAttribute("mesSeleccionado") != null) {
            try {
                FacturaEntity facturaEntity = new FacturaEntity();
                facturaEntity.setClienteId(Integer.parseInt(httpSession.getAttribute("clienteId").toString()));

                MesEntity mesEntity = (MesEntity) httpSession.getAttribute("mesSeleccionado");
                facturaEntity.setMesId(mesEntity.getMesId());

                EjercicioEntity ejercicioEntity = (EjercicioEntity) httpSession.getAttribute("ejercicioSeleccionado");
                facturaEntity.setEjercicioFiscalId(ejercicioEntity.getEjercicioFiscalComoInt());

                List<FacturaEntity> facturas = egresoService.obtenerListaEgresos(facturaEntity);
                req.setAttribute("facturas", facturas);
                System.out.println("facturas:" + facturas);
                req.setAttribute("mesId", req.getParameter("mesId"));
                System.out.println("mesId:" + req.getParameter("mesId"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("Despues de validar mesId");
        getServletContext().getRequestDispatcher("/egresos.jsp").forward(req, resp);
    }
}
