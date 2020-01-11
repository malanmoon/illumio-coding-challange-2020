package com.illumio;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * Tests for the firewall, assume the input is always well formed.
 */
public class FirewallTest {
    public FirewallTest() throws IOException {};
    FirewallImpl providedTestFile = new FirewallImpl("src/test/resources/providedTestCases.csv");
    FirewallImpl largeTestFile = new FirewallImpl("src/test/resources/largeTestCases.csv");
    FirewallImpl moreTestCasesFile = new FirewallImpl("src/test/resources/moreTestCases.csv");

    /**
     * Provided test cases in instructions.
     */
    @Test
    public void providedTestCases() {
        Assert.assertTrue(providedTestFile.acceptPacket("inbound", "tcp", 80, "192.168.1.2"));
        Assert.assertTrue(providedTestFile.acceptPacket("inbound", "udp", 53, "192.168.2.1"));
        Assert.assertTrue(providedTestFile.acceptPacket("outbound", "tcp", 10234, "192.168.10.11"));
        Assert.assertFalse(providedTestFile.acceptPacket("inbound", "tcp", 81, "192.168.1.2"));
        Assert.assertFalse(providedTestFile.acceptPacket("inbound", "udp", 24, "52.12.48.92"));
    }

    /**
     * More testing.
     * Common inputs with/out ip/port ranges, with/out overlapping ranges, and the inclusiveness of ranges is tested.
     */
    @Test
    public void moreTestCases() {
        for (int i = 1; i <= 1000; i++) {
            Assert.assertTrue(moreTestCasesFile.acceptPacket("inbound", "tcp", i, "99.168.1.2"));
        }
        for (int i = 1001; i <= 10000; i++) {
            Assert.assertFalse(moreTestCasesFile.acceptPacket("inbound", "tcp", i, "99.168.1.2"));
        }
        for (int i = 1; i <= 100; i++) {
            Assert.assertTrue(moreTestCasesFile.acceptPacket("inbound", "udp", 1, i + "." + i + "." + i + "." + i));
        }
        for (int i = 1; i <= 10; i++) {
            Assert.assertTrue(moreTestCasesFile.acceptPacket("inbound", "tcp", i, "99.168.1.2"));
        }
    }

    /**
     * Query a million random packets from a million random policy.
     * Takes 1s 992ms after constructor returned.
     */
    @Test
    public void benchmark() {
        long start = System.currentTimeMillis();

        RandomPacket randomPacket = new RandomPacket();
        for (int i = 0; i < 1000000; i++) {
            largeTestFile.acceptPacket(randomPacket.getDirection(), randomPacket.getProtocol(), randomPacket.getPort(), randomPacket.getIp());
        }

        System.out.println("Execution Time:" + (System.currentTimeMillis() - start));
    }


}
