/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.division.ticketerclient.interpreters;

import com.division.ticketerclient.net.Net_Framework;
import java.awt.Color;
import java.net.Socket;

/**
 *
 * @author Evan
 */
public class CONNRInterpreter extends NetInterpreter {

    public CONNRInterpreter() {
        super("CONNR");
    }

    @Override
    public void run(String data, Socket sock, Net_Framework netFrame) {
        netFrame.getLoginForm().ErrorUpdate("Invalid username/password.", Color.RED);
    }
}
