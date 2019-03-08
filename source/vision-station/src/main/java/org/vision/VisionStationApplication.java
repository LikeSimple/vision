package main.java.org.vision;

import main.java.org.vision.frames.MainFrame;
import main.java.org.vision.frames.VisionClient;

import javax.swing.*;
import java.net.SocketException;
import java.util.List;
import java.util.Vector;

public class VisionStationApplication {

    private static List<VisionClient> visionClientList = new Vector<>();

    public static void main(String[] args) throws SocketException {

        JFrame frame = new MainFrame("睛锐光学", visionClientList);
        frame.setVisible(true);
    }
}
