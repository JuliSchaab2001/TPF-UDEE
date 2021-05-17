package com.utn.TPFUDEE.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.utn.TPFUDEE.Models.Measurement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MeasurementDTO {

    Integer measurement_id;
    LocalDateTime dateTime;
    Integer kw;
    MeterDTO meter;
    BillDTO bill;

    public static List<MeasurementDTO> mapMeasurementToDTOList(List<Measurement> measurementList){
        List<MeasurementDTO> measurementDTOList = new ArrayList<>();
        measurementList.forEach(measurement -> measurementDTOList.add(mapMeasurementToDTO(measurement)));
        return measurementDTOList;
    }

    public static MeasurementDTO mapMeasurementToDTO(Measurement measurement){
        return MeasurementDTO.builder().
                measurement_id(measurement.getMeasurement_id()).
                dateTime(measurement.getDateTime()).
                kw(measurement.getKw()).
                meter(MeterDTO.mapMeterToDTO(measurement.getMeter())).
                bill(BillDTO.mapBillToDTO(measurement.getBill())).
                build();
    }

}
