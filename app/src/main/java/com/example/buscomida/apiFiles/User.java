package com.example.buscomida.apiFiles;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("NombreUsuario")
    private String user;

    @SerializedName("Password")
    private String password;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String user, String password) {
        this.user = user;
        this.password = password;
    }
}
