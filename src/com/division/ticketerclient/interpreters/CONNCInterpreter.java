/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.division.ticketerclient.interpreters;

import com.division.ticketerclient.core.Rank;
import com.division.ticketerclient.net.NetCase;
import com.division.ticketerclient.net.Net_Framework;
import java.awt.Color;
import java.net.Socket;

/**
 *
 * @author Evan
 */
public class CONNCInterpreter extends NetInterpreter {

    public CONNCInterpreter() {
        super("CONNC");
    }

    @Override
    public void run(String data, Socket sock, Net_Framework netFrame) {
        netFrame.getLoginForm().ErrorUpdate("Successfully connected to server.", Color.GREEN);
        String cleandata = data.replace(NetCase.CONNC.getNetCase(), "");
        Rank rank = getRank(cleandata);
        netFrame.getLoginForm().launchTicketPanel(rank);
    }

    public Rank getRank(String rawRank) {
        for (Rank rank : Rank.values()) {
            if (rank.getRank().equalsIgnoreCase(rawRank)) {
                return rank;
            }
        }
        return Rank.GUEST;
    }
}
