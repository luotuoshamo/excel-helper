package com.wjh.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MySheet {
    private String name;
    private List<String> headList = new ArrayList();// 按sheet中的顺序从左往右
    private List<List<String>> dataList = new ArrayList();// sheet的所有行
    private List<Map<String, String>> dataMapList = new ArrayList();//sheet中的数据

    /**
     * @param rowList 就是一个sheet
     */
    public MySheet(String name, List<List<String>> rowList) {
        this.name = name;
        if (rowList == null || rowList.isEmpty()) return;
        if (rowList.size() == 1) this.headList = rowList.get(0);
        else {
            this.headList = rowList.get(0);
            this.dataList = rowList.subList(1, rowList.size());
            this.dataMapList = createDataMapList();
        }
    }

    public MySheet(String name, List<String> headList, List<List<String>> dataList) {
        this.name = name;
        this.headList = headList;
        this.dataList = dataList;
        this.dataMapList = createDataMapList();
    }

    private List<Map<String, String>> createDataMapList() {
        List<Map<String, String>> mapList = new ArrayList();
        if (headList == null) return mapList;
        if (dataList == null) return mapList;
        // 一个map就是一行数据
        for (List<String> rowCellList : dataList) {
            Map<String, String> map = new HashMap();
            if (rowCellList == null) {
                mapList.add(map);
                continue;
            }
            for (int i = 0; i < rowCellList.size(); i++) map.put(headList.get(i), rowCellList.get(i));
            mapList.add(map);
        }
        return mapList;
    }

    public List<String> getHeadList() {
        return headList;
    }

    public List<List<String>> getDateList() {
        return dataList;
    }

    public List<Map<String, String>> getDataMapList() {
        return dataMapList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "MySheet{" +
                "headList=" + headList +
                ", dataList=" + dataList +
                ", dataMapList=" + dataMapList +
                '}';
    }
}
