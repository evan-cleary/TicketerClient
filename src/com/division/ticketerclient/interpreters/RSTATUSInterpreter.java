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
public class RSTATUSInterpreter extends NetInterpreter {

    public RSTATUSInterpreter() {
        super("RSTATUS");
    }
    
    @Override
    public void run(String data, Socket sock, Net_Framework netFrame){
        String cleandata = data.replace(NetCase.RSTATUS.getNetCase(),"");
        if(!cleandata.equals("")){
            netFrame.getLoginForm().getTicketPanel().setStatus(cleandata);
            if(cleandata.equalsIgnoreCase("active")){
                netFrame.getLoginForm().getTicketPanel().enableControls();
            } else{
                netFrame.getLoginForm().getTicketPanel().disableControls();
            }
        }
    }
}
