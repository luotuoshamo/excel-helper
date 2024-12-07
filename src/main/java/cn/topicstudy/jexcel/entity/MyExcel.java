package cn.topicstudy.jexcel.entity;

import cn.topicstudy.jexcel.enums.ExcelTypeEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 专题学习大师 topicstudy.cn 2021/10/10
 */
public class MyExcel {
    private String name;
    private ExcelTypeEnum excelType;
    private List<MySheet> mySheets = new ArrayList();// excel中的sheet

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ExcelTypeEnum getExcelType() {
        return excelType;
    }

    public void setExcelType(ExcelTypeEnum excelType) {
        this.excelType = excelType;
    }

    public List<MySheet> getMySheets() {
        return mySheets;
    }

    public void setMySheets(List<MySheet> mySheets) {
        this.mySheets = mySheets;
    }

    @Override
    public String toString() {
        return "MyExcel{" +
                "name='" + name + '\'' +
                ", mySheets=" + mySheets +
                '}';
    }
}
