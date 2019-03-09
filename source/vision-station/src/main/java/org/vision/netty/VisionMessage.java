package org.vision.netty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import static org.vision.utils.CSVUtil.fieldToHeader;

@Data
@AllArgsConstructor
@ToString
public class VisionMessage {

    private VisionMessageHeader header;

    private VisionMessageData data;

    private byte[] crc16;

    public static String toCSVHeader() {
        return fieldToHeader(VisionMessageHeader.class) + "," + fieldToHeader(VisionMessageData.class) + ",activityId,clientId\n";
    }

    public String toCSVData(String clientId, String activityId) {
        return header.toCSV() + "," + data.toCSV() + "," + activityId + "," + clientId + "\n";
    }

}
