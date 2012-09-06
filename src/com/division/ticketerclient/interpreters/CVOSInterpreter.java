/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.division.ticketerclient.interpreters;

import com.division.ticketerclient.core.TicketPanel;
import com.division.ticketerclient.net.Net_Framework;
import java.net.Socket;

/**
 *
 * @author Evan
 */
public class CVOSInterpreter extends NetInterpreter {

    public CVOSInterpreter() {
        super("CVOS");
    }

    @Override
    public void run(String data, Socket sock, Net_Framework netFrame) {
        TicketPanel TP = netFrame.getLoginForm().getTicketPanel();
        TP.createNotification("Request Accepted", "Your request to talk has been accepted.");
    }
}
