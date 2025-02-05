package com.example.localsocketchatui;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.net.InetAddress;

public class UserSocketSingleton {


    private String hostName = "Ovramenko"; //DESKTOP-4S5C71
    private int portNumber = 8080;
    private String deskTopName = InetAddress.getLocalHost().getHostName();
    private Socket serverSocket = new Socket(hostName,portNumber);

    private static UserSocketSingleton instance;

    private UserSocketSingleton() throws IOException {
        // Initialize any required resources
    }

    public static synchronized UserSocketSingleton getInstance() throws IOException {
        if (instance == null) {
            instance = new UserSocketSingleton();
        }
        return instance;
    }

    public Socket GetSocket() throws IOException {
        return serverSocket;
    }

    public void StartUpUser() throws IOException {

        try
        {
            System.out.println("TEST: StartUpUser = checkpoint a");

            System.out.println("TEST: StartUpUser = checkpoint b");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(this.serverSocket.getInputStream()));
            String fromServer;
            PrintWriter out = new PrintWriter(serverSocket.getOutputStream(),true);

            String message = "Im Connected Tal gay !";
            out.println(deskTopName);



            //fromServer = in.readLine();
            //assert !fromServer.isEmpty();

        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                    hostName);
            System.exit(1);
        }
    }

    public String SendAndReciveContentToServer(String message) throws IOException {
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(this.serverSocket.getInputStream()));
            PrintWriter out = new PrintWriter(serverSocket.getOutputStream(), true);

            out.println(message);
            return in.readLine();

        } catch (UnknownHostException e) {
            return "Don't know about host " + hostName;
        } catch (IOException e) {
            return "Couldn't get I/O for the connection to " +
                    hostName;

        }
    }
}
