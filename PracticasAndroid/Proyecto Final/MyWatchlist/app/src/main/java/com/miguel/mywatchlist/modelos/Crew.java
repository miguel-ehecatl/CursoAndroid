package com.miguel.mywatchlist.modelos;

import com.google.gson.annotations.SerializedName;

/**
 * Creado por Miguel Ángel Hernández Muñoz
 * para Servicios de Software Ehecatl con fecha 05/05/2017.
 */

public class Crew {
    @SerializedName("credit_id")
    public String creditId;
    @SerializedName("department")
    public String department;
    @SerializedName("id")
    public Integer id;
    @SerializedName("job")
    public String job;
    @SerializedName("name")
    public String name;
    @SerializedName("profile_path")
    public String profilePath;

    public String getCreditId() {
        return creditId;
    }

    public void setCreditId(String creditId) {
        this.creditId = creditId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }
}
