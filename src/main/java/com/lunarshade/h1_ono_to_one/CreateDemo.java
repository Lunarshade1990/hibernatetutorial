package com.lunarshade.h1_ono_to_one;

import com.lunarshade.h1_ono_to_one.entity.Instructor;
import com.lunarshade.h1_ono_to_one.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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
            instructorDetail.setInstructor(instructor);
//            instructorDetail.setInstructor(instructor);

//            Сохранятся оба объекта в БД cascade = CascadeType.ALL
            session.save(instructorDetail);
            session.getTransaction().commit();
            System.out.println("Сохранение завершено");
        } catch (Exception e) {e.printStackTrace();}


    }
}
