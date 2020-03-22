package com.lunarshade.h1_ono_to_one;

import com.lunarshade.h1_ono_to_one.entity.Instructor;
import com.lunarshade.h1_ono_to_one.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteInstructionDetailWithoutInstructor {

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml") //Имя не обязательно, если используется имя по-умолчанию
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            int id = 11;
            InstructorDetail instructorDetail = session.get(InstructorDetail.class, id);

//          Удалить связь Instructor с InstructorDetail
            instructorDetail.getInstructor().setInstructorDetail(null);

            if (instructorDetail!=null) {
                session.delete(instructorDetail);
            }

            session.getTransaction().commit();
            System.out.println("Сохранение завершено");
        } catch (Exception e) {e.printStackTrace();}


    }
}
