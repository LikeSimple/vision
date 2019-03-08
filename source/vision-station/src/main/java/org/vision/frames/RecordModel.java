package main.java.org.vision.frames;

import lombok.Setter;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class RecordModel extends AbstractTableModel {

    private final static String[] tableHeader = {"眼睛", "日期", "时间", "瞳孔大小", "等效球面度1", "球面度1", "柱面度1",
            "柱面轴位角1", "等效球面度2", "球面度2", "柱面度2", "柱面轴位角2", "瞳距", "眼压", "水平固视", "垂直固视"};

    @Setter
    private List<VisionClientRecord> visionClientRecordList;

    public RecordModel(List<VisionClientRecord> visionClientRecordList) {
        this.visionClientRecordList = visionClientRecordList;
    }

    @Override
    public String getColumnName(int column) {
        return tableHeader[column];
    }

    @Override
    public int getRowCount() {
        return null == visionClientRecordList ? 0 : visionClientRecordList.size();
    }

    @Override
    public int getColumnCount() {
        return tableHeader.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (null == visionClientRecordList)
            return null;
        else {
            VisionClientRecord record = visionClientRecordList.get(rowIndex);

            switch (columnIndex) {
                case 0: //eyeType
                    return "1".equals(record.getEyeType())  ? "左" : "右";
                case 1: //日期
                    return record.getDate();
                case 2: //时间
                    return record.getTime();
                case 3: //瞳孔大小
                    return record.getPupil();
                case 4: //等效球面度
                    return record.getSe1();
                case 5: //球面度
                    return record.getDs1();
                case 6: //柱面度
                    return record.getDc1();
                case 7: //柱面轴位角
                    return record.getAxis1();
                case 8: //等效球面度
                    return record.getSe2();
                case 9: //球面度
                    return record.getDs2();
                case 10: //柱面度
                    return record.getDc2();
                case 11: //柱面轴位角
                    return record.getAxis2();
                case 12: //瞳距
                    return record.getPd();
                case 13: //眼压
                    return record.getMmHg();
                case 14: //水平固视
                    return record.getGazeH();
                case 15: //垂直固视
                    return record.getGazeV();
                default:
                    return null;
            }
        }
    }
}
