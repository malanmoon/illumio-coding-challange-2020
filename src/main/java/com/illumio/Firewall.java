package com.illumio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This abstract class is the superclass of all classes representing a firewall.
 */
 abstract class Firewall {
    // Concatenate the direction, protocol, and each port number with '@" as key of HashMap.
    // Format: {direction@protocol@port, IpInterval}
    final Map<String, List<IpInterval>> map;

    /**
     * Constructor read in and preprocess the firewall policy file line by line.
     * @param policyFilePath
     * @throws IOException
     */
    Firewall(String policyFilePath) throws IOException {
        String line;
        String csvSplitBy = ",";
        map = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(policyFilePath))) {
            while ((line = br.readLine()) != null) {
                // Split policy by comma.
                String[] rule = line.split(csvSplitBy);

                String direction = rule[0];
                String protocol = rule[1];
                String port = rule[2];
                String ip = rule[3];

                // Ip might be a range.
                String ipFrom;
                String ipTo = "";
                if (ip.contains("-")) {
                    String[] ipRange = ip.split("-");
                    ipFrom = ipRange[0];
                    ipTo = ipRange[1];
                } else {
                    ipFrom = ip;
                }

                // Port number might be a range.
                int portFrom;
                int portTo;

                // If the policy contains a port range.
                if (port.contains("-")) {
                    String[] portRange = port.split("-");
                    portFrom = Integer.parseInt(portRange[0]);
                    portTo = Integer.parseInt(portRange[1]);

                    // Update each port.
                    for (int i = portFrom; i <= portTo; i++) {
                        String key = direction + "@" + protocol + "@" + i;
                        if (!map.containsKey(key)) {
                            map.put(key ,new ArrayList<>());
                        }
                        map.get(key).add(new IpInterval(ipFrom, ipTo));
                    }
                } else {
                 // Else policy contains single port number.
                    portFrom = Integer.parseInt(port);
                    String key = direction + "@" + protocol + "@" + portFrom;
                    if (!map.containsKey(port)) {
                        map.put(key, new ArrayList<>());
                    }
                    map.get(key).add(new IpInterval(ipFrom, ipTo));
                }
            }
        }
    }

    /**
     * Determine if the packet will be accepted by the firewall.
     * @param direction
     * @param protocol
     * @param port
     * @param ipAddress
     * @return
     */
    abstract boolean acceptPacket(String direction, String protocol, Integer port, String ipAddress);
}