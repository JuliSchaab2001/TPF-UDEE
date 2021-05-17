package com.utn.TPFUDEE.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.utn.TPFUDEE.Models.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MeterDTO {

    Integer meter_id;
    Integer serialNumber;
    MeterTypeDTO meterType;
    AddressDTO address;
    List<BillDTO> billList;
    List<MeasurementDTO> measurementList;

    public static List<MeterDTO> mapMeterToDTOList(List<Meter> meterList) {
        List<MeterDTO> meterDTOList = new ArrayList<>();
        meterList.forEach(meter -> meterDTOList.add(mapMeterToDTO(meter)));
        return meterDTOList;
    }

    public static MeterDTO mapMeterToDTO(Meter meter) {
        return MeterDTO.builder().
                meter_id(meter.getMeter_id()).
                serialNumber(meter.getSerialNumber()).
                meterType(MeterTypeDTO.mapMeterTypeToPDO(meter.getMeterType())).
                address(AddressDTO.mapAddressToDTO(meter.getAddress())).
                build();
    }
}
