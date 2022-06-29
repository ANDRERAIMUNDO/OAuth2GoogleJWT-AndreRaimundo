package com.arct.OAuth2GoogleJWT.services.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.arct.OAuth2GoogleJWT.domain.Usuario;
import com.arct.OAuth2GoogleJWT.repositories.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario == null) {
            throw new UsernameNotFoundException(email);
        }
         return new UserSS(usuario.getId(), usuario.getEmail(), usuario.getPassword(), usuario.getPerfis());
    }

    //talvez necessidade de implementar metodor para verificar oauth2
    
}
