package com.ortega.webapp.servlet.swfe.controllers.ejercicio.mes;

import com.ortega.webapp.servlet.swfe.models.MesEntity;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/mes/seleccionar")
public class MesSeleccionarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();

        try {
            List<MesEntity> meses = (List<MesEntity>) httpSession.getAttribute("meses");

            System.out.println("mesId: " + req.getParameter("mesId"));
            int mesId = Integer.parseInt(req.getParameter("mesId").toString());

            for (MesEntity mes : meses) {
                if (mes.getMesId() == mesId) {
                    httpSession.setAttribute("mesSeleccionado", mes);
                    System.out.println("mes seleccionado" + httpSession.getAttribute("mesSeleccionado"));
                }
            }

            resp.setContentType("application/json");
            resp.getWriter().write("{\"status\": \"ok\"}");
            resp.setCharacterEncoding("UTF-8");

        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("{\"status\": \"error\"}");
        }

    }
}
