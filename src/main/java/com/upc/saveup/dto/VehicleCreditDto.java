package com.upc.saveup.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleCreditDto {
    private int id;
    private String fechaDesembolso;
    private String moneda;
    private String diaPago;
    private String valorVehiculo;
    private String tasaEfectivaAnual;
    private String periodoPago;
    private String tasaDesgravamen;
    private String cuotaInicial;
    private String montoPrestamo;
    private String cuotaFinal;
    private String cuotasAnio;
    private String cuotaMensual;
    private String cok;
    private String van;
    private String tir;
    private String gastoNotarial;
    private String gastoRegistral;
    private String comisionEstudio;
    private String porte;
    private String gastoAdministracion;
    private String periodoTotal;
    private String periodoParcial;
    private String flujoMensual;
    private String tasaSeguroVehicular;
    private String seguroVehicular;
    private String tcea;
    private int userId;

}