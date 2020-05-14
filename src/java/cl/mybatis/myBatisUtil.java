/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.mybatis;

import java.io.Reader;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 *
 * @author David Lara
 */
public class myBatisUtil {
    private final String resource = "mybatis-config.xml";
    private SqlSession session = null;
    
    public SqlSession getSession() {
        try {
            Reader reader = Resources.getResourceAsReader(resource);
            SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);
            session = sqlMapper.openSession();
        } catch (Exception e) {
            System.out.println("error "+e.getMessage());
            e.printStackTrace();
        }
        return session;
    }
    
}
