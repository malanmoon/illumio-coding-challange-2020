package com.illumio;

import java.util.Random;

public class RandomPacket {
    Random r = new Random();

    //Randomly generate direction.
    public String getDirection() {
        String direction;
        if (r.nextInt(2) > 0.5) {
            direction = "inbound";
        } else {
            direction = "outbound";
        }
        return direction;
    }

    // Randomly generate protocol.
    public String getProtocol() {
        String protocol;
        if (r.nextInt(2) > 0.5) {
            protocol = "tcp";
        } else {
            protocol = "udp";
        }
        return protocol;
    }

    // Randomly generate port number.
    public int getPort() {
        return r.nextInt(65535) + 1;
    }

    // Randomly generate ip address.
    public String getIp() {
        String ip = r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256);
        return ip;
    }
}
