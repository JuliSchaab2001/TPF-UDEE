package com.utn.TPFUDEE.DTO;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.utn.TPFUDEE.Models.Client;
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
public class ClientDTO {
     Integer dni;
     String name;
     String mail;
     String lastName;
     List<AddressDTO> addressList;
     UserDTO user;

     public static List<ClientDTO> mapClientListToDTO(List<Client> clientList){
          List<ClientDTO> clientDTOList= new ArrayList<>();
          clientList.forEach( o -> clientDTOList.add(mapClientToDTO(o)));
          return clientDTOList;
     }

     public static ClientDTO mapClientToDTO(Client client) {
          return ClientDTO.builder().
                  dni(client.getDni()).
                  name(client.getName()).
                  mail(client.getMail()).
                  lastName(client.getLastName()).
                  user(UserDTO.mapUserToDTO(client.getUser())).
                  build();
     }

     public static ClientDTO mapClientToDTOwithoutUser(Client client) {
          return ClientDTO.builder().
                  dni(client.getDni()).
                  name(client.getName()).
                  mail(client.getMail()).
                  lastName(client.getLastName()).
                  user(UserDTO.mapUserToDTO(client.getUser())).
                  build();
     }
}
