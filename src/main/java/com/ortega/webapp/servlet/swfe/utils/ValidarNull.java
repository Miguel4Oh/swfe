package com.ortega.webapp.servlet.swfe.utils;

public class ValidarNull {

    public static String getValor(String valor) {
        return valor == null ? "" : valor;
    }

    public static Integer getValor(Integer valor) {
        return valor == null ? 0 : valor;
    }

    public static Double getValor(Double valor) {
        return valor == null ? 0.0 : valor;
    }

}
