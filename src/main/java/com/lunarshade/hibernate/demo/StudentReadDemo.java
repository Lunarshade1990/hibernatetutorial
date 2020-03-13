package com.lunarshade.hibernate.demo;

import com.lunarshade.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class StudentReadDemo {

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                                                .configure("hibernate.cfg.xml") //Имя не обязательно, если используется имя по-умолчанию
                                                .addAnnotatedClass(Student.class)
                                                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();
        try {
            List<Student> students = new ArrayList<>();
            students.add(new Student("Paul", "Jackson", "1@rmail.com"));
            students.add(new Student("Martin", "Phil", "2@rmail.com"));
            students.add(new Student("Jane", "Austin", "3@rmail.com"));
//            Student student = new Student("Jack", "Wild", "blabla@email.com");
            System.out.println("Студент создан");
            session.beginTransaction();
            System.out.println("Сохраняем в базу");
            students.forEach(session::save);
//            session.save(student);
            session.getTransaction().commit();
            System.out.println("Сохранение завершено");
        } catch (Exception e) {e.printStackTrace();}


    }
}
