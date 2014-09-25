package com.gmail.sync667.gougouserver.server.packets;

import java.net.InetAddress;

import com.gmail.sync667.gougouserver.GouGouServer;

public class Packet11Ping extends Packet {

    /**
     * @param parsed
     *            packet data.
     */
    public Packet11Ping(InetAddress senderIp, int port, byte[] data) {
        super(11, senderIp, port);
    }

    public Packet11Ping(InetAddress senderIp, int port) {
        super(11, senderIp, port);

    }

    @Override
    public void writeData(GouGouServer server) {
        return;
    }

    @Override
    public byte[] getData() {
        return ("11").getBytes();
    }

}
