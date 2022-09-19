package com.unipiloto.proyecto;

public class Usuario {

    private Integer id;
    private String nombre;
    private String email;
    private String password;
    private Integer genero;
    private Integer rol;

    public Usuario(){

    }

    public Usuario(Integer id, String nombre, String email, String password, Integer genero, Integer rol) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.genero = genero;
        this.rol = rol;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getGenero() {
        return genero;
    }

    public void setGenero(Integer genero) {
        this.genero = genero;
    }

    public Integer getRol() {
        return rol;
    }

    public void setRol(Integer rol) {
        this.rol = rol;
    }
}
