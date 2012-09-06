/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.division.ticketerclient.interpreters;

import com.division.ticketerclient.core.SUPanel;
import com.division.ticketerclient.net.NetCase;
import com.division.ticketerclient.net.Net_Framework;
import java.net.Socket;

/**
 *
 * @author Evan
 */
public class GMLInterpreter extends NetInterpreter {

    public GMLInterpreter() {
        super("GML");
    }

    @Override
    public void run(String data, Socket sock, Net_Framework netFrame) {
        SUPanel suPanel = netFrame.getLoginForm().getTicketPanel().getSUPanel();
        String cleandata = data.replace(NetCase.GML.getNetCase(), "");
        String[] delimit = cleandata.split("%");
        for (String s : delimit) {
            if (!s.equals("")) {
                suPanel.addGM(s);
            }
        }
    }
}
