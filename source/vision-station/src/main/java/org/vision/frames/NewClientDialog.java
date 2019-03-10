package org.vision.frames;

import cn.hutool.core.codec.Base64;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class NewClientDialog extends JDialog {

    @Getter
    private VisionClient visionClient = null;
    private JTextField textField;

    public NewClientDialog() {

        this.setLayout(new BorderLayout());
        this.setMinimumSize(new Dimension(600, 100));
        this.setModal(true);
        this.setLocationRelativeTo(null);

        // 配置文本框
        textField = new JTextField();
        textField.setMinimumSize(new Dimension(60, 60));
        textField.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                visionClient = parseText(textField.getText());
                if (null != visionClient) {
                    setVisible(false);
                } else {
                    textField.selectAll();
                    textField.requestFocus();
                }
            }
        });
        // 添加文本框
        this.add(textField, BorderLayout.CENTER);

    }

    @Override
    public void setVisible(boolean b) {
        if (b) {
            visionClient = null;
            textField.setText("");
        }
        super.setVisible(b);
    }

    private VisionClient parseText(String text) {
        if (null == text || "".equals(text)) {
            return null;
        }
        //Base64解码
        String originText = Base64.decodeStr(text);
        String[] clientData = originText.split("&");
        if (5 != clientData.length) {
            return null;
        } else {
            String[] keyValue;
            String activityId = null;
            String clientId = null;
            String className = null;
            String studentNumber = null;
            String name = null;

            for (String s : clientData) {
                keyValue = s.split("=");
                switch (keyValue[0]) {
                    case "activityId":
                        activityId = keyValue[1];
                        break;
                    case "clientId":
                        clientId = keyValue[1];
                        break;
                    case "className":
                        className = keyValue[1];
                        break;
                    case "studentNumber":
                        studentNumber = keyValue[1];
                        break;
                    case "name":
                        name = keyValue[1];
                        break;
                    default:
                        return null;
                }
            }

            return new VisionClient(activityId, clientId, className, studentNumber, name);
        }
    }
}
