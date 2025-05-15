package com.herramienta.herramienta_app.domain.dtos;

public class LoginResponseDTO {
      private String token;
    private UsuarioDTO usuario;
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public UsuarioDTO getUsuario() {
        return usuario;
    }
    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }




}
