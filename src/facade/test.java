/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author bbalt
 */
public class test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        facade f = new facade();
        System.out.println("Total employees = " + f.getEmployeCount());
        f.getCustomersInCity("Nantes");
        System.out.println(f.getEmploueMaxCustomers().toString());
        System.out.println(f.getOrderOnHold().toString());
        System.out.println(f.getOrderOnHold(144));
    }
}
