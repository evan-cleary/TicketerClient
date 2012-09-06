/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.division.ticketerclient.config;

import java.io.Serializable;

/**
 *
 * @author Evan
 */
public class Config implements Serializable {

    private String username = "";
    private String password = "";
    private String host = "";
    private String port = "9184";
    private boolean notwarn = true;
    private boolean remember = false;


    public String getHost() {
        return host;
    }

    public String getPassword() {
        return password;
    }

    public String getPort() {
        return port;
    }

    public String getUsername() {
        return username;
    }

    public boolean isWarned() {
        return notwarn;
    }

    public boolean isRemembered() {
        return remember;
    }

    public void setDefault() {
        this.password = "";
        this.username = "";
        this.port = "9184";
        this.host = "";
        this.remember = false;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setWarned(boolean warned) {
        this.notwarn = warned;
    }

    public void setRemeber(boolean remember) {
        this.remember = remember;
    }
}
