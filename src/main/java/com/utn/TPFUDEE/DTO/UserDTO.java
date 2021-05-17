package com.utn.TPFUDEE.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.utn.TPFUDEE.Models.User;
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
public class UserDTO {
    Integer user_id;
    String userName;
    String password;
    boolean isEmployee;
    ClientDTO client;

    public static List<UserDTO> mapUserToDTOList(List<User> userList){
        List<UserDTO> userDTOList = new ArrayList<>();
        userList.forEach(user -> userDTOList.add(mapUserToDTO(user)));
        return userDTOList;
    }


    public static UserDTO mapUserToDTO(User user) {
        return UserDTO.builder().
                user_id(user.getUser_id()).
                userName(user.getUserName()).
                password(user.getPassword()).
                isEmployee(user.isEmployee()).
                client(ClientDTO.mapClientToDTOwithoutUser(user.getClient())).
                build();
    }
}
