/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.ClassicOrder;
import entity.Customer;
import entity.Employee;
import entity.Office;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author bbalt
 */
public class facade {
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("classicmodelsPU");
        EntityManager em = emf.createEntityManager();

    public Employee createEmploye(int empNr, String lastName, String fName, String ext, String email, String jTitle, Office off) {
        Employee e1 = new Employee();  
        try {
             e1.setEmployeeNumber(empNr);
             e1.setLastName(lastName);
             e1.setFirstName(fName);
             e1.setExtension(ext);
             e1.setEmail(email);
             e1.setJobTitle(jTitle);
             e1.setOffice(off);
             em.getTransaction().begin();
             em.persist(e1);
             em.getTransaction().commit();
         }
         finally {
             em.close();
         }
          return e1;
    }

    public long getEmployeCount() {
        Query q = em.createQuery("SELECT count(p) FROM Employee p");
        long result = (long) q.getSingleResult();
        return result;
    }

    public List<Customer> getCustomersInCity(String city) {
        System.out.println(city);
        Query q = em.createQuery("SELECT cusp FROM Customer cusp WHERE cusp.city = :city");
        q.setParameter("city", city);
        System.out.println(q);
        List<Customer> cList = q.getResultList();
        System.out.println(cList.toString());
        return cList;
    }
    
    public Employee getEmploueMaxCustomers() {
        Query q = em.createQuery("SELECT cus.employee FROM Customer cus GROUP BY cus.employee ORDER BY COUNT(cus) DESC");
        List<Employee> max = q.getResultList();
        Query q2 = em.createQuery("SELECT emp FROM Employee emp WHERE emp.employeeNumber = :employeeNumber");
        q2.setParameter("employeeNumber", max.get(0).getEmployeeNumber());
        List<Employee> res = q2.getResultList();
        return res.get(0);
    }

    public List<ClassicOrder> getOrderOnHold() {
        Query q = em.createQuery("SELECT ord FROM ClassicOrder ord WHERE ord.status = :status");
        q.setParameter("status", "On Hold");
        List<ClassicOrder> res = q.getResultList();
        return res;
    }

    public List<ClassicOrder> getOrderOnHold(int customerNumber) {
        Query q = em.createQuery("SELECT ord FROM ClassicOrder ord where ord.status = :status AND ord.customer.customerNumber = :customerNumber");
        q.setParameter("status", "On Hold");
        q.setParameter("customerNumber", customerNumber);
        List<ClassicOrder> res = q.getResultList();
        return res;
    }

}
