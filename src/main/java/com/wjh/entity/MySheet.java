package com.wjh.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MySheet {
    private String name;
    private List<String> headList;// 按sheet中的顺序从左往右
    private List<List<String>> dataList;
    private List<Map<String, String>> dataMapList;

    public MySheet(List<List<String>> dataList) {
        this(dataList.get(0), dataList.subList(1, dataList.size()));
    }

    public MySheet(List<String> headList, List<List<String>> dataList) {
        this.headList = headList == null ? new ArrayList() : headList;
        this.dataList = dataList == null ? new ArrayList() : dataList;
        dataMapList = createDataMapList();
    }

    public MySheet(String name, List<String> headList, List<List<String>> dataList) {
        this(headList, dataList);
        setName(name);
    }

    private List<Map<String, String>> createDataMapList() {
        List<Map<String, String>> mapList = new ArrayList();
        for (List<String> cellValueList : dataList) {
            Map<String, String> map = new HashMap();
            // 将一行数据转成一个map
            for (int i = 0; i < cellValueList.size(); i++) {
                map.put(headList.get(i), cellValueList.get(i));
            }
            mapList.add(map);
        }
        return mapList;
    }

    public List<String> getHeadList() {
        return headList;
    }

    public List<List<String>> getDataList() {
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
