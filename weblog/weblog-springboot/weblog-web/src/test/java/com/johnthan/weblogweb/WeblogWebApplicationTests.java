package com.johnthan.weblogweb;

import com.johnthan.module.common.domain.dos.UserDO;
import com.johnthan.module.common.domain.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Date;


@Slf4j
@SpringBootTest
class WeblogWebApplicationTests {

    @Autowired
    private  UserMapper userMapper ;

    @Test
    void insertDataSourceByUser(){
        UserDO userDO = UserDO.builder()
                .username("Jonyd")
                .password("33620")
                .createTime(new Date())
                .updateTime(new Date())
                .isDeleted(false)
                .build();
            userMapper.insert(userDO);
            log.info("成功插入数据");
    }

    @Test
    void DeleteDataSourceByUser(){
       UserDO user = userMapper.selectById(1);
        userMapper.deleteById(user.getId());
        log.info("成功删除数据：{}" ,user );
    }

    @Test
    void contextLoads() {
        // 获取当前日期
        Date today = new Date();
        log.info("today :{}", String.format(today.toString(), "yyyy-MM-dd "));
        // localDate
        LocalDate localDate = LocalDate.now();
        log.info("localDate :{}", localDate.plusDays(4));
    }


    @Test
    void testLog() {
        log.info("这是一行 Info 级别日志");
        log.warn("这是一行 Warn 级别日志");
        log.error("这是一行 Error 级别日志");

        // 占位符
        String author = "JonyDDD";
        log.info("这是一行带有占位符日志，作者：{}", author);
    }
}
