/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.division.ticketerclient.interpreters;

import com.division.ticketerclient.core.Rank;
import com.division.ticketerclient.core.TicketPanel;
import com.division.ticketerclient.net.NetCase;
import com.division.ticketerclient.net.Net_Framework;
import java.net.Socket;

/**
 *
 * @author Evan
 */
public class MSGInterpreter extends NetInterpreter {
    
    public MSGInterpreter() {
        super("MSG");
    }
    
    @Override
    public void run(String data, Socket sock, Net_Framework netFrame) {
        TicketPanel TP = netFrame.getLoginForm().getTicketPanel();
        String cleandata = data.replace(NetCase.MSG.getNetCase(), "");
        String[] delimit = cleandata.split("%");
        TP.setMessage(delimit[0]);
        TP.setSubject(delimit[1]);
        TP.setComment(delimit[2]);
        if (TP.getRank() == Rank.SUPERUSER) {
            TP.SUP.setEnabled(true);
        } else {
            TP.SUP.setEnabled(false);
        }
        netFrame.sendToServer(netFrame.getLoginForm().getTicketPanel().getOpenTicket(), NetCase.RSTATUS);
    }
}
