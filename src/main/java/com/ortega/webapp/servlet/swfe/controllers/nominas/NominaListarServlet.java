package com.ortega.webapp.servlet.swfe.controllers.nominas;

import com.ortega.webapp.servlet.swfe.models.EjercicioEntity;
import com.ortega.webapp.servlet.swfe.models.MesEntity;
import com.ortega.webapp.servlet.swfe.models.nomina.NominaEntity;
import com.ortega.webapp.servlet.swfe.models.nomina.NominaFormDTO;
import com.ortega.webapp.servlet.swfe.services.MesService;
import com.ortega.webapp.servlet.swfe.services.MesServiceImpl;
import com.ortega.webapp.servlet.swfe.services.NominaService;
import com.ortega.webapp.servlet.swfe.services.NominaServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


@WebServlet("/nomina")
public class NominaListarServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("connection");
        NominaService nominaService = new NominaServiceImpl(connection);

        List<NominaEntity> nomina;
        HttpSession session = req.getSession();

        NominaFormDTO nominaFormDTO = new NominaFormDTO();
        EjercicioEntity ejercicioEntity = null;

        try {
            if (session.getAttribute("ejercicioSeleccionado") != null) {
                ejercicioEntity = (EjercicioEntity) session.getAttribute("ejercicioSeleccionado");

                nominaFormDTO.setEjercicioFiscal(ejercicioEntity.getEjercicioFiscalId());
                nominaFormDTO.setClienteId(Integer.parseInt(session.getAttribute("clienteId").toString()));
                nominaFormDTO.setQuincena(1);
            }

            if (session.getAttribute("mesSeleccionado") != null) {
                MesEntity mes = (MesEntity) session.getAttribute("mesSeleccionado");
                nominaFormDTO.setMes(mes.getMesId());
            } else {
                nominaFormDTO.setMes(0);
            }

            nomina = nominaService.obtenerListaNominas(nominaFormDTO);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        req.setAttribute("totales", obtenerTotalesNomina(nomina));
        req.setAttribute("quincena", 1);
        req.setAttribute("nominas", nomina);
        getServletContext().getRequestDispatcher("/nomina.jsp").forward(req, resp);
    }


    private Map<String, Double> obtenerTotalesNomina(List<NominaEntity> nomina) {
        double totalImss = 0;
        double totalIsr = 0;
        double totalSubsidio = 0;
        double totalSalarioNeto = 0;
        double totalFiniquito = 0;

        for (NominaEntity nominaEntity : nomina) {
            totalImss += nominaEntity.getImssNomina();
            totalIsr += nominaEntity.getIsrSalario();
            totalSubsidio += nominaEntity.getSubsidioNomina();
            totalSalarioNeto += nominaEntity.getSalarioNeto();
            totalFiniquito += nominaEntity.getFiniquitoNomina();
        }

        return Map.of(
                "totalImss", totalImss,
                "totalIsr", totalIsr,
                "totalSubsidio", totalSubsidio,
                "totalSalarioNeto", totalSalarioNeto,
                "totalFiniquito", totalFiniquito
        );
    }
}
