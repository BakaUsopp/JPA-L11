package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.example.dto.CountedEnrollmentForStudent;
import org.example.entities.Student;
import org.example.persistence.CustomPersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        String puName = "pu-name";
        Map<String,String> props = new HashMap<>();
        props.put("hibernate.show_sql", "true");
        props.put("hibernate.hbm2ddl.auto", "none"); // create ,update, none

        EntityManagerFactory emf = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(puName), props);

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

//            String jpql = """
//
//                      SELECT new org.example.dto.CountedEnrollmentForStudent(s.name,COUNT(s))
//                      FROM Student s
//                      GROUP BY s.name
//                      HAVING s.name LIKE 'Y%'
//                      order by s.name DESC
//
//                     """;
//
//
//            TypedQuery<CountedEnrollmentForStudent> oq = em.createQuery(jpql,CountedEnrollmentForStudent.class);
//            oq.getResultList().forEach(o -> System.out.println(o.s()+" "+o.count()));

            TypedQuery<Student> st = em.createNamedQuery("Student.Name",Student.class);
            st.getResultList().forEach(System.out::println);




            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

}