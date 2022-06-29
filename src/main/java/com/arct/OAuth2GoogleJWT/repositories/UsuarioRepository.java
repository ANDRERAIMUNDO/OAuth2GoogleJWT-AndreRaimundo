package com.arct.OAuth2GoogleJWT.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.arct.OAuth2GoogleJWT.domain.Usuario;

public interface UsuarioRepository extends JpaRepository <Usuario, Integer> {
    
    @Transactional(readOnly=true)
	Usuario findByEmail(String email);

    Usuario findByNome(String nome);
}   