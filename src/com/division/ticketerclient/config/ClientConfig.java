/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.division.ticketerclient.config;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Evan
 */
public class ClientConfig {

    File config;

    public ClientConfig() {
        this.config = new File(defaultDirectory() + "/.ticketer/config.tfc");
        config.isFile();
    }

    public static String defaultDirectory() {
        String OS = System.getProperty("os.name").toUpperCase();
        if (OS.contains("WIN")) {
            return System.getenv("APPDATA");
        }
        return System.getProperty("user.dir");
    }

    public Config load() {
        FileInputStream fReader;
        ObjectInputStream oReader;
        Config cfg = new Config();
        if (!config.exists()) {
            File directory = new File(defaultDirectory() + "/.ticketer");
            directory.isDirectory();
            directory.mkdirs();
            try {
                config.createNewFile();
                save(cfg);
            } catch (IOException ex) {
            }
        } else {
            try {
                Config tcfg;
                fReader = new FileInputStream(config);
                oReader = new ObjectInputStream(fReader);
                tcfg = (Config) oReader.readObject();
                oReader.close();
                fReader.close();
                return tcfg;
            } catch (Exception ex) {
                Logger.getLogger(ClientConfig.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return null;
    }

    public boolean save(Config cfg) {
        if (!config.exists()) {
            File directory = new File(defaultDirectory() + "/Roaming/.ticketer");
            directory.isDirectory();
            directory.mkdirs();
            try {
                config.createNewFile();
            } catch (IOException ex) {
            }
        } else {
            FileOutputStream fStream = null;
            ObjectOutputStream oStream = null;
            try {
                fStream = new FileOutputStream(config);
                oStream = new ObjectOutputStream(fStream);
                oStream.writeObject(cfg);
                oStream.flush();
            } catch (Exception ex) {
                Logger.getLogger(ClientConfig.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    fStream.close();
                } catch (IOException ex) {
                    Logger.getLogger(ClientConfig.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }
}
