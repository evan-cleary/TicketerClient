/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.division.ticketerclient.interpreters;

import com.division.ticketerclient.net.Net_Framework;
import java.net.Socket;

/**
 *
 * @author Evan
 */
public class CTOInterpreter extends NetInterpreter {

    public CTOInterpreter() {
        super("CTO");
    }

    @Override
    public void run(String data, Socket sock, Net_Framework netFrame) {
        netFrame.getLoginForm().getTicketPanel().createNotification("Disconnected.", "The ticketer server has suddenly dropped connection.");
        netFrame.getLoginForm().getTicketPanel().dispose();
        netFrame.getLoginForm().setVisible(true);
        netFrame.getLoginForm().toFront();
    }
}
