package com.christian.map.hibernate.demo;

import com.christian.map.hibernate.demo.entity.Address;
import com.christian.map.hibernate.demo.entity.Status;
import com.christian.map.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class CreateStudentAddressDemo {
    public static void main (String[]args){
        //create session factory
        SessionFactory factory=new Configuration().configure("hibernate.cfg.xml").
                addAnnotatedClass(Student.class)
                .buildSessionFactory();
        //create session
        Session session= factory.getCurrentSession();


        try{

            //create the object
            Student tempStudent= new Student("John","Doe","john@doe.com", Status.ACTIVE);
            Address homeAddress= new Address("street123","Osaka","123456");
            Address billingAddress= new Address("ave123","toronto","010101");

            Student tempStudent2= new Student("Mary","Sue","mary@sue.com", Status.INACTIVE);
            Address homeAddress2= new Address("street123","Osaka","123456");
            Address billingAddress2= new Address("ave123","toronto","010101");



            //start a transaction
            session.beginTransaction();

            //save the obj
            System.out.println("Saving the student and address");
            tempStudent.setHomeAddress(homeAddress);
            tempStudent.setBillingAddress((billingAddress));

            System.out.println("Saving the student2 and address");
            tempStudent2.setHomeAddress(homeAddress2);
            tempStudent2.setBillingAddress((billingAddress2));

            session.save(tempStudent);
            session.save(tempStudent2);

            //commit the transaction
            session.getTransaction().commit();
            System.out.println("Done");

        }finally{
            //clean up the code
            session.close();
            factory.close();
        }

    }
}
