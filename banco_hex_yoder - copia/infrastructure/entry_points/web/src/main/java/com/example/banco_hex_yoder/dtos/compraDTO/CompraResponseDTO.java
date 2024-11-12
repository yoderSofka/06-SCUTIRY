package com.example.banco_hex_yoder.dtos.compraDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CompraResponseDTO {
    private String cuentaOrigenEncriptada;
    private BigDecimal saldoActualizado;
    private Detalle detalle;

    public CompraResponseDTO() {}

    public CompraResponseDTO(String cuentaOrigenEncriptada, BigDecimal saldoActualizado, Detalle detalle) {
        this.cuentaOrigenEncriptada = cuentaOrigenEncriptada;
        this.saldoActualizado = saldoActualizado;
        this.detalle = detalle;
    }

    public String getCuentaOrigenEncriptada() {
        return cuentaOrigenEncriptada;
    }

    public void setCuentaOrigenEncriptada(String cuentaOrigenEncriptada) {
        this.cuentaOrigenEncriptada = cuentaOrigenEncriptada;
    }

    public BigDecimal getSaldoActualizado() {
        return saldoActualizado;
    }

    public void setSaldoActualizado(BigDecimal saldoActualizado) {
        this.saldoActualizado = saldoActualizado;
    }

    public Detalle getDetalle() {
        return detalle;
    }

    public void setDetalle(Detalle detalle) {
        this.detalle = detalle;
    }

    public static class Detalle {
        private BigDecimal montoCompra;
        private BigDecimal costoCompra;
        private String tipoCompra;
        private String cuentaOrigen;
        private LocalDateTime fechaTransaccion;

        public Detalle() {}

        public Detalle(BigDecimal montoCompra, BigDecimal costoCompra, String tipoCompra, String cuentaOrigen, LocalDateTime fechaTransaccion) {
            this.montoCompra = montoCompra;
            this.costoCompra = costoCompra;
            this.tipoCompra = tipoCompra;
            this.cuentaOrigen = cuentaOrigen;
            this.fechaTransaccion = fechaTransaccion;
        }

        public BigDecimal getMontoCompra() {
            return montoCompra;
        }

        public void setMontoCompra(BigDecimal montoCompra) {
            this.montoCompra = montoCompra;
        }

        public BigDecimal getCostoCompra() {
            return costoCompra;
        }

        public void setCostoCompra(BigDecimal costoCompra) {
            this.costoCompra = costoCompra;
        }

        public String getTipoCompra() {
            return tipoCompra;
        }

        public void setTipoCompra(String tipoCompra) {
            this.tipoCompra = tipoCompra;
        }

        public String getCuentaOrigen() {
            return cuentaOrigen;
        }

        public void setCuentaOrigen(String cuentaOrigen) {
            this.cuentaOrigen = cuentaOrigen;
        }

        public LocalDateTime getFechaTransaccion() {
            return fechaTransaccion;
        }

        public void setFechaTransaccion(LocalDateTime fechaTransaccion) {
            this.fechaTransaccion = fechaTransaccion;
        }
    }
}
