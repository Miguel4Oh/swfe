package com.ortega.webapp.servlet.swfe.services;

import com.ortega.webapp.servlet.swfe.models.FacturaEntity;
import com.ortega.webapp.servlet.swfe.models.ImpuestoEntity;
import com.ortega.webapp.servlet.swfe.models.catalogos.CatalogoEntity;
import com.ortega.webapp.servlet.swfe.models.catalogos.EncabezadoEntity;
import com.ortega.webapp.servlet.swfe.models.catalogos.dto.ColumnasEspeciales;
import com.ortega.webapp.servlet.swfe.models.catalogos.dto.EncabezadosDTO;
import com.ortega.webapp.servlet.swfe.repositories.FacturaRepositoryImpl;
import com.ortega.webapp.servlet.swfe.utils.ValidarNull;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.Normalizer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.ortega.webapp.servlet.swfe.utils.Constantes.*;

public class FacturaServiceImpl implements FacturaService {
    private final FacturaRepositoryImpl facturaRepository;
    private final CatalogoServiceImpl catalogoService;

    public FacturaServiceImpl(Connection connection) {
        this.facturaRepository = new FacturaRepositoryImpl(connection);
        this.catalogoService = new CatalogoServiceImpl(connection);
    }

    @Override
    public List<FacturaEntity> obtenerListaFacturas(int clienteId, int mesId, String filtroBusqueda) {
        try {
            return facturaRepository.listarFacturas(clienteId, mesId, filtroBusqueda);
        } catch (SQLException exception) {
            throw new ServiceJdbcException(exception.getMessage(), exception.getCause());
        }
    }

    @Override
    public Optional<FacturaEntity> obtenerFactura(int facturaId) {
        try {
            return Optional.ofNullable(facturaRepository.obtenerFactura(facturaId));
        } catch (SQLException exception) {
            throw new ServiceJdbcException(exception.getMessage(), exception.getCause());
        }
    }

    @Override
    public void actualizarGuardarFactura(FacturaEntity factura) {
        obtenerFechaCobranza(factura);

        if (factura.getFacturaId() > 0) {
            try {
                facturaRepository.actualizarFactura(factura);
            } catch (SQLException exception) {
                throw new ServiceJdbcException(exception.getMessage(), exception.getCause());
            }
        } else {
            try {
                facturaRepository.guardarFactura(factura);
            } catch (SQLException exception) {
                throw new ServiceJdbcException(exception.getMessage(), exception.getCause());
            }
        }
    }

    @Override
    public void eliminarFactura(int facturaId) {
        try {
            facturaRepository.eliminarFactura(facturaId);
        } catch (SQLException exception) {
            throw new ServiceJdbcException(exception.getMessage(), exception.getCause());
        }
    }

    @Override
    public void guardarFacturas(Part filePart, String clienteId, FacturaEntity factura) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(filePart.getInputStream());
        XSSFSheet hoja = workbook.getSheetAt(0);

