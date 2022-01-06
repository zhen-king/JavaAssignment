package session;

import entity.EmployeeEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;
import sessionFactory.HibernateUtil;

import java.util.List;

public class HQLTest {

    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        //Select
        String queryTable = "from EmployeeEntity";
        Query query = session.createQuery(queryTable);

        query.setFirstResult(4);//set the 4 row to be the first result
        query.setMaxResults(3);//Max records should be pull out, in this case 3 will be show


        //using named query
        query = session.getNamedQuery("employee.byId");
        query.setParameter(1, 2);

        List<EmployeeEntity> users = query.list();
        for (EmployeeEntity user : users){
            System.out.println(user.toString());
        }



        //Create
        String queryCreate = "insert into EmployeeEntity(email, firstName, lastName) " +
                "select email, firstName, lastName from NewEmployeeEntity";
        query = session.createQuery(queryCreate);
        int result = query.executeUpdate();
        System.out.println("Rows affected: " + result);

        //Update
        String queryUpdate = "update EmployeeEntity set firstName = :firstName " + "where employeeId = :employeeId";
        query = session.createQuery(queryUpdate);
        query.setParameter("firstName", "qi");
        query.setParameter("employeeId", 14);
        result = query.executeUpdate();
        System.out.println("Rows affected: " + result);

        //Read
        String querySelect = "from EmployeeEntity E where E.employeeId = :employeeId";
        query = session.createQuery(querySelect);
        query.setParameter("employeeId", 8);
        List<EmployeeEntity> results = query.getResultList();

        if (results != null && !results.isEmpty()) {
            EmployeeEntity employee = results.get(0);
            System.out.println(employee.toString());
        } else {
            System.out.println("The id is empty");
        }

        //Delete
        EmployeeEntity employee = session.get(EmployeeEntity.class, 15);
        if (employee != null) {
            String queryDelete = "delete from EmployeeEntity" + " where employeeId = :employeeId";
            query = session.createQuery(queryDelete);
            query.setParameter("employeeId", 15);
            result = query.executeUpdate();
            System.out.println("Rows affected: " + result);
        }



        session.getTransaction().commit();
        HibernateUtil.shutdown();
    }
}
