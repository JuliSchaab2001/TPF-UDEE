package com.utn.TPFUDEE.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.utn.TPFUDEE.Exceptions.Exist.UserExistException;
import com.utn.TPFUDEE.Exceptions.NoContent.UserNoContentException;
import com.utn.TPFUDEE.Exceptions.NotFound.UserNotFoundException;
import com.utn.TPFUDEE.Models.DTO.LoginResponseDTO;
import com.utn.TPFUDEE.Models.DTO.LoginUserDTO;
import com.utn.TPFUDEE.Models.DTO.UserDTO;
import com.utn.TPFUDEE.Models.User;
import com.utn.TPFUDEE.Services.UserService;
import com.utn.TPFUDEE.Utils.EntityURLBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;
import static com.utn.TPFUDEE.Utils.Constants.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final String USER_PATH = "user";

    @Autowired
    private UserService userService;
    private final ObjectMapper objectMapper;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ObjectMapper objectMapper, ModelMapper modelMapper) {
        this.userService = userService;
        this.objectMapper = objectMapper;
        this.modelMapper = modelMapper;
    }

    /*@GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Integer id) throws UserNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).header("Nombre", "Cuerpo").body( userService.getById(id));
    }*/

    @PostMapping("/")
    public ResponseEntity add(@RequestBody User user) throws UserExistException, UserNoContentException {
        return ResponseEntity.status(HttpStatus.CREATED).location(EntityURLBuilder.buildURL(USER_PATH,userService.add(user).getUserId())).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Integer id) throws UserNotFoundException {
        userService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).header("Aca", "Salio todo en orden man").build();
    }

    // Esta bien que sea get? esta bien recibir asi userName y password?
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginUserDTO userDTO){
        User user = userService.getUserByUserNameAndPassword(userDTO.getUserName(), userDTO.getPassword());
        if(user!=null){
            return ResponseEntity.ok(LoginResponseDTO.builder().token(this.generateToken(user)).build());
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    private String generateToken(User user) {
        try {
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

            if(user.isEmployee())
                 grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("EMPLOYEE");
            else
                 grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("CLIENT");
            String token = Jwts
                    .builder()
                    .setId("JWT")
                    .setSubject(user.getUserName())
                    .claim("user", objectMapper.writeValueAsString(modelMapper.map(user, UserDTO.class)))/*Aca tengo que usar un converter de moddelMapper* asi no devuelvo la contraseña, No puedo pasar el dto directamente por que no tengo que devolver el isEmployee*/
                    .claim("authorities",grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + 1000000))
                    .signWith(SignatureAlgorithm.HS512, JWT_SECRET.getBytes()).compact();
            return  token;
        } catch(Exception e) {
            return "dummy";
        }
    }


}