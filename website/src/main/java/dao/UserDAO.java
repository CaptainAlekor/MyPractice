package dao;

import jakarta.persistence.criteria.CriteriaBuilder;
import models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import tools.HibernateSessionFactoryUtil;

public class UserDAO {
    public void create(User user) {
        Transaction transaction = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        }
    }
    public User read(int id) {
        User user = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            user = session.get(User.class, id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return user;
    }
    public User read(String email) {
        User user = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            user = session.createQuery("from User where email = :email", User.class)
                    .setParameter("email", email)
                    .uniqueResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return user;
    }
    public void update(User user) {
        Transaction transaction = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        }
    }
    public void delete(User user) {
        Transaction transaction = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        }
    }
}
