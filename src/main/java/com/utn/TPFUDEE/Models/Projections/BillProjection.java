package com.utn.TPFUDEE.Models.Projections;

public interface BillProjection {
    Integer getId();
    Integer getInitialMeasurement();
    Integer getfinalMeasurement();
    String getInitialDate();
    String getFinalDate();
    Integer getKw();
    Double getTotal();
    String getTarrifType();
    String getName();
    String getLastName();
    String getStreet();
    Integer getNumber();
}
