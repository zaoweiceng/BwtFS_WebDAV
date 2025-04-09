package com.bwtfs.server;

import com.bwtfs.common.CommonTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ServerApplicationTests {

    @Autowired
    public CommonTest t;
    @Test
    void contextLoads() {
        System.out.println(t.print());
    }

}
