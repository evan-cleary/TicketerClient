/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.division.ticketerclient.interpreters;

import com.division.ticketerclient.net.NetCase;
import com.division.ticketerclient.net.Net_Framework;
import java.awt.Toolkit;
import java.net.Socket;

/**
 *
 * @author Evan
 */
public class NOTIFInterpreter extends NetInterpreter {

    public NOTIFInterpreter() {
        super("NOTIF");
    }

    @Override
    public void run(String data, Socket sock, Net_Framework netFrame) {
        String cleandata = data.replace(NetCase.NOTIF.getNetCase(), "");
        String[] delimit = cleandata.split("%");
        Toolkit.getDefaultToolkit().beep();
        netFrame.getLoginForm().getTicketPanel().createNotification(delimit[0], delimit[1]);
        netFrame.getLoginForm().getTicketPanel().toFront();
    }
}
