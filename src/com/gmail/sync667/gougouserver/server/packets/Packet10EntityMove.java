package com.gmail.sync667.gougouserver.server.packets;

import java.net.InetAddress;

import com.gmail.sync667.gougouserver.GouGouServer;

public class Packet10EntityMove extends Packet {

    private int entityId;
    private int x;
    private int y;

    /**
     * @param parsed
     *            packet data.
     */
    public Packet10EntityMove(InetAddress senderIp, int port, byte[] data) {
        super(10, senderIp, port);

        String[] dataArray = readData(data).split("/");

        try {
            this.entityId = Integer.valueOf(dataArray[0]);
            this.x = Integer.valueOf(dataArray[1]);
            this.y = Integer.valueOf(dataArray[2]);
        } catch (NumberFormatException e) {
            this.entityId = 0;
            this.x = 0;
            this.y = 0;
        }
    }

    /**
     * @param entityId
     * @param x
     * @param y
     */
    public Packet10EntityMove(InetAddress senderIp, int port, int entityId, int x, int y) {
        super(10, senderIp, port);

        this.entityId = entityId;
        this.x = x;
        this.y = y;

    }

    @Override
    public void writeData(GouGouServer server) {
        return;
    }

    @Override
    public byte[] getData() {
        return ("10" + this.entityId + "/" + x + "/" + y).getBytes();
    }

    public int getEntityId() {
        return entityId;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
