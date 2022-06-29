package com.arct.OAuth2GoogleJWT.DTO;

public class OauthToken {
    
    private String acessToken;

    public OauthToken() {

    }

    public OauthToken (String acessToken) {
        super();
        this.acessToken = acessToken;
    }

    public String getAcessToken() {
        return acessToken;
    }

    public void setAcessToken(String acessToken) {
        this.acessToken = acessToken;
    }
}
