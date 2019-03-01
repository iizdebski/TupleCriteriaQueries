package com.izdebski.client;

import java.util.Date;

import com.izdebski.entity.Employee;
import com.izdebski.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class SaveDataClientTest {

    public static void main(String[] args) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Employee employee1 = new Employee();
            employee1.setEmployeeName("Martin Petersson");
            employee1.setEmail("martin.pt2031@gmail.com");
            employee1.setSalary(50000.00);
            employee1.setDoj(new Date());

            Employee employee2 = new Employee();
            employee2.setEmployeeName("Sean Connery");
            employee2.setEmail("sean.m2031@gmail.com");
            employee2.setSalary(90000.00);
            employee2.setDoj(new Date());

            session.save(employee1);
            session.save(employee2);

            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}