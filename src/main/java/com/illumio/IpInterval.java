package com.illumio;

/**
 * Represents ip range.
 */
class IpInterval {
    private String startOfIpRange;
    private String endOfIpRange;

    public IpInterval(String startOfIpRange, String endOfIpRange) {
        this.startOfIpRange = startOfIpRange;
        this.endOfIpRange = endOfIpRange;
    }

    /**
     * Validate given ip address.
     * @param ip
     * @return
     */
    public boolean isIpInRange(String ip) {
        // IpInterval object represents a single ip if endOfIpRange is empty.
        if (endOfIpRange.equals("")) {
            return ip.equals(startOfIpRange);
        } else {
            // IpInterval is an interval.
            return ip2Long(startOfIpRange)<=ip2Long(ip) &&ip2Long(ip)<=ip2Long(endOfIpRange);
        }
    }

    /**
     * Change ip address it to Long type for comparison.
     * @param ip
     * @return
     */
    private long ip2Long(String ip) {
        String[] ips = ip.split("\\.");
        long ip2long = 0L;
        // Ipv4 is 32 bit, move left 8 bit for 4 times, meanwhile use OR('|') to copy bits.
        for (int i = 0; i < 4; ++i) {
            ip2long = ip2long << 8;
            ip2long |= Integer.parseInt(ips[i]);
        }
        return ip2long;
    }

}


