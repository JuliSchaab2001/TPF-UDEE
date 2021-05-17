package com.utn.TPFUDEE.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.utn.TPFUDEE.Models.Meter;
import com.utn.TPFUDEE.Models.MeterType;
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
public class MeterTypeDTO {

    String model;
    String brand;
    List<MeterDTO> meterList;
    Integer measurerType_id;


    public static List<MeterTypeDTO> mapMeterTypeToPDOList(List<MeterType> meterTypeList){
        List<MeterTypeDTO> meterTypeDTOList = new ArrayList<>();
        meterTypeList.forEach(meterType -> meterTypeDTOList.add(mapMeterTypeToPDO(meterType)));
        return meterTypeDTOList;
    }

    public static MeterTypeDTO mapMeterTypeToPDO(MeterType meterType) {
        return MeterTypeDTO.builder().
                measurerType_id(meterType.getMeasurerType_id()).
                model(meterType.getModel()).
                brand(meterType.getBrand()).
                build();
    }


}
