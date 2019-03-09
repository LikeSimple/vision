package org.vision.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class Addressing {
    // From HBase Addressing.Java
    public static InetAddress getIpAddress() throws
            SocketException {
        // Before we connect somewhere, we cannot be sure about what we'd be bound to; however,
        // we only connect when the message where client ID is, is long constructed. Thus,
        // just use whichever IP address we can find.
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while (interfaces.hasMoreElements()) {
            NetworkInterface current = interfaces.nextElement();
            if (!current.isUp() || current.isLoopback() || current.isVirtual()) continue;
            Enumeration<InetAddress> addresses = current.getInetAddresses();
            while (addresses.hasMoreElements()) {
                InetAddress addr = addresses.nextElement();
                if (addr.isLoopbackAddress()) continue;
                if (AccessibleAddress.condition(addr))
                    return addr;
            }
        }

        throw new SocketException("Can't get our ip address, interfaces are: " + interfaces);
    }

    private static class AccessibleAddress {
        public static boolean condition(InetAddress addr) {
            if (4 == addr.getAddress().length) {
                return true;
            } else
                return false;
        }
    }

    public static String inetHostFormat(InetAddress inetAddress) {
        StringBuilder sb = new StringBuilder();
        for (byte b : inetAddress.getAddress()) {
            sb.append(".").append(b < 0 ? 256 + b : b);
        }
        return sb.substring(1);
    }
}
