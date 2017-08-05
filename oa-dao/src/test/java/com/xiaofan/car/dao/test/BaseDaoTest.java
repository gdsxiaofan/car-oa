package com.xiaofan.car.dao.test;

import com.xiaofan.car.dao.configuration.MybatisConfiguration;
import com.xiaofan.car.dao.repository.LedgerInfoMapper;
import com.xiaofan.car.persistence.model.LedgerInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.security.auth.login.Configuration;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MybatisConfiguration.class)
@Slf4j
public class BaseDaoTest {

    @Test
    public void test(){
        log.debug("debug");
    }
}
