package session;

import entity.EmployeeEntity;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.loader.custom.sql.SQLCustomQuery;
import org.hibernate.loader.custom.sql.SQLQueryParser;
import org.hibernate.query.Query;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import sessionFactory.HibernateUtil;

import java.util.Iterator;
import java.util.List;

public class NativeQuery {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        //Select
        String qry = "select * from employee";
                //Object
        SQLQuery sqlQuery = session.createNativeQuery(qry);
        List<Object[]> list = sqlQuery.list();
        Iterator iterator = list.iterator();
        while(iterator.hasNext()){
            Object[] object = (Object[]) iterator.next();
            System.out.println("Id: "+ object[0] + " Email: " + object[1]
            +" FirstName: " + object[2] + " LastName: " + object[3]);
        }

        //Object
        sqlQuery = session.createNativeQuery(qry);
        sqlQuery.addScalar("ID", IntegerType.INSTANCE);
        sqlQuery.addScalar("EMAIL", StringType.INSTANCE);
        sqlQuery.addScalar("FIRST_NAME", StringType.INSTANCE);
        sqlQuery.addScalar("LAST_NAME", StringType.INSTANCE);

        list = sqlQuery.list();
        iterator = list.iterator();
        while(iterator.hasNext()){
            Object[] object = (Object[]) iterator.next();

            System.out.println("Id: "+ object[0] + " Email: " + object[1]
                    +" FirstName: " + object[2] + " LastName: " + object[3]);
        }


        //Create
        String queryInsert = "insert into employee (EMAIL, FIRST_NAME, LAST_NAME) values(?,?,?)";
        SQLQuery insert = session.createNativeQuery(queryInsert);
        insert.setParameter(1, "zhonghuang@gmail.com");
        insert.setParameter(2, "Zhong");
        insert.setParameter(3, "Huang");
        insert.executeUpdate();

        //Update
        String queryUpdate = "update employee set FIRST_NAME=:firstName, LAST_Name=:lastName where ID=:id";
        SQLQuery update = session.createNativeQuery(queryUpdate);
        update.setParameter("id", 14);
        update.setParameter("firstName", "Qi");
        update.setParameter("lastName", "Bai");
        update.executeUpdate();


        //Delete
        String queryDelete = "delete from employee where ID=:id";
        SQLQuery delete = session.createNativeQuery(queryDelete);
        delete.setParameter("id", 20);
        delete.executeUpdate();



        session.getTransaction().commit();
        HibernateUtil.shutdown();
    }
}