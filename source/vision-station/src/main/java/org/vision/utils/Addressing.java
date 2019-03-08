package main.java.org.vision.utils;

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
                return addr;
            }
        }

        throw new SocketException("Can't get our ip address, interfaces are: " + interfaces);
    }
}
