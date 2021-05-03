package com.example.mybatisstudy;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.ExecutorType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserDAOTest {
    UserDAO userDAO;

    @BeforeEach
    void setUp() throws Exception {
        userDAO = new UserDAO(ExecutorType.SIMPLE, true);
        userDAO.deleteAllUsers();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void insertUserListTest() {
        List<User> list = new ArrayList<>();
        for(int i=0; i<100; i++) {
            list.add(new User("a", "bbb@ccc.ddd"));
        }
        userDAO.insertUserList(list);
    }

    @Test
    void executorTypePerfTest() throws Exception {
        long beforeTime;
        double simpleTime, reuseTime, batchTime;
        int iterateLimit = 10000;

        userDAO = new UserDAO(ExecutorType.SIMPLE, true);
        beforeTime = System.currentTimeMillis();
        for (int i=0; i < iterateLimit; i++) {
            userDAO.insertUser(new User("aaa", "a@kkk.com"));
        }
        simpleTime = (double) (System.currentTimeMillis() - beforeTime) / 1000;
        userDAO.deleteAllUsers();

        userDAO = new UserDAO(ExecutorType.REUSE, true);
        beforeTime = System.currentTimeMillis();
        for (int i=0; i < iterateLimit; i++) {
            userDAO.insertUser(new User("aaa", "a@kkk.com"));
        }
        reuseTime = (double) (System.currentTimeMillis() - beforeTime) / 1000;
        userDAO.deleteAllUsers();

        userDAO = new UserDAO(ExecutorType.BATCH, true);
        beforeTime = System.currentTimeMillis();
        for (int i=0; i < iterateLimit; i++) {
            userDAO.insertUser(new User("aaa", "a@kkk.com"));
        }
        batchTime = (double) (System.currentTimeMillis() - beforeTime) / 1000;
        userDAO.deleteAllUsers();

        System.out.println("ExecutorType.SIMPLE: " + simpleTime);
        System.out.println("ExecutorType.REUSE: " + reuseTime);
        System.out.println("ExecutorType.BATCH: " + batchTime);
    }
}