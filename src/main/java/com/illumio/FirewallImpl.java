package com.illumio;

import java.io.IOException;
import java.util.List;

/**
 * Implementation of the {@code Firewall} abstract class.
 */
public class FirewallImpl extends Firewall {
    FirewallImpl(String policyFilePath) throws IOException {
        super(policyFilePath);
    }

    /**
     * Determine if the packet with the given info will be accepted.
     * @param direction
     * @param protocol
     * @param port
     * @param ipAddress
     * @return
     */
    @Override
    boolean acceptPacket(String direction, String protocol, Integer port, String ipAddress) {
        String key = direction + "@" + protocol + "@" + port;
        // If exist policy match.
        if (map.containsKey(key)) {
            List<IpInterval> ipIntervalsList = map.get(key);
            // Go through all the ip of matching policy.
            for (IpInterval ipInterval : ipIntervalsList) {
                // If existing policy that also accept the input ip, return true.
                if (ipInterval.isIpInRange(ipAddress)) {
                    return true;
                }
            }
        }
        return false;
    }
}
