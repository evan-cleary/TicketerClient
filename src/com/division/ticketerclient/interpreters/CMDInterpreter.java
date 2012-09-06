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
public class CMDInterpreter extends NetInterpreter {

    public CMDInterpreter() {
        super("CMD");
    }

    @Override
    public void run(String data, Socket sock, Net_Framework netFrame) {
        String cleandata = data.replace(NetCase.CMD.getNetCase(), "");
        String[] delimit = cleandata.split("%");
        boolean success = Boolean.parseBoolean(delimit[0]);
        if (success) {
            netFrame.getLoginForm().getTicketPanel().addToConvo("Command successfully run.");
        } else {
            if (delimit.length == 2) {
                netFrame.getLoginForm().getTicketPanel().addToConvo(delimit[1]);
            } else {
                netFrame.getLoginForm().getTicketPanel().addToConvo("Command failed to run.");
            }
        }
    }
}
