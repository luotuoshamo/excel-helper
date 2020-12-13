package com.wjh;

import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class T {
    @Test
    public void t() {
        Map m = new HashMap();
        Map<String, String> mStr = new HashMap<>();
        Map<Object, Object> mObj = new HashMap<>();

        m = mObj;
        m = mStr;

        // mObj = mStr;
        System.out.println(m.get("1"));
    }

    @Test
    public void testFileExist() {
        File xlsFile = new File("test.xls");
        System.out.println(xlsFile.exists());// true

        File xlsFile2 = new File("test2.xls");
        System.out.println(xlsFile2.exists());// false

        File txtFile = new File("test.txt");
        System.out.println(txtFile.exists());// false
    }
}
