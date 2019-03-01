package com.izdebski.client;

import java.util.List;

import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import com.izdebski.entity.Employee;
import com.izdebski.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class CriteriaQueryEntityAttributesSelectionClientTest {

    public static void main(String[] args) {
        getEmployeesInfo();
    }

    private static void getEmployeesInfo() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Tuple> criteriaQuery = builder.createQuery(Tuple.class);
            Root<Employee> root = criteriaQuery.from(Employee.class);

            Path<Object> employeeNamePath = root.get("employeeName");
            Path<Object> emailPath = root.get("email");
            Path<Object> salaryPath = root.get("salary");

            criteriaQuery.multiselect(employeeNamePath,emailPath,salaryPath);

            List<Tuple> tuples = session.createQuery(criteriaQuery).getResultList();
            for (Tuple tuple : tuples) {
                String employeeName = (String) tuple.get(employeeNamePath);
                String email=(String) tuple.get(emailPath);
                Double sal =(Double) tuple.get(salaryPath);
                System.out.println(employeeName+"\t"+email+"\t"+sal);
            }

        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}