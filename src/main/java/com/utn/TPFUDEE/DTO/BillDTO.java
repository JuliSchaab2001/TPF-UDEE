package com.utn.TPFUDEE.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.utn.TPFUDEE.Models.Measurement;
import com.utn.TPFUDEE.Models.Meter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BillDTO {
    Integer bill_id;
    Integer initialMeasurement;
    Integer finalMeasurement;
    LocalDateTime initialDate;
    LocalDateTime finalDate;
    Double kwConsumed;
    double total;
    Boolean isPaid;
    Meter meter;
    List<Measurement> measurementList;

}
