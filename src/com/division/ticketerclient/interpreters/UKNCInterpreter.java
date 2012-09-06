/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.division.ticketerclient.interpreters;

import com.division.ticketerclient.net.Net_Framework;
import java.net.Socket;

/**
 *
 * @author Evan
 */
public class UKNCInterpreter extends NetInterpreter{
    
    public UKNCInterpreter(){
        super("UKNC");
    }
    
    @Override
    public void run(String data, Socket sock, Net_Framework netFrame){
        netFrame.getLoginForm().getTicketPanel().createNotification("Unknown Protocol", "You have submitted an unknown protocol. Latest version?");
    }
}
