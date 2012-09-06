/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.division.ticketerclient.interpreters;

import com.division.ticketerclient.core.TicketPanel;
import com.division.ticketerclient.net.NetCase;
import com.division.ticketerclient.net.Net_Framework;
import java.net.Socket;

/**
 *
 * @author Evan
 */
public class UFLGInterpreter extends NetInterpreter {

    public UFLGInterpreter() {
        super("UFLG");
    }

    @Override
    public void run(String data, Socket sock, Net_Framework netFrame) {
        String cleandata = data.replace(NetCase.UFLG.getNetCase(), "");
        String[] delimit = cleandata.split("%");
        TicketPanel TP = netFrame.getLoginForm().getTicketPanel();
        TP.createNotification("Ticket id: " + delimit[0], delimit[1]);
    }
}
