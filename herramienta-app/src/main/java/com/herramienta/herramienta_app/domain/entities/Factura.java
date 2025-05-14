package com.herramienta.herramienta_app.domain.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Factura {
      private Long idFactura;
    private Pago pago;
    private String rfcEmisor;
    private String rfcReceptor;
    private BigDecimal subtotal;
    private BigDecimal iva;
    private BigDecimal total;
    private String xml;
    private byte[] pdf;
    private LocalDateTime fecha;
    public Long getIdFactura() {
        return idFactura;
    }
    public void setIdFactura(Long idFactura) {
        this.idFactura = idFactura;
    }
    public Pago getPago() {
        return pago;
    }
    public void setPago(Pago pago) {
        this.pago = pago;
    }
    public String getRfcEmisor() {
        return rfcEmisor;
    }
    public void setRfcEmisor(String rfcEmisor) {
        this.rfcEmisor = rfcEmisor;
    }
    public String getRfcReceptor() {
        return rfcReceptor;
    }
    public void setRfcReceptor(String rfcReceptor) {
        this.rfcReceptor = rfcReceptor;
    }
    public BigDecimal getSubtotal() {
        return subtotal;
    }
    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
    public BigDecimal getIva() {
        return iva;
    }
    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }
    public BigDecimal getTotal() {
        return total;
    }
    public void setTotal(BigDecimal total) {
        this.total = total;
    }
    public String getXml() {
        return xml;
    }
    public void setXml(String xml) {
        this.xml = xml;
    }
    public byte[] getPdf() {
        return pdf;
    }
    public void setPdf(byte[] pdf) {
        this.pdf = pdf;
    }
    public LocalDateTime getFecha() {
        return fecha;
    }
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }



}
