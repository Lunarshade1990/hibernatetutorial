package com.lunarshade.hibernate.demo;

import com.lunarshade.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class StudentDeleteDemo {

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                                                .configure("hibernate.cfg.xml") //Имя не обязательно, если используется имя по-умолчанию
                                                .addAnnotatedClass(Student.class)
                                                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            Student delStudent = session.get(Student.class, 10006);
//            session.delete(delStudent);

            session.createQuery("delete from Student where firstName = 'Ivan'").executeUpdate();

            session.getTransaction().commit();
        } catch (Exception e) {e.printStackTrace();}


    }
}
