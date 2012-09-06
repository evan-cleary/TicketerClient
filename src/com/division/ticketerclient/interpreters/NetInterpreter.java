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
public class NetInterpreter implements TicketerInterpreter {

    String name;

    public NetInterpreter(final String name) {
        this.name = name;
    }

    @Override
    public String getname() {
        return name;
    }

    @Override
    public void run(String data, Socket sock, Net_Framework netframe) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
