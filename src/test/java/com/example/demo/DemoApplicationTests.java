package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private Service service;

    @Autowired
    private ParentDao parentDao;

    @Autowired
    private ChildDao childDao;

    @Test
    void testDeleteChild() {
        final UUID id = service.setup().getId();
        assertThat(parentDao.count(), equalTo(1L));
        assertThat(childDao.count(), equalTo(1L));

        service.delete(id);

        assertThat(parentDao.count(), equalTo(1L));
        assertThat(childDao.count(), equalTo(0L));
    }
}
