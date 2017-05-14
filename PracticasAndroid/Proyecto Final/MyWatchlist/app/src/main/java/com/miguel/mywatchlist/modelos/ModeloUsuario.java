package com.miguel.mywatchlist.modelos;

/**
 * Creado por Miguel Ángel Hernández Muñoz
 * para Servicios de Software Ehecatl con fecha 12/04/2017.
 */

public class ModeloUsuario {

    private int id;
    private String email;
    private String pass;
    private String indicio;
    private int sesion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getIndicio() {
        return indicio;
    }

    public void setIndicio(String indicio) {
        this.indicio = indicio;
    }

    public int getSesion() {
        return sesion;
    }

    public void setSesion(int sesion) {
        this.sesion = sesion;
    }
}
