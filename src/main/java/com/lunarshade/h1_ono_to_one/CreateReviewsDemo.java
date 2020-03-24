package com.lunarshade.h1_ono_to_one;

import com.lunarshade.h1_ono_to_one.entity.Course;
import com.lunarshade.h1_ono_to_one.entity.Instructor;
import com.lunarshade.h1_ono_to_one.entity.InstructorDetail;
import com.lunarshade.h1_ono_to_one.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateReviewsDemo {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml") //Имя не обязательно, если используется имя по-умолчанию
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            Course course = new Course("Pacman - how to score a million points");
            course.addReview(new Review("Great course!"));
            course.addReview(new Review("Cool course!"));
            course.addReview(new Review("What a dumb course!"));
            session.save(course);

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
