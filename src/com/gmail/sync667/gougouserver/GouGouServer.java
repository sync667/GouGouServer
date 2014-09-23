package com.gmail.sync667.gougouserver;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import com.gmail.sync667.gougouserver.config.ServerConfig;
import com.gmail.sync667.gougouserver.log.ConsoleLogger;

public class GouGouServer extends Thread {

    public String VERSION = "ALPHA-0.1 Build 3";
    private DatagramSocket socket;
    public static GouGouServer server;
    public static ConsoleLogger console;
    public static ServerConfig serverConfig;

    public GouGouServer() {
        server = this;
    }

    @Override
    public void run() {

        console = new ConsoleLogger(System.console());
        console.infoC("Starting GouGou Server [" + VERSION + "]...");

        serverConfig = new ServerConfig();

        try {
            this.socket = new DatagramSocket(Integer.valueOf(serverConfig.getEntry("ServerPort").toString()));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        }

        console.infoC("Server started on " + serverConfig.getEntry("ServerIP") + ":"
                + serverConfig.getEntry("ServerPort"));

        while (true) {
            byte[] data = new byte[1024];
            DatagramPacket packet = new DatagramPacket(data, data.length);
            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String message = new String(packet.getData());

            if (message.trim().equalsIgnoreCase("ping")) {
                sendData("pong".getBytes(), packet.getAddress(), packet.getPort());
            }
        }
    }

    public static GouGouServer getServer() {
        return server;
    }

    public static ConsoleLogger getConsole() {
        return console;
    }

    public void sendData(byte[] data, InetAddress ipAddress, int port) {
        DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, port);
        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void start() {
        new Thread(this).start();
    }

    public static void main(String[] args) {
        new GouGouServer().start();
    }
}
