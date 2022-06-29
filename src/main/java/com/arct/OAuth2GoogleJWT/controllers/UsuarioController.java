package com.arct.OAuth2GoogleJWT.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.arct.OAuth2GoogleJWT.DTO.OauthToken;
import com.arct.OAuth2GoogleJWT.domain.Usuario;
import com.arct.OAuth2GoogleJWT.services.UsuarioService;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity <Usuario> find (@PathVariable Integer id){
      Usuario obj = usuarioService.findId(id);
     return ResponseEntity.ok().body(obj); 
    }

	  @RequestMapping(value = "/token/oauth2", method = RequestMethod.GET)
    public ResponseEntity <OauthToken> find(Model model,
    @RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient) {
    OauthToken aux = new OauthToken(authorizedClient.getAccessToken().getTokenValue());
    OauthToken obj = aux;
    return ResponseEntity.ok().body(obj); 
  }
}