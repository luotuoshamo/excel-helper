package com.wjh;

import org.junit.Test;

import javax.jws.Oneway;
import java.util.HashMap;
import java.util.Map;

public class TmpTest {
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
    public void f(){
        Map<String , String> map = new HashMap<>();
        Object o = null;
        map.put("s",String.valueOf(o));
        System.out.println(map);
    }
}
