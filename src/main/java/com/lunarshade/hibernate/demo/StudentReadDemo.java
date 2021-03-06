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
            Student tempStudent = new Student("Ivan", "Ivanov", "ivan@rmail.com");
//            Student student = new Student("Jack", "Wild", "blabla@email.com");
            System.out.println("Студент создан");
            session.beginTransaction();
            System.out.println("Сохраняем в базу");
            session.save(tempStudent);
            session.getTransaction().commit();
            System.out.printf("Студент сохранён. ID: %s\n", tempStudent.getId());
//            Получить новую сессию и начать тразакцию
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
//            Получить студента из базы на основании ИД
            System.out.printf("Получить студента с ID: %s\n", tempStudent.getId());
            Student student = session.get(Student.class, tempStudent.getId());
            System.out.printf("Получен студент: %s\n", student);
            session.getTransaction().commit();

        } catch (Exception e) {e.printStackTrace();}


    }
}
