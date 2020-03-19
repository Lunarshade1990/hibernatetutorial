package com.lunarshade.h1_ono_to_one;

import com.lunarshade.h1_ono_to_one.entity.Instructor;
import com.lunarshade.h1_ono_to_one.entity.InstructorDetail;
import com.lunarshade.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class CreateDemo {

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                                                .configure("hibernate.cfg.xml") //Имя не обязательно, если используется имя по-умолчанию
                                                .addAnnotatedClass(Instructor.class)
                                                .addAnnotatedClass(InstructorDetail.class)
                                                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();

            Instructor instructor = new Instructor("Chad", "Darby", "darby@luv2code.com");
            InstructorDetail instructorDetail = new InstructorDetail("http//www.luv2code/youtube", "Luv 2 code");
            instructor.setInstructorDetail(instructorDetail);

//            Сохранятся оба объекта в БД cascade = CascadeType.ALL
            session.save(instructor);
            session.getTransaction().commit();
            System.out.println("Сохранение завершено");
        } catch (Exception e) {e.printStackTrace();}


    }
}
