package com.miguel.mywatchlist.modelos;

/**
 * Created by miguelangel on 4/22/17.
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
