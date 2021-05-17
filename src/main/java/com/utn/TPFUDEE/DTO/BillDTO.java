package com.utn.TPFUDEE.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.utn.TPFUDEE.Models.Bill;
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
public class BillDTO {
    Integer bill_id;
    Integer initialMeasurement;
    Integer finalMeasurement;
    LocalDateTime initialDate;
    LocalDateTime finalDate;
    Double kwConsumed;
    double total;
    Boolean isPaid;
    MeterDTO meter;
    List<MeasurementDTO> measurementList;

    public static List<BillDTO> mapBillListToDTO(List<Bill> billList){
        List<BillDTO> billDTOList = new ArrayList<>();
        billList.forEach(bill -> billDTOList.add( mapBillToDTO(bill)));
        return  billDTOList;
    }

    public static BillDTO mapBillToDTO(Bill bill){
        return BillDTO.builder().
                bill_id(bill.getBill_id()).
                initialMeasurement(bill.getInitialMeasurement()).
                finalMeasurement(bill.getFinalMeasurement()).
                initialDate(bill.getInitialDate()).
                finalDate(bill.getFinalDate()).
                kwConsumed(bill.getKwConsumed()).
                total(bill.getTotal()).
                isPaid(bill.getIsPaid()).
                meter(MeterDTO.mapMeterToDTO(bill.getMeter())).
                build();
    }

}
