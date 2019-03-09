package org.vision;

import org.vision.frames.MainFrame;
import org.vision.frames.VisionClient;
import org.vision.utils.Addressing;

import javax.swing.*;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.List;
import java.util.Vector;

public class VisionStationApplication {

    private static List<VisionClient> visionClientList = new Vector<>();

    public static void main(String[] args) throws SocketException {

        InetAddress inetAddress = Addressing.getIpAddress();

        JFrame frame = new MainFrame("睛锐光学", visionClientList, inetAddress);

        frame.setVisible(true);
    }
}
