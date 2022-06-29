package com.arct.OAuth2GoogleJWT.enums;

public enum Perfil {

    COMUM(1, "ROLE_COMUM"),
	ADMIN(2, "ROLE_ADMIN");

    private int cod;
    private String descricao;

    private Perfil(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }
    
    public static Perfil toEnum (Integer cod) {
        if (cod  == null) {
            return null;
        }
        for (Perfil x: Perfil.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id invalido: " + cod);
    }
}  