package com.lunarshade.h1_ono_to_one;

import com.lunarshade.h1_ono_to_one.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DeleteCourseDemo {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml") //Имя не обязательно, если используется имя по-умолчанию
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
//            Удаляется из таблицы course И из джоин тэйбл!
            int id = 11;
            Course course = session.get(Course.class, id);
            session.delete(course);
            session.getTransaction().commit();
            System.out.println("Сохранение завершено");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }


    }

}
