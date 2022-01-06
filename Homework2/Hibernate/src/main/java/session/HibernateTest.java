package session;

import org.hibernate.Session;
import sessionFactory.HibernateUtil;

public class HibernateTest {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        //Create
//        EmployeeEntity employee = new EmployeeEntity();
//        employee.setEmail("YueMin@gmail.com");
//        employee.setFirstName("Yue");
//        employee.setLastName("Min");
//
//        session.save(employee);

        //Read
//        EmployeeEntity employee = session.get(EmployeeEntity.class, 1);
//        System.out.println(employee.toString());

        //Update
//        EmployeeEntity employee = session.get(EmployeeEntity.class, 8);
//        employee.setEmail("yuemin@gmail.com");
//        session.update(employee);

        //Delete
//        EmployeeEntity employee = session.get(EmployeeEntity.class,7);
//        session.delete(employee);

        session.getTransaction().commit();
        HibernateUtil.shutdown();
    }
}
