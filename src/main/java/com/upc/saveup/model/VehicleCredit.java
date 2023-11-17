package com.upc.saveup.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "vehicle_credit")
public class VehicleCredit{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "fecha_desembolso", length = 20, nullable = false)
    private String fechaDesembolso;
    @Column(name = "moneda", length = 20, nullable = false)
    private String moneda;
    @Column(name = "dia_pago", length = 20, nullable = false)
    private String diaPago;
    @Column(name = "valor_vehiculo", length = 20, nullable = false)
    private String valorVehiculo;
    @Column(name = "tasa_efectiva_anual", length = 20, nullable = false)
    private String tasaEfectivaAnual;

    @Column(name = "periodo_pago", length = 20, nullable = false)
    private String periodoPago;
    @Column(name = "tasa_desgravamen", length = 20, nullable = false)
    private String tasaDesgravamen;
    @Column(name = "cuota_inicial", length = 20, nullable = false)
    private String cuotaInicial;
    @Column(name = "monto_prestamo", length = 20, nullable = false)
    private String montoPrestamo;
    @Column(name = "cuota_final", length = 20, nullable = false)
    private String cuotaFinal;
    @Column(name = "cuotas_anio", length = 20, nullable = false)
    private String cuotasAnio;
    @Column(name = "cuota_mensual", length = 20, nullable = false)
    private String cuotaMensual;
    @Column(name = "cok", length = 20, nullable = false)
    private String cok;
    @Column(name = "van", length = 20, nullable = false)
    private String van;
    @Column(name = "tir", length = 20, nullable = false)
    private String tir;


    @Column(name = "gasto_notarial", length = 20, nullable = false)
    private String gastoNotarial;
    @Column(name = "gasto_registral", length = 20, nullable = false)
    private String gastoRegistral;
    @Column(name = "comision_estudio", length = 20, nullable = false)
    private String comisionEstudio;
    @Column(name = "porte", length = 20, nullable = false)
    private String porte;
    @Column(name = "gasto_administracion", length = 20, nullable = false)
    private String gastoAdministracion;
    @Column(name = "periodo_total", length = 20, nullable = false)
    private String periodoTotal;
    @Column(name = "periodo_parcial", length = 20, nullable = false)
    private String periodoParcial;
    @Column(name = "flujo_mensual", length = 20, nullable = false)
    private String flujoMensual;
    @Column(name = "tasa_seguro_vehicular", length = 20, nullable = false)
    private String tasaSeguroVehicular;
    @Column(name = "seguro_vehicular", length = 20, nullable = false)
    private String seguroVehicular;
    @Column(name = "tcea", length = 20, nullable = false)
    private String tcea;
    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_vehicle_credit_users"))
    private User user;
}
