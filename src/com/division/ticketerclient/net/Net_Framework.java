/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.division.ticketerclient.net;

import com.division.ticketerclient.core.LoginForm;
import com.division.ticketerclient.interpreters.TicketerInterpreter;
import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 *
 * @author Evan
 */
public class Net_Framework {

    private LoginForm sender;
    private Socket connection;
    private boolean active = true;

    public void connectToServer(String data, NetCase netCase, LoginForm sender) {
        this.sender = sender;
        Thread cts = new Thread(new EstablishConnection(data, netCase, sender));
        cts.setDaemon(true);
        cts.setName("EstConnection");
        cts.start();
    }

    public void ListenThread(Socket SSocket, LoginForm sender) {
        Thread listenThread = new Thread(new Listen(SSocket, sender, this));
        listenThread.setDaemon(true);
        listenThread.setName("Listener");
        listenThread.start();
    }

    public class EstablishConnection implements Runnable {

        private String data;
        private NetCase netCase;
        private LoginForm sender;

        EstablishConnection(String data, NetCase netCase, LoginForm sender) {
            this.data = data;
            this.netCase = netCase;
            this.sender = sender;
        }

        @Override
        public void run() {
            SocketAddress sockaddr;
            Socket conn = null;
            int port = Integer.parseInt(sender.PORT.getText());
            try {
                sockaddr = new InetSocketAddress(sender.SRVIP.getText(), port);
                Socket tempSocket = new Socket();
                try {
                    tempSocket.setSoTimeout(1800000);
                    tempSocket.connect(sockaddr);
                } catch (IOException ex) {
                    sender.ErrorUpdate("BLARG", Color.RED);
                }
                if (tempSocket.isConnected()) {
                    conn = tempSocket;
                    connection = conn;
                    sender.setConnected(true);
                    ListenThread(conn, sender);
                }
            } catch (Exception ex) {
                sender.ErrorUpdate("Unable to find server at that IP.", Color.RED);
            }
            try {
                OutputStream os = conn.getOutputStream();
                os.write((netCase.getNetCase() + data).getBytes());
            } catch (Exception ex) {
                sender.ErrorUpdate("Unable to send data to server.", Color.RED);
            }
            if (conn != null) {
            }
        }
    }

    public class Listen implements Runnable {

        public Socket conn;
        public LoginForm login;
        Net_Framework netFrame;

        Listen(Socket Socket, LoginForm login, Net_Framework netFrame) {
            this.conn = Socket;
            this.login = login;
            this.netFrame = netFrame;
        }

        @Override
        public void run() {
            InputStream in = null;
            String data;
            int bytes;
            try {
                in = conn.getInputStream();
            } catch (IOException ie) {
            } catch (NullPointerException npe) {
            }
            while (active) {
                byte[] bytesRec = new byte[1024];
                try {
                    while ((bytes = in.read(bytesRec, 0, bytesRec.length)) != -1) {
                        data = new String(bytesRec, 0, bytes);
                        String netCase = data.substring(0, 4);
                        for (NetCase nc : NetCase.values()) {
                            if (nc.getNetCase().equals(netCase)) {
                                netCase = nc.name();
                                try {
                                    TicketerInterpreter tickint;
                                    String clazzpath = "com.division.ticketerclient.interpreters.";
                                    tickint = (TicketerInterpreter) LoginForm.class.getClassLoader().loadClass(clazzpath + netCase + "Interpreter").newInstance();
                                    tickint.run(data, conn, netFrame);
                                } catch (Exception ex) {
                                    netFrame.getLoginForm().ErrorUpdate("Unknown protocol. Latest version??", Color.RED);
                                    ex.printStackTrace();
                                }
                            }
                        }
                    }
                } catch (IOException ioe) {
                    try {
                        conn.close();
                    } catch (IOException ioe2) {
                    }
                    break;
                }
            }
        }
    }

    public LoginForm getLoginForm() {
        return sender;
    }

    public void sendToServer(String data, NetCase netCase) {
        if (connection != null) {
            try {
                OutputStream os = connection.getOutputStream();
                byte[] tosend = (netCase.getNetCase() + data).getBytes();
                if (tosend.length > 1024) {
                    getLoginForm().ErrorUpdate("Invalid data length.", Color.RED);

                } else {
                    os.write(tosend);
                    os.flush();
                }
            } catch (IOException ex) {
                getLoginForm().ErrorUpdate("Unabled to contact server", Color.RED);
            }
        }
    }

    public Socket getConnection() {
        return connection;
    }
    public boolean isActive(){
        return active;
    }
    public void setActive(boolean active){
        this.active = active;
    }
}
