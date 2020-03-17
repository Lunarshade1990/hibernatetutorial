package com.lunarshade.hibernate.demo;

import com.lunarshade.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("JpaQlInspection")
public class StudentQueryDemo {

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                                                .configure("hibernate.cfg.xml") //Имя не обязательно, если используется имя по-умолчанию
                                                .addAnnotatedClass(Student.class)
                                                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            List<Student> students;
            String query = "from Student"; // Student - класс, а не таблица!
            String ivanQuery = "from Student s where s.firstName = 'Ivan'";
            students = session.createQuery(ivanQuery).list();
            session.getTransaction().commit();
            display(students);
        } catch (Exception e) {e.printStackTrace();}


    }

    private static void display(List<Student> students) {
        students.forEach(System.out::println);
    }
}
