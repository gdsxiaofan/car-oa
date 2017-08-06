package com.xiaofan.car.dao.test;

import com.xiaofan.car.dao.configuration.MybatisConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MybatisConfiguration.class)
@Slf4j
public class BaseDaoTest {

    @Test
    public void test(){
        log.debug("debug");
    }
}
