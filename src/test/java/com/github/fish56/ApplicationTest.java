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
}
