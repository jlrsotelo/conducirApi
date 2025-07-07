package com.licencia.conducir.util;

import java.util.Base64;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.licencia.conducir.entity.UserEntity;
import com.licencia.conducir.repository.UserRepository;

@Service
public class Auth {
    private final UserRepository userRepository;

    public Auth(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean validate(String authz){
        String credenciales=authz.substring(6);
        byte[] decodedBytes = Base64.getDecoder().decode(credenciales);
        String decode = new String(decodedBytes);
        String []argCred=decode.split(":");
        String user=argCred[0];
        String password=argCred[1];

        Optional<UserEntity> optUserEntity=userRepository.validar(user,AppClasicEncrypt.encrypt(password));
        return optUserEntity.isPresent();
    }
}
