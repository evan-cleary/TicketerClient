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
public class LRQInterpreter extends NetInterpreter {

    public LRQInterpreter() {
        super("LRQ");
    }

    @Override
    public void run(String data, Socket sock, Net_Framework netFrame) {
        String cleandata = data.replace(NetCase.LRQ.getNetCase(), "");
        String[] delimit = cleandata.split("%");
        netFrame.getLoginForm().getTicketPanel().clearTickList();
        for (String s : delimit) {
            if (!s.equals("")) {
                netFrame.getLoginForm().getTicketPanel().addOpenTicket(s);
            }
        }
        netFrame.sendToServer(netFrame.getLoginForm().USRN.getText(), NetCase.ASNG);
    }
}
