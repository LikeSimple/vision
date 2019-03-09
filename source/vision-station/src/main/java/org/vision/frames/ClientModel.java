package org.vision.frames;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ClientModel extends AbstractTableModel {

    private final String[] tableHeader = {"班级", "学号", "姓名"};
    private List<VisionClient> visionClientList;

    public ClientModel(List<VisionClient> visionClientList) {
        this.visionClientList = visionClientList;
    }

    @Override
    public int getRowCount() {
        return visionClientList.size();
    }

    @Override
    public int getColumnCount() {
        return tableHeader.length;
    }

    @Override
    public String getColumnName(int column) {
        return tableHeader[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        VisionClient visionClient = visionClientList.get(rowIndex);

        switch (columnIndex) {
            case 0: //班级
                return visionClient.getClassName();
            case 1: //学号
                return visionClient.getStudentNumber();
            case 2: //姓名
                return visionClient.getName();
            default:
                return null;
        }
    }
}
