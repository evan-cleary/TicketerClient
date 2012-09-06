/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.division.ticketerclient.interpreters;

import com.division.ticketerclient.net.NetCase;
import com.division.ticketerclient.net.Net_Framework;
import java.net.Socket;

/**
 *
 * @author Evan
 */
public class ASNGInterpreter extends NetInterpreter {

    public ASNGInterpreter() {
        super("ASNG");
    }

    @Override
    public void run(String data, Socket sock, Net_Framework netFrame) {
        String cleandata = data.replace(NetCase.ASNG.getNetCase(), "");
        String[] delimit = cleandata.split("%");
        netFrame.getLoginForm().getTicketPanel().clearASGNList();
        for (String s : delimit) {
            if (!s.equals("")) {
                netFrame.getLoginForm().getTicketPanel().addAsgnTicket(s);
            }
        }
        netFrame.sendToServer("", NetCase.RFLG);
    }
}
