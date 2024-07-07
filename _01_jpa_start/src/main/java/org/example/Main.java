package org.example;

import javax.persistence.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            logic(em);
            tx.commit();

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

    public static void logic(EntityManager em) {
        String id = "kyungsu";
        Member member = new Member();
        member.setId(id);
        member.setUsername("경수");
        member.setAge(28);

        // INSERT
        em.persist(member);

        // UPDATE
        member.setAge(20);

        // SELECT ONE
        Member findMember = em.find(Member.class, id);
        System.out.println("findMember=" + findMember.getUsername() + ", age=" + findMember.getAge());

        // SELECT LIST
        List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
        System.out.println("members.size=" + members.size());

        // DELETE
        em.remove(member);
    }
}
