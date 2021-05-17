package com.utn.TPFUDEE.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.utn.TPFUDEE.Models.Address;
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
public class AddressDTO {

    Integer address_id;
    String street;
    Integer number;
    TariffDTO tariff;
    ClientDTO client;

    public static List<AddressDTO>mapAddressListToDTO(List<Address> addressList){
        List<AddressDTO> addressDTOList = new ArrayList<>();
        addressList.forEach(address -> addressDTOList.add(mapAddressToDTO(address)));
        return addressDTOList;
    }

    public static  AddressDTO mapAddressToDTO(Address address){
        return  AddressDTO.builder().
                    address_id(address.getAddress_id()).
                    street(address.getStreet()).
                    number(address.getNumber()).
                    tariff(TariffDTO.mapTariffToDTO(address.getTariff())).
                    client(ClientDTO.mapClientToDTO(address.getClient())).build();
    }

}
