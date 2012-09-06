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
public class PINFOInterpreter extends NetInterpreter {

    public PINFOInterpreter() {
        super("PINFO");
    }

    @Override
    public void run(String data, Socket sock, Net_Framework netFrame) {
        String cleandata = data.replace(NetCase.PINFO.getNetCase(), "");
        String[] delimit  = cleandata.split("%");
        netFrame.getLoginForm().getTicketPanel().setPlayer(delimit[0]);
        netFrame.getLoginForm().getTicketPanel().setOnline(delimit[1]);
        if(delimit[0].equalsIgnoreCase("true")){
            netFrame.getLoginForm().getTicketPanel().SND.setEnabled(true);
        }
        netFrame.sendToServer(netFrame.getLoginForm().getTicketPanel().getOpenTicket(), NetCase.MSG);
    }
}
