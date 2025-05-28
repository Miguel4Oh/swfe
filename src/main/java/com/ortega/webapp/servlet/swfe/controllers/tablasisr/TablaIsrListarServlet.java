package com.ortega.webapp.servlet.swfe.controllers.tablasisr;

import com.ortega.webapp.servlet.swfe.models.MesEntity;
import com.ortega.webapp.servlet.swfe.models.TablaIsrEntity;
import com.ortega.webapp.servlet.swfe.services.MesService;
import com.ortega.webapp.servlet.swfe.services.MesServiceImpl;
import com.ortega.webapp.servlet.swfe.services.TablaIsrService;
import com.ortega.webapp.servlet.swfe.services.TablaIsrServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/tablas")
public class TablaIsrListarServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("connection");
        TablaIsrService tablaIsrService = new TablaIsrServiceImpl(connection);
        MesService mesEntity = new MesServiceImpl(connection);

        List<TablaIsrEntity> tablasIsr = new ArrayList<>();
        List<MesEntity> meses = new ArrayList<>();
        MesEntity mes = new MesEntity();

        String nombreTabla = "";

        if (req.getParameter("nombreTabla") != null) {
            nombreTabla = req.getParameter("nombreTabla");

            try {
                tablasIsr = tablaIsrService.obtenerListaTablasIsr(nombreTabla);
                meses = mesEntity.obtenerListaMeses("");
                mes = mesEntity.obtenerMesPorNombre(nombreTabla);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        req.setAttribute("tablas", tablasIsr);
        req.setAttribute("meses", meses);
        req.setAttribute("periodoTabla", mes);
        //req.setAttribute("usuario", usuario);
        getServletContext().getRequestDispatcher("/tablas.jsp").forward(req, resp);
    }
}