        try {
            insertarNuevasFacturas(hoja, clienteId, factura);
        } catch (SQLException exception) {
            throw new ServiceJdbcException(exception.getMessage(), exception.getCause());
        }
    }

    @Override
    public double obtenerSubtotalIngresos(ImpuestoEntity factura) {
        double subtotalIngresos = 0;

        try {
            subtotalIngresos = facturaRepository.obtenerSubtotalIngresos(factura);
        } catch (SQLException exception) {
            throw new ServiceJdbcException(exception.getMessage(), exception.getCause());
        }

        return subtotalIngresos;
    }

    @Override
    public double obtenerSubtotalDeducciones(ImpuestoEntity factura) {
        double subtotalDeducciones = 0;

        try {
            subtotalDeducciones = facturaRepository.obtenerSubtotalDeducciones(factura);
        } catch (SQLException exception) {
            throw new ServiceJdbcException(exception.getMessage(), exception.getCause());
        }

        return subtotalDeducciones;
    }

    @Override
    public double obtenerIvaIngresos(ImpuestoEntity factura) {
        double ivaIngresos = 0;

        try {
            if (factura.getMesId() >= 1 && factura.getClienteId() >= 1 && factura.getEjercicioId() >= 1) {
                ivaIngresos = facturaRepository.obtenerIvaIngresos(factura);
            }
        } catch (SQLException exception) {
            throw new ServiceJdbcException(exception.getMessage(), exception.getCause());
        }

        return ivaIngresos;
    }

    @Override
    public double obtenerIvaRetenidoIngresos(ImpuestoEntity factura) {
        double ivaRetenidoIngresos = 0;

        try {
            ivaRetenidoIngresos = facturaRepository.obtenerIvaRetenidoIngresos(factura);
        } catch (SQLException exception) {
            throw new ServiceJdbcException(exception.getMessage(), exception.getCause());
        }

        return ivaRetenidoIngresos;
    }

    @Override
    public double obtenerIvaEgresos(ImpuestoEntity factura) {
        double ivaAcreditableEgresos = 0;

        try {
            ivaAcreditableEgresos = facturaRepository.obtenerIvaAcreditableEgresos(factura);
        } catch (SQLException exception) {
            throw new ServiceJdbcException(exception.getMessage(), exception.getCause());
        }

        return ivaAcreditableEgresos;
    }

    @Override
    public double obtenerIsrRetenidoIngresos(ImpuestoEntity factura) {
        double isrRetenidoIngresos = 0;

//        try {
//            isrRetenidoIngresos = facturaRepository.
//        }

        return 0;
    }

    private void obtenerFechaCobranza(FacturaEntity factura) {
        if (factura.getFechaCobranza() != null) {
            System.out.println("Fecha de Cobranza: " + factura.getFechaCobranza());

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(factura.getFechaCobranza());

            factura.setMesCobroId(calendar.get(Calendar.MONTH) + 1);
        }
    }

    private void insertarNuevasFacturas(XSSFSheet hoja, String clienteId, FacturaEntity facturaEntity) throws SQLException {
        System.out.println("Insertando facturas...");
        List<Map<String, String>> facturas = extraerFacturasDeArchivo(hoja);

        if (!facturas.isEmpty()) {
            for (Map<String, String> factura : facturas) {

                insertarNuevaFactura(factura, clienteId, facturaEntity);
            }
        }
    }

    private void insertarNuevaFactura(Map<String, String> factura, String clienteId, FacturaEntity facturaEntityData) throws SQLException {
        System.out.println("Insertando factura: " + factura.get("UUID"));

        FacturaEntity facturaEntity = facturaRepository.obtenerFacturaPorUUID(factura.get("UUID"));

        if (facturaEntity == null) {
            FacturaEntity nuevaFactura = mapearCamposFactura(factura, clienteId);
            nuevaFactura.setEjercicioFiscalId(facturaEntityData.getEjercicioFiscalId());
            nuevaFactura.setMesId(facturaEntityData.getMesId());
            nuevaFactura.setTipoFactura(facturaEntityData.getTipoFactura());

            facturaRepository.guardarFactura(nuevaFactura);
        }
    }

    private FacturaEntity mapearCamposFactura(Map<String, String> factura, String clienteId) {
        FacturaEntity facturaEntity = new FacturaEntity();

        try {
            facturaEntity.setFolioFactura(ValidarNull.getValor(factura.get("FOLIO")));
            facturaEntity.setFechaFactura(formatearFecha(factura.get("EMISION")));
            //facturaEntity.setFechaCobranza();
            facturaEntity.setRfcEmisor(ValidarNull.getValor(factura.get("RECEPTOR")));
            facturaEntity.setNombreEmisor(ValidarNull.getValor(factura.get("RAZON SOCIAL")));
            facturaEntity.setSubtotalFactura(Double.parseDouble(factura.get("SUBTOTAL")));

            if (factura.get("IMPORTE") != null && !factura.get("IMPORTE").isBlank()) {
                facturaEntity.setIvaFactura(Double.parseDouble(factura.get("IMPORTE")));
            }

            if (factura.get("TOTAL") != null && !factura.get("TOTAL").isBlank()) {
                facturaEntity.setTotalFactura(Double.parseDouble(factura.get("TOTAL")));
            }

            facturaEntity.setUuidFactura(ValidarNull.getValor(factura.get("UUID")));
            //facturaEntity.setTcFactura();
            facturaEntity.setUsoCfdiReceptor(ValidarNull.getValor(factura.get("DESCRIPCION DEL USO DEL CFDI")));
            facturaEntity.setClienteId(Integer.parseInt(clienteId));

        } catch (Exception e) {
            e.printStackTrace();
        }

        mapearCamposDeCatalogos(factura, facturaEntity);

        return facturaEntity;
    }

    private void mapearCamposDeCatalogos(Map<String, String> factura, FacturaEntity facturaEntity) {
        Optional<CatalogoEntity> catalogoOptional = catalogoService.obtenerCatalogos();

        if (catalogoOptional.isPresent()) {
            CatalogoEntity catalogo = catalogoOptional.get();

//            for (Map.Entry<String, Object> tiposSerie : catalogo.getTiposFactura().entrySet()) {
//                if (tiposSerie.getValue().equals(factura.get("TIPO DE FACTURA"))) {
//                    facturaEntity.setTipoFactura(tiposSerie.getKey());
//                }
//            }

            for (Map.Entry<String, Object> serie : catalogo.getSeries().entrySet()) {
                if (serie.getValue().equals(factura.get("SERIE"))) {
                    facturaEntity.setSerie(serie.getKey());
                }
            }

            for (Map.Entry<String, Object> moneda : catalogo.getMonedas().entrySet()) {
                if (moneda.getValue().equals(factura.get("MONEDA"))) {
                    facturaEntity.setMoneda(moneda.getKey());
                }
            }

            for (Map.Entry<String, Object> estatus : catalogo.getEstatus().entrySet()) {
                if (estatus.getValue().equals(factura.get("ESTATUS"))) {
                    facturaEntity.setEstatus(estatus.getKey());
                }
            }

            for (Map.Entry<String, Object> efecto : catalogo.getEfectos().entrySet()) {
                if (efecto.getValue().equals(factura.get("EFECTO"))) {
                    facturaEntity.setEfecto(efecto.getKey());
                }
            }

            for (Map.Entry<String, Object> metodoPago : catalogo.getMetodosPago().entrySet()) {
                if (metodoPago.getValue().equals(factura.get("METODO"))) {
                    facturaEntity.setMetodoPago(metodoPago.getKey());
                }
            }

            for (Map.Entry<String, Object> formaPago : catalogo.getFormasPago().entrySet()) {
                if (formaPago.getValue().equals(factura.get("FORMA"))) {
                    facturaEntity.setFormaPago(formaPago.getKey());
                }
            }

//            for (Map.Entry<String, Object> formaPagoDeducible : catalogo.getFormasPagoDeducible().entrySet()) {
//                if (formaPagoDeducible.getValue().equals(factura.get("FORMA DE PAGO DEDUCIBLE"))) {
//                    facturaEntity.setFormaPagoDeducible(formaPagoDeducible.getKey());
//                }
//            }

            facturaEntity.setFormaPagoDeducible("3");
        }
    }

    private Date formatearFecha(String fecha) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-uuuu");

        return Date.valueOf(LocalDate.parse(fecha, formatter));
    }

    private List<Map<String, String>> extraerFacturasDeArchivo(XSSFSheet hoja) throws SQLException {
        List<Map<String, String>> facturas = new ArrayList<>();

        Map<Integer, String> columnasMap = new HashMap<>();

        ColumnasEspeciales columnasEspeciales = new ColumnasEspeciales();

        for (int i = 0; i < hoja.getPhysicalNumberOfRows() - 1; i++) {
            Map<String, String> factura = new HashMap<>();

            Row row = hoja.getRow(i);

            for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
                String celda = row.getCell(j).toString();

                switch (i) {
                    case 0:
                        comprobarCabeceraPrincipal(celda, columnasEspeciales, j);
                        break;

                    case 1:
                        if (celda.equals(IVA_16)) {
                            columnasEspeciales.setColumnaIva16(j);
                        }
                        break;
                    case 2:
                        columnasMap.putAll(obtenerColumnasMap(i, j, celda, columnasEspeciales));
                        break;

                    default:
                        if (columnasMap.containsKey(j)) {
                            factura.put(columnasMap.get(j), celda);
                            facturas.add(factura);
                        }
                        break;
                }
            }
        }

        return facturas;
    }

    private void comprobarCabeceraPrincipal(String celda, ColumnasEspeciales columnasEspeciales, int j) {
        switch (celda) {
            case EMISOR:
                columnasEspeciales.setColumnaEmisor(j);

                break;
            case IMPORTES:
                columnasEspeciales.setColumnaImportes(j);

                break;
            case COMPROBANTE:
                columnasEspeciales.setColumnaComprobante(j);
                break;

            case RECEPTOR:
                columnasEspeciales.setColumnaReceptor(j);
                break;
        }
    }

    private Map<Integer, String> obtenerColumnasMap(int i, int j, String celda, ColumnasEspeciales columnasEspeciales) throws SQLException {
        Map<Integer, String> columnasMap = new HashMap<>();

        int columnaEmisor = columnasEspeciales.getColumnaEmisor();
        int columnaImportes = columnasEspeciales.getColumnaImportes();
        int columnaIva = columnasEspeciales.getColumnaIva16();
        int columnaComprobante = columnasEspeciales.getColumnaComprobante();
        int columnaReceptor = columnasEspeciales.getColumnaReceptor();

        EncabezadosDTO encabezados = obtenerEncabezados();
        List<String> columnas = encabezados.getColumnas();
        List<String> columnasEspecial = encabezados.getColumnasEspecial();

        String cell = Normalizer.normalize(celda.toUpperCase(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");

        if ((j >= columnaEmisor && j <= columnaEmisor + 3) ||
                (j >= columnaImportes && j <= columnaImportes + 6) ||
                (j >= columnaIva && j <= columnaIva + 4) ||
                (j >= columnaComprobante && j <= columnaComprobante + 18) ||
                (j >= columnaReceptor && j <= columnaReceptor + 8)) {

            if (columnasEspecial.contains(cell)) {
                columnasMap.put(j, cell);
            }
        }

        if (columnas.contains(cell)) {
            columnasMap.put(j, cell);
        }

        return columnasMap;
    }

    private EncabezadosDTO obtenerEncabezados() throws SQLException {
        EncabezadosDTO encabezados = new EncabezadosDTO();

        List<String> columnas = new ArrayList<>();
        List<String> columnasEspecial = new ArrayList<>();

        List<EncabezadoEntity> encabezadoEntities = facturaRepository.obtenerEncabezados();

        for (EncabezadoEntity encabezadoEntity : encabezadoEntities) {
            if (encabezadoEntity.getEsEpecial()) {
                columnasEspecial.add(encabezadoEntity.getNombreEncabezado());
            } else {
                columnas.add(encabezadoEntity.getNombreEncabezado());
            }
        }

        encabezados.setColumnas(columnas);
        encabezados.setColumnasEspecial(columnasEspecial);

        return encabezados;
    }
}
