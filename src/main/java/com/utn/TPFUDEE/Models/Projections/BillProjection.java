package com.utn.TPFUDEE.Models.Projections;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface BillProjection {
    Integer getBillId();
    Double getInitialMeasurement();
    Double getFinalMeasurement();
    String getInitialDate();
    String getFinalDate();
    String getExpirationDate();
    Double getkwConsumed();
    Double getTotal();
}
