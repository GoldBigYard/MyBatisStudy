package com.example.mybatisstudy;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class UserDAO {
    private static SqlSessionFactory sqlSessionFactory;
    final static String NAMESPACE = "user.";
    boolean autoCommit;
    ExecutorType execType;

    UserDAO() throws Exception {
        final String resource = "mybatis-config.xml";
        final InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        autoCommit = true;
        execType = ExecutorType.SIMPLE;
    }

    UserDAO(ExecutorType execType, boolean autoCommit) throws Exception {
        final String resource = "mybatis-config.xml";
        final InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        this.autoCommit = autoCommit;
        this.execType = execType;
    }

    public List<User> selectAllUsers(){
        try (SqlSession sqlSession = sqlSessionFactory.openSession(execType, autoCommit)) {
            return sqlSession.selectList(NAMESPACE + "selectAllUsers");
        }
    }

    public int insertUser(User user){
        try (SqlSession sqlSession = sqlSessionFactory.openSession(execType, autoCommit)) {
            return sqlSession.insert(NAMESPACE+"insertUser", user);
        }
    }

    public int insertUserList(List<User> userList){
        try (SqlSession sqlSession = sqlSessionFactory.openSession(execType, autoCommit)) {
            return sqlSession.insert(NAMESPACE+"insertUserList", userList);
        }
    }

    public int updateUserById(String id, String propertyName, String propertyValue){
        try (SqlSession sqlSession = sqlSessionFactory.openSession(execType, autoCommit)) {
            final HashMap<String, Object> params = new HashMap<>();
            params.put("id", id);
            params.put("propertyName", propertyName);
            params.put("propertyValue", propertyValue);
            return sqlSession.update(NAMESPACE+"updateUser", params);
        }
    }

    public int updateUserByName(String name, String propertyName, String propertyValue){
        try (SqlSession sqlSession = sqlSessionFactory.openSession(execType, autoCommit)) {
            final HashMap<String, Object> params = new HashMap<>();
            params.put("name", name);
            params.put("propertyName", propertyName);
            params.put("propertyValue", propertyValue);
            return sqlSession.update(NAMESPACE+"updateUser", params);
        }
    }

    public int deleteUser(String name){
        try (SqlSession sqlSession = sqlSessionFactory.openSession(execType, autoCommit)) {
            return sqlSession.delete(NAMESPACE+"deleteUser", name);
        }
    }

    public int deleteAllUsers(){
        try (SqlSession sqlSession = sqlSessionFactory.openSession(execType, autoCommit)) {
            return sqlSession.delete(NAMESPACE + "deleteAllUsers");
        }
    }
}
