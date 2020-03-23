package com.lunarshade.h1_ono_to_one;

import com.lunarshade.h1_ono_to_one.entity.Course;
import com.lunarshade.h1_ono_to_one.entity.Instructor;
import com.lunarshade.h1_ono_to_one.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class FetchJoinDemo {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml") //Имя не обязательно, если используется имя по-умолчанию
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();

            int id = 15;
            Query<Instructor> query= session.createQuery("SELECT i from Instructor i JOIN FETCH i.courses where i.id=:InstructorId", Instructor.class);
            query.setParameter("InstructorId", id);
            Instructor instructor = query.getSingleResult();
            System.out.println(instructor);
            System.out.println(instructor.getCourses());

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
