package com.lunarshade.h1_ono_to_one;

import com.lunarshade.h1_ono_to_one.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateCourseAndStudentDemo {
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

            int[] coursesId = {11, 14, 15};
            List<Course> courses = new ArrayList<>();

            Arrays.stream(coursesId).forEach(id ->{
                courses.add(session.get(Course.class, id));
            });

            Student student1 = new Student("John", "Doe", "doe@mail.com");
            Student student2 = new Student("Mary", "Public", "public@mail.com");
            student1.setCourses(courses);
            student2.addCourse(courses.get(1));
            session.save(student1);
            session.save(student2);
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
