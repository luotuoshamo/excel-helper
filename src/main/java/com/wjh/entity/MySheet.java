package com.wjh.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MySheet {
    private List<String> headList;// 从做往右
    private List<List<String>> dataList;
    private List<Map<String, String>> dataMapList;

    public MySheet(List<List<String>> rowList) {
        if (rowList == null || rowList.size() == 0) return;
        headList = rowList.get(0);
        dataList = rowList.subList(1, rowList.size());
        if (headList == null) headList = new ArrayList();
        if (dataList == null) dataList = new ArrayList();

        dataMapList = createDataMapList();
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

    @Override
    public String toString() {
        return "MySheet{" +
                "headList=" + headList +
                ", dataList=" + dataList +
                ", dataMapList=" + dataMapList +
                '}';
    }
}
