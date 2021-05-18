package com.christian.map.hibernate.demo;

import com.christian.map.hibernate.demo.entity.Address;
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
            Student tempStudent= new Student("John","Doe","john@doe.com");
            Address homeAddress= new Address("street123","Osaka","123456");

            //start a transaction
            session.beginTransaction();

            //save the obj
            System.out.println("Saving the student and address");
            tempStudent.setHomeAddress(homeAddress);
            session.persist(tempStudent);

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
