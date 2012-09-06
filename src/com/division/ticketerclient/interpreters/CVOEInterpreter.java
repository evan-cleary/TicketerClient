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
public class CVOEInterpreter extends NetInterpreter {
    
    public CVOEInterpreter() {
        super("CVOE");
    }
    
    @Override
    public void run(String data, Socket sock, Net_Framework netFrame) {
        String cleandata = data.replace(NetCase.CVOE.getNetCase(), "");
        netFrame.getLoginForm().getTicketPanel().addToConvo(netFrame.getLoginForm().USRN.getText() + ": " + cleandata);
    }
}
