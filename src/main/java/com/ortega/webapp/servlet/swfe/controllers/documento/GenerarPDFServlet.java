package com.ortega.webapp.servlet.swfe.controllers.documento;

import com.ortega.webapp.servlet.swfe.models.EjercicioEntity;
import com.ortega.webapp.servlet.swfe.models.ImpuestoEntity;
import com.ortega.webapp.servlet.swfe.models.MesEntity;
import com.ortega.webapp.servlet.swfe.services.ImpuestoService;
import com.ortega.webapp.servlet.swfe.services.ImpuestoServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;

@WebServlet("/generar-pdf")
public class GenerarPDFServlet extends HttpServlet {

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

            req.setAttribute("impuesto", impuesto);

            String html = generateHTML(impuesto);

            resp.setContentType("application/pdf");
            resp.setHeader("Content-Disposition", "attachment; filename=impuestos.pdf");

            OutputStream os = resp.getOutputStream();
            ITextRenderer renderer = new ITextRenderer();

            renderer.setDocumentFromString(html);

            String baseUrl = req.getRequestURL().toString();
            renderer.getSharedContext().setBaseURL(baseUrl.substring(0, baseUrl.lastIndexOf("/")));

            renderer.layout();

            renderer.createPDF(os);
            os.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String generateHTML(ImpuestoEntity impuestoGenerado) {
        StringBuilder htmlBuilder = new StringBuilder();

        htmlBuilder.append("<!DOCTYPE html>")
                .append("<html lang=\"es\">")
                .append("<head>")
                .append("<meta charset=\"UTF-8\"/>")
                .append("<title>Impuestos</title>")
                .append("<style>")
                .append("body { font-family: Arial, sans-serif; margin: 0; padding: 0; font-size: 12px; }")
                .append("h1, h2 { color: #2963dd; }")
                .append("table { width: 100%; border-collapse: collapse; margin: 20px 0; }")
                .append("th, td { padding: 8px; border: 1px solid #ddd; text-align: left; }")
                .append("th { background-color: #f4f4f4; }")
                .append(".total { font-weight: bold; background-color: #f9f9f9; }")
                .append(".impuesto-header { text-align: center; padding: 20px 0; }")
                .append(".impuesto-article { padding: 20px; }")
                .append("</style>")
                .append("</head>")
                .append("<body>");

        htmlBuilder.append("<main><section class=\"impuestos-container\">")
                .append("<div class=\"impuesto-header\"><h1>Calculo de impuestos mensual</h1></div>")
                .append("<article class=\"impuesto-article\"><div class=\"container\">");

        htmlBuilder.append("<h2>I.S.R.</h2>")
                .append("<div class=\"table-wrapper\"><table class=\"impuesto-table\">")
                .append("<tr><th>Concepto</th><th>Importe</th></tr>")
                .append("<tr><td>Ingresos del mes anterior</td><td>").append(impuestoGenerado.getIngresoMesAnterior()).append("</td></tr>")
                .append("<tr><td>Ingresos cobrados</td><td>").append(impuestoGenerado.getIngresosCobrados()).append("</td></tr>")
                .append("<tr class=\"total\"><td>Total de periodo</td><td>").append(impuestoGenerado.getTotalDePeriodo()).append("</td></tr>")
                .append("<tr><td>Deducciones del mes anterior</td><td>").append(impuestoGenerado.getDeduccionesMesAnterior()).append("</td></tr>")
                .append("<tr><td>Deducciones autorizadas pagadas</td><td>").append(impuestoGenerado.getDeduccionesAutorizadasPagadas()).append("</td></tr>")
                .append("<tr><td>Gastos Nómina</td><td>").append(impuestoGenerado.getGastosNomina()).append("</td></tr>")
                .append("<tr><td>Depreciación</td><td>").append(impuestoGenerado.getDepreciacion()).append("</td></tr>")
                .append("<tr class=\"total\"><td>Total de periodo</td><td>").append(impuestoGenerado.getDeduccionesDelMes()).append("</td></tr>")
                .append("<tr class=\"total\"><td>Base para el pago provisional</td><td>").append(String.format("%.2f", impuestoGenerado.getBasePagoProvisional())).append("</td></tr>")
                .append("</table></div>");

        htmlBuilder.append("<h2>I.V.A.</h2>")
                .append("<div class=\"table-wrapper\"><table class=\"impuesto-table\">")
                .append("<tr><th>Concepto</th><th>Importe</th></tr>")
                .append("<tr><td>Base del impuesto</td><td>").append(impuestoGenerado.getBaseDelImpuesto()).append("</td></tr>")
                .append("<tr><td>I.V.A. Cobrado</td><td>").append(impuestoGenerado.getIvaCobrado()).append("</td></tr>")
                .append("<tr><td>(-) Retenciones</td><td>").append(impuestoGenerado.getRetenciones()).append("</td></tr>")
                .append("<tr><td>(-) I.V.A. Acreditable</td><td>").append(impuestoGenerado.getIvaAcreditable()).append("</td></tr>")
                .append("<tr><td>(=) I.V.A. a cargo o a favor del periodo</td><td>").append(String.format("%.2f", impuestoGenerado.getIvaAFavorPeriodo())).append("</td></tr>")
                .append("<tr><td>(-) Saldo a favor pendiente de acreditar</td><td>").append(impuestoGenerado.getSaldoAFavor()).append("</td></tr>")
                .append("<tr class=\"calculo-total\"><td>(=) Impuesto a cargo (+) ó a favor (-)</td><td>").append(String.format("%.2f", impuestoGenerado.getImpuestoCargoFavor())).append("</td></tr>")
                .append("</table></div>");

        htmlBuilder.append("<h2>I.S.R. Salarios</h2>")
                .append("<div class=\"table-wrapper\"><table class=\"impuesto-table\">")
                .append("<tr><th>Concepto</th><th>Importe</th></tr>")
                .append("<tr><td>I.S.R. Salarios</td><td>").append(impuestoGenerado.getIsrSalarios()).append("</td></tr>")
                .append("<tr><td>(-) Subsidio</td><td>").append(impuestoGenerado.getSubsidio()).append("</td></tr>")
                .append("<tr><td>(-) Subsidio periodos anteriores</td><td>").append(impuestoGenerado.getSubsidioPeriodosAnteriores()).append("</td></tr>")
                .append("<tr class=\"calculo-total\"><td>(=) ISR Salario. mensual por pagar</td><td>").append(String.format("%.2f", impuestoGenerado.getIsrSalarioPorPagar())).append("</td></tr>")
                .append("</table></div>");

        htmlBuilder.append("</div></article></section></main></body></html>");

        return htmlBuilder.toString();
    }
}
