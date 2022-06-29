package com.arct.OAuth2GoogleJWT.services;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.arct.OAuth2GoogleJWT.domain.Usuario;
import com.arct.OAuth2GoogleJWT.repositories.UsuarioRepository;

@Service
public class StartDataBaseService {
    
    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    //@Bean 
    //public BCryptPasswordEncoder bCryptPasswordEncoder() {
        //return new BCryptPasswordEncoder(); 
    //}

    public void instantiateTestDataBase () throws ParseException {

        Usuario usuario1  = new Usuario(
            null, "andreraimundo.me@gmail.com",
            bCryptPasswordEncoder.encode("13badroke"),
            "Andre Raimundo Rodrigues de Oliveira");
        
        Usuario usuario2 = new Usuario(
            null,
            "robertolcm92@gmail.com",
            bCryptPasswordEncoder.encode("1402"),
            "Luiz Roberto Cabral Moraes");
        
        repository.saveAll(Arrays.asList(usuario1, usuario2));
    }   
}