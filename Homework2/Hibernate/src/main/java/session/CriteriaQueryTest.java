package session;

import entity.EmployeeEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.query.Query;
import sessionFactory.HibernateUtil;

import javax.persistence.criteria.*;
import java.util.List;

public class CriteriaQueryTest {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<EmployeeEntity> query = builder.createQuery(EmployeeEntity.class);
        Root<EmployeeEntity> root = query.from(EmployeeEntity.class);
        //selecting all
        // query.select(root);

        //Selecting one by id
        query.select(root).where(builder.equal(root.get("employeeId"), 1));
        Query<EmployeeEntity> q = session.createQuery(query);

        //For ALL
//        List<EmployeeEntity> employees = q.getResultList();
//        for (EmployeeEntity employee : employees) {
//            System.out.println(employee.getEmail());
//        }
        //For one
        EmployeeEntity employee = q.getSingleResult();
        System.out.println(employee.toString());



        //Create
        EmployeeEntity newEmployee = new EmployeeEntity();
        newEmployee.setEmail("chandiao@gmail.com");
        newEmployee.setFirstName("Chan");
        newEmployee.setLastName("Diao");
        session.save(newEmployee);
        CriteriaQuery<EmployeeEntity> create = builder.createQuery(EmployeeEntity.class);
        root = create.from(EmployeeEntity.class);
        create.select(root);
        session.createQuery(create);

       //Update
        CriteriaUpdate<EmployeeEntity> update = builder.createCriteriaUpdate(EmployeeEntity.class);
        root = update.from(EmployeeEntity.class);
        update.set("firstName", "Renjie").set("lastName", "Di").where(builder.equal(root.get("employeeId"), 13));
        session.createQuery(update).executeUpdate();


        //Delete
        CriteriaDelete<EmployeeEntity> delete = builder.createCriteriaDelete(EmployeeEntity.class);
        root = delete.from(EmployeeEntity.class);
        delete.where(builder.equal(root.get("employeeId"), 16));
        session.createQuery(delete).executeUpdate();


        session.getTransaction().commit();
        HibernateUtil.shutdown();
    }
}
