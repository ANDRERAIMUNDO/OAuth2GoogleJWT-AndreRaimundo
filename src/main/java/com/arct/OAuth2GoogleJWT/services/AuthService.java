package com.arct.OAuth2GoogleJWT.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.arct.OAuth2GoogleJWT.domain.Usuario;
import com.arct.OAuth2GoogleJWT.repositories.UsuarioRepository;
import com.arct.OAuth2GoogleJWT.services.exception.ObjectNotFoundException;

@Service
public class AuthService {
  
    @Autowired
    UsuarioRepository registroRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private Random random = new Random();

    public void sendNewPassword (String email) {
        Usuario usuario  = registroRepository.findByEmail(email);
        if (usuario == null) {
            throw new ObjectNotFoundException("Email não encontrado! .");
        }

        String newPassword = newPassword();
        usuario.setPassword(bCryptPasswordEncoder.encode(newPassword));

        registroRepository.save(usuario);
    }

    private String newPassword() {
		char[] vet = new char[10];
		for (int i=0; i<10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = random.nextInt(3);
		if (opt == 0) { // gera um digito
			return (char) (random.nextInt(10) + 48);
		}
		else if (opt == 1) { // gera letra maiuscula
			return (char) (random.nextInt(26) + 65);
		}
		else { // gera letra minuscula
			return (char) (random.nextInt(26) + 97);
		}
    }
}