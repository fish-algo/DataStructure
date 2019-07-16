package com.github.fish56;

import com.alibaba.fastjson.JSONObject;
import lombok.val;
import org.junit.Test;

public class ApplicationTest {
    @Test
    public void a(){
        val a = new boolean[3][3];
        System.out.println(JSONObject.toJSONString(a));
    }

    @Test
    public void doubleEquals(){
        double a = 0.0;
        int b = 0;
        System.out.println(a == b);
        // true
    }

    @Test
    public void black(){
        // https://www.fileformat.info/info/unicode/char/25a0/index.htm
        for (int i = 0; i < 10; i++) {
            System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■&#x25a0");
        }
    }
}
