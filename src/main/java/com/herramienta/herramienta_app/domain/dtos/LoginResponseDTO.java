package com.herramienta.herramienta_app.domain.dtos;

public class LoginResponseDto {
      private String token;
    private UsuarioDto usuario;
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public UsuarioDto getUsuario() {
        return usuario;
    }
    public void setUsuario(UsuarioDto usuario) {
        this.usuario = usuario;
    }




}
