package com.arct.OAuth2GoogleJWT.services;

import org.springframework.stereotype.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.arct.OAuth2GoogleJWT.repositories.UsuarioRepository;
import com.arct.OAuth2GoogleJWT.services.exception.ObjectNotFoundException;
import com.arct.OAuth2GoogleJWT.domain.Usuario;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository  usuarioRepository;

    public Usuario findId (Integer id) {
        Optional <Usuario> obj = usuarioRepository.findById(id);
        return obj.orElseThrow(()-> new 
        ObjectNotFoundException("Registro não encontrado Id: "+ id + " Tipo: "
         + Usuario.class.getName()));
    }
}
