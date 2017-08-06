package com.xiaofan.car.dao.test;

import com.xiaofan.car.AppStartConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppStartConfiguration.class)
@Slf4j
@Transactional
@Rollback
public class BaseDaoTest {

    @Test
    public void test(){
        log.debug("debug");
    }
}
