package com.unipiloto.proyecto.Objects;

public class Producto {

    private Integer id;
    private String nombre;
    private String Descripcion;
    private String Valor;
    private String Local;
    private String categoria;

    public Producto(){

    }

    public Producto(Integer id, String nombre, String descripcion, String valor, String local, String categoria) {
        this.id = id;
        this.nombre = nombre;
        Descripcion = descripcion;
        Valor = valor;
        Local = local;
        this.categoria = categoria;
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

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getValor() {
        return Valor;
    }

    public void setValor(String valor) {
        Valor = valor;
    }

    public String getLocal() {
        return Local;
    }

    public void setLocal(String local) {
        Local = local;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
