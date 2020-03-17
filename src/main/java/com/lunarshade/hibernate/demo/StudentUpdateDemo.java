package com.lunarshade.hibernate.demo;

import com.lunarshade.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class StudentUpdateDemo {

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                                                .configure("hibernate.cfg.xml") //Имя не обязательно, если используется имя по-умолчанию
                                                .addAnnotatedClass(Student.class)
                                                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();
        try {
            int id = 10006;
            session.beginTransaction();
            Student student = session.get(Student.class, id);
//          Меняем объект и делаем коммит, сохраняется в базу автоматом!!!!
            student.setFirstName("Scooby");
            session.getTransaction().commit();

            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.createQuery("update Student set email = 'scooby@email.com'").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {e.printStackTrace();}


    }
}
