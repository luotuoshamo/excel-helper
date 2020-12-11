package com.wjh.domain;

import java.util.HashMap;
import java.util.Map;


public class SheetHeader {
    /**
     * 例如 {姓名:name,性别:gender}
     */
    private Map<String, String> chineseKeyHeaderMap = new HashMap<>();
    /**
     * 例如 {name:姓名,gender:性别}
     */
    private Map<String, String> englishKeyHeaderMap = new HashMap<>();

    /**
     * {
     * 0 : {englishName : name, chineseName : 姓名},
     * 1 : {englishName : gender, chineseName : 性别}
     * }
     */
    private Map<Integer, SheetHeaderItem> indexKeyHeaderMap = new HashMap<>();

    public void add(String chineseName, String englishName, int serial) {
        chineseKeyHeaderMap.put(chineseName, englishName);
        englishKeyHeaderMap.put(englishName, chineseName);
        indexKeyHeaderMap.put(serial, new SheetHeaderItem(englishName, chineseName));
    }

    public void add(String chineseName, String englishName) {
        chineseKeyHeaderMap.put(chineseName, englishName);
        englishKeyHeaderMap.put(englishName, chineseName);
        indexKeyHeaderMap.put(indexKeyHeaderMap.entrySet().size(), new SheetHeaderItem(englishName, chineseName));
    }

    /**
     * 取第columnIndex列的中文名称
     */
    public String getChineseName(int columnIndex) {
        return indexKeyHeaderMap.get(columnIndex).getChineseName();
    }

    /**
     * 取第columnIndex列英文的名称
     */
    public String getEnglishName(int columnIndex) {
        return indexKeyHeaderMap.get(columnIndex).getEnglishName();
    }


    /**
     * 表头的一个单元格
     */
    private class SheetHeaderItem {
        String englishName;
        String chineseName;

        SheetHeaderItem(String englishName, String chineseName) {
            this.englishName = englishName;
            this.chineseName = chineseName;
        }

        public String getEnglishName() {
            return englishName;
        }

        public String getChineseName() {
            return chineseName;
        }
    }
}
