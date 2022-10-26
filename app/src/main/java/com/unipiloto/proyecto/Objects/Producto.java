package com.unipiloto.proyecto.Objects;

public class Producto {

    private Integer id;
    private String nombre;
    private String Descripcion;
    private String Valor;
    private String Local;
    private String expedicion;
    private String categoria;
    private int imageResource;

    public Producto(){

    }

    public Producto(Integer id, String nombre, String descripcion, String valor, String local, String expedicion, String categoria, int imageResource) {
        this.id = id;
        this.nombre = nombre;
        Descripcion = descripcion;
        Valor = valor;
        Local = local;
        this.expedicion = expedicion;
        this.categoria = categoria;
        this.imageResource = imageResource;
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

    public String getExpedicion() {
        return expedicion;
    }

    public void setExpedicion(String expedicion) {
        this.expedicion = expedicion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }
}
