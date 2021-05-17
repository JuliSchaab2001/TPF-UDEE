package com.utn.TPFUDEE.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;

import com.utn.TPFUDEE.Models.Tariff;
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
public class TariffDTO {

    Integer tariff_id;
    String type;
    Double price;
    List<AddressDTO> address;

    public static List<TariffDTO> mapTariffListToDTO(List<Tariff> tariffList) {
        List<TariffDTO> tariffDTOList = new ArrayList<>();
        tariffList.forEach(tariff -> tariffDTOList.add(mapTariffToDTO(tariff)));
        return tariffDTOList;
    }

    public static TariffDTO mapTariffToDTO(Tariff tariff) {
        return TariffDTO.builder().
                tariff_id(tariff.getTariff_id()).
                type(tariff.getType()).
                price(tariff.getPrice()).
                build();
    }
}
