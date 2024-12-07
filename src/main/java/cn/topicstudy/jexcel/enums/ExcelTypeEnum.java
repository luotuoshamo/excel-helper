package cn.topicstudy.jexcel.enums;

import org.apache.poi.util.StringUtil;

public enum ExcelTypeEnum {
    XLS("XLS", "XLS"),
    XLSX("XLSX", "XLSX");


    String code;
    String desc;

    ExcelTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static ExcelTypeEnum parseByFileName(String fileName) {
        if (fileName == null) {
            return null;
        }

        if (!fileName.contains(".")) {
            return null;
        }

        String suffix = fileName.substring(fileName.indexOf(".") + 1).toUpperCase();

        for (ExcelTypeEnum value : values()) {
            if (suffix.equals(value.getCode())) {
                return value;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
