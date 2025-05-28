package com.ortega.webapp.servlet.swfe.repositories;

import com.ortega.webapp.servlet.swfe.models.ImpuestoEntity;
import com.ortega.webapp.servlet.swfe.models.nomina.NominaEntity;
import com.ortega.webapp.servlet.swfe.models.nomina.NominaFormDTO;
import org.apache.poi.ss.formula.functions.T;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NominaRepositoryImpl implements NominaRepository {
    private Connection connection;

    public NominaRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<T> listarNominas(int clienteId, String filtroBusqueda) throws SQLException {
        return List.of();
    }

    @Override
    public NominaEntity obtenerNomina(int nominaId) throws SQLException {
        System.out.println("NominaRepositoryImpl.obtenerNomina " + nominaId);

        NominaEntity nomina = new NominaEntity();

        StringBuilder query = new StringBuilder();
        query.append("SELECT \n" +
                "    n.nomina_id,\n" +
                "    n.empleado_id,\n" +
                "    n.numero_quincena,\n" +
                "    n.imss_nomina,\n" +
                "    n.isr_salario,\n" +
                "    n.subsidio_nomina,\n" +
                "    n.salario_neto,\n" +
                "    n.finiquito_nomina,\n" +
                "    n.cliente_id,\n" +
                "    n.mes_id,\n" +
                "    n.ejercicio_fiscal_id,\n" +
                "    n.dias_laborados,\n" +
                "    e.nombre_empleado,\n" +
                "    e.sueldo_empleado,\n" +
                "    e.fecha_ingreso\n" +
                "FROM \n" +
                "    nominas n\n" +
                "JOIN \n" +
                "    empleados e ON n.empleado_id = e.empleado_id WHERE n.nomina_id = ?");

        try (PreparedStatement ps = connection.prepareStatement(query.toString())) {
            ps.setInt(1, nominaId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    nomina.setNominaid(rs.getInt("nomina_id"));
                    nomina.setEmpeladoId(String.valueOf(rs.getInt("empleado_id")));
                    nomina.setNumeroQuincena(rs.getInt("numero_quincena"));
                    nomina.setImssNomina(rs.getDouble("imss_nomina"));
                    nomina.setIsrSalario(rs.getDouble("isr_salario"));
                    nomina.setSubsidioNomina(rs.getDouble("subsidio_nomina"));
                    nomina.setSalarioNeto(rs.getDouble("salario_neto"));
                    nomina.setClienteId(rs.getInt("cliente_id"));
                    nomina.setMesId(rs.getInt("mes_id"));
                    nomina.setEjercicioFiscalId(rs.getInt("ejercicio_fiscal_id"));
                    nomina.setDiasLaborados(rs.getInt("dias_laborados"));
                    nomina.setNombreEmpleado(rs.getString("nombre_empleado"));
                    nomina.setSalario(rs.getDouble("sueldo_empleado"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nomina;
    }

    @Override
    public List<NominaEntity> obtenerNomina(NominaFormDTO nominaEntity) throws SQLException {
        System.out.println("NominaRepositoryImpl.obtenerNomina " + nominaEntity);
        List<NominaEntity> nominaEntities = new ArrayList<>();

        StringBuilder query = new StringBuilder();
        query.append("SELECT \n" +
                "    n.nomina_id,\n" +
                "    n.empleado_id,\n" +
                "    n.numero_quincena,\n" +
                "    n.imss_nomina,\n" +
                "    n.isr_salario,\n" +
                "    n.subsidio_nomina,\n" +
                "    n.salario_neto,\n" +
                "    n.finiquito_nomina,\n" +
                "    n.cliente_id,\n" +
                "    n.mes_id,\n" +
                "    n.ejercicio_fiscal_id,\n" +
                "    n.dias_laborados,\n" +
                "    e.nombre_empleado,\n" +
                "    e.sueldo_empleado,\n" +
                "    e.fecha_ingreso\n" +
                "FROM \n" +
                "    nominas n\n" +
                "JOIN \n" +
                "    empleados e ON n.empleado_id = e.empleado_id WHERE n.cliente_id = ? AND n.ejercicio_fiscal_id = ?" +
                " AND n.mes_id = ? AND n.numero_quincena = ?");

        try (PreparedStatement statement = connection.prepareStatement(query.toString())) {
            statement.setInt(1, nominaEntity.getClienteId());
            statement.setInt(2, nominaEntity.getEjercicioFiscal());
            statement.setInt(3, nominaEntity.getMes());
            statement.setInt(4, nominaEntity.getQuincena());

            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    NominaEntity nomina = new NominaEntity();
                    nomina.setNominaid(resultSet.getInt("nomina_id"));
                    nomina.setEmpeladoId(String.valueOf(resultSet.getInt("empleado_id")));
                    nomina.setNumeroQuincena(resultSet.getInt("numero_quincena"));
                    nomina.setImssNomina(resultSet.getDouble("imss_nomina"));
                    nomina.setIsrSalario(resultSet.getDouble("isr_salario"));
                    nomina.setSubsidioNomina(resultSet.getDouble("subsidio_nomina"));
                    nomina.setSalarioNeto(resultSet.getDouble("salario_neto"));
                    nomina.setClienteId(resultSet.getInt("cliente_id"));
                    nomina.setMesId(resultSet.getInt("mes_id"));
                    nomina.setEjercicioFiscalId(resultSet.getInt("ejercicio_fiscal_id"));
                    nomina.setDiasLaborados(resultSet.getInt("dias_laborados"));
                    nomina.setNombreEmpleado(resultSet.getString("nombre_empleado"));
                    nomina.setSalario(resultSet.getDouble("sueldo_empleado")) ;

                    nominaEntities.add(nomina);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("NominaRepositoryImpl.obtenerNomina nominaEntities " + nominaEntities);
        return nominaEntities;
    }

    /*@Override
    public List<NominaEntity> obtenerNomina(NominaFormDTO nominaEntity) throws SQLException {
        System.out.println("NominaRepositoryImpl.obtenerNomina " + nominaEntity);
        List<NominaEntity> nominaEntities = new ArrayList<>();

        String condicion = "WHERE n.cliente_id = ? AND n.ejercicio_fiscal_id = ? AND n.mes_id = (\n" +
                "        SELECT MAX(mes_id) \n" +
                "        FROM nominas \n" +
                "        WHERE cliente_id = ? \n" +
                "        AND ejercicio_fiscal_id =  ?\n" +
                "    )" +
                "AND n.numero_quincena = (\n" +
                "        SELECT MAX(numero_quincena) \n" +
                "        FROM nominas \n" +
                "        WHERE cliente_id = ? \n" +
                "        AND ejercicio_fiscal_id = ?\n" +
                "        AND mes_id = (\n" +
                "            SELECT MAX(mes_id) \n" +
                "            FROM nominas \n" +
                "            WHERE cliente_id = ? \n" +
                "            AND ejercicio_fiscal_id = ? \n" +
                "        ));";

        StringBuilder query = new StringBuilder();
        query.append("SELECT \n" +
                "    n.nomina_id,\n" +
                "    n.empleado_id,\n" +
                "    n.numero_quincena,\n" +
                "    n.imss_nomina,\n" +
                "    n.isr_salario,\n" +
                "    n.subsidio_nomina,\n" +
                "    n.salario_neto,\n" +
                "    n.finiquito_nomina,\n" +
                "    n.cliente_id,\n" +
                "    n.mes_id,\n" +
                "    n.ejercicio_fiscal_id,\n" +
                "    n.dias_laborados,\n" +
                "    e.nombre_empleado,\n" +
                "    e.sueldo_empleado,\n" +
                "    e.fecha_ingreso\n" +
                "FROM \n" +
                "    nominas n\n" +
                "JOIN \n" +
                "    empleados e ON n.empleado_id = e.empleado_id ");

        if (nominaEntity.getMes() != 0) {
            condicion = "WHERE n.cliente_id = ? AND n.ejercicio_fiscal_id = ? AND n.mes_id = ?";
        }

        if (nominaEntity.getQuincena() != 0) {
            condicion = "WHERE n.cliente_id = ? AND n.ejercicio_fiscal_id = ? AND n.mes_id = ? AND n.numero_quincena = ?";
        }

        query.append(condicion);

        try (PreparedStatement statement = connection.prepareStatement(query.toString())) {
            statement.setInt(1, nominaEntity.getClienteId());
            statement.setInt(2, nominaEntity.getEjercicioFiscal());
            if ((nominaEntity.getMes() == 0) ) {
                statement.setInt(3, nominaEntity.getClienteId());
                statement.setInt(4, nominaEntity.getEjercicioFiscal());
                statement.setInt(5, nominaEntity.getClienteId());
                statement.setInt(6, nominaEntity.getEjercicioFiscal());
                statement.setInt(7, nominaEntity.getClienteId());
                statement.setInt(8, nominaEntity.getEjercicioFiscal());
            }

            if (nominaEntity.getMes() != 0) {
                statement.setInt(3, nominaEntity.getMes());
            }

            if (nominaEntity.getQuincena() != 0) {
                statement.setInt(4, nominaEntity.getQuincena());
            }

            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    NominaEntity nomina = new NominaEntity();
                    nomina.setNominaid(resultSet.getInt("nomina_id"));
                    nomina.setEmpeladoId(String.valueOf(resultSet.getInt("empleado_id")));
                    nomina.setNumeroQuincena(resultSet.getInt("numero_quincena"));
                    nomina.setImssNomina(resultSet.getDouble("imss_nomina"));
                    nomina.setIsrSalario(resultSet.getDouble("isr_salario"));
                    nomina.setSubsidioNomina(resultSet.getDouble("subsidio_nomina"));
                    nomina.setSalarioNeto(resultSet.getDouble("salario_neto"));
                    nomina.setClienteId(resultSet.getInt("cliente_id"));
                    nomina.setMesId(resultSet.getInt("mes_id"));
                    nomina.setEjercicioFiscalId(resultSet.getInt("ejercicio_fiscal_id"));
                    nomina.setDiasLaborados(resultSet.getInt("dias_laborados"));
                    nomina.setNombreEmpleado(resultSet.getString("nombre_empleado"));
                    nomina.setSalario(resultSet.getDouble("sueldo_empleado")) ;

                    nominaEntities.add(nomina);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("NominaRepositoryImpl.obtenerNomina nominaEntities " + nominaEntities);
        return nominaEntities;
    }*/

    @Override
    public void actualizarNomina(NominaEntity nomina) throws SQLException {
        System.out.println("NominaRepositoryImpl.actualizarNomina " + nomina);

        String query = "UPDATE nominas SET imss_nomina = ?, isr_salario = ?, salario_neto = ?, dias_laborados = ? WHERE nomina_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDouble(1, nomina.getImssNomina());
            statement.setDouble(2, nomina.getIsrSalario());
            statement.setDouble(3, nomina.getSalarioNeto());
            statement.setInt(4, nomina.getDiasLaborados());
            statement.setInt(5, nomina.getNominaid());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void guardarNomina(NominaEntity nomina) throws SQLException {
        System.out.println("NominaRepositoryImpl.guardarNomina " + nomina);
        String query = "INSERT INTO nominas (empleado_id, numero_quincena, imss_nomina, isr_salario, subsidio_nomina, salario_neto, cliente_id, mes_id, ejercicio_fiscal_id, dias_laborados) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, Integer.parseInt(nomina.getEmpeladoId()));
            statement.setInt(2, nomina.getNumeroQuincena());
            statement.setDouble(3, nomina.getImssNomina());
            statement.setDouble(4, nomina.getIsrSalario());
            statement.setDouble(5, nomina.getSubsidioNomina());
            statement.setDouble(6, nomina.getSalarioNeto());
            statement.setInt(7, nomina.getClienteId());
            statement.setInt(8, nomina.getMesId());
            statement.setInt(9, nomina.getEjercicioFiscalId());
            statement.setInt(10, nomina.getDiasLaborados());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarNomina(int nominaId) throws SQLException {

        String query = "DELETE FROM nominas WHERE nomina_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, nominaId);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public double obtenerNominaMensual(ImpuestoEntity impuestoEntity) throws SQLException {
        double nominaMensual = 0;

        String query = "Select SUM(salario_bruto) as salario_bruto FROM nominas WHERE cliente_id = ? AND mes_id = ? AND ejercicio_fiscal_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, impuestoEntity.getClienteId());
            statement.setInt(2, impuestoEntity.getMesId());
            statement.setInt(3, impuestoEntity.getEjercicioId());

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                nominaMensual = resultSet.getDouble("salario_bruto");
            }
        }

        return nominaMensual;
    }

    @Override
    public double obtenerIsrMensual(ImpuestoEntity impuestoEntity) throws SQLException {
        double isrMensual = 0;

        String query = "SELECT SUM(isr_salario) as isr_salario FROM nominas WHERE cliente_id = ? AND mes_id = ? AND ejercicio_fiscal_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, impuestoEntity.getClienteId());
            statement.setInt(2, impuestoEntity.getMesId());
            statement.setInt(3, impuestoEntity.getEjercicioId());

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                isrMensual = resultSet.getDouble("isr_salario");
            }
        }

        return isrMensual;
    }

    @Override
    public double obtenerSubsidioMensual(ImpuestoEntity impuestoEntity) throws SQLException {
        double subsidioMensual = 0;

        String query = "SELECT SUM(subsidio_nomina) as subsidio_nomina FROM nominas WHERE cliente_id = ? AND mes_id = ? AND ejercicio_fiscal_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, impuestoEntity.getClienteId());
            statement.setInt(2, impuestoEntity.getMesId());
            statement.setInt(3, impuestoEntity.getEjercicioId());

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                subsidioMensual = resultSet.getDouble("subsidio_nomina");
            }
        }

        return subsidioMensual;
    }
}
