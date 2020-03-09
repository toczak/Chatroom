package main.DAO;


import java.util.List;

import main.HibernateUtil;
import main.model.UserEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserDao {


    public void saveUser(UserEntity user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void updateUser(UserEntity user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteUser(int id) {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            UserEntity user = session.get(UserEntity.class, id);
            if (user != null) {
                session.delete(user);
                System.out.println("user is deleted");
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public UserEntity getUser(int id) {

        Transaction transaction = null;
        UserEntity user = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            user = session.get(UserEntity.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return user;
    }

    @SuppressWarnings("unchecked")
    public List<UserEntity> getAllUser() {

        Transaction transaction = null;
        List<UserEntity> listOfUser = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            listOfUser = session.createQuery("from UserEntity").getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return listOfUser;
    }

    public UserEntity validateLoginAndReturnUser(String username, String email, String password) {
        Transaction transaction = null;
        UserEntity user = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            user = (UserEntity) session.createQuery("from UserEntity where username = :username OR email = :email")
                    .setParameter("username", username)
                    .setParameter("email", email)
                    .uniqueResult();
            if (user != null && user.getPassword().equals(password)) {
                return user;
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }

    public boolean checkUserBeforeRegister(String username, String email) {
        Transaction transaction = null;
        UserEntity user = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            user = (UserEntity) session.createQuery("from UserEntity where username = :username OR email=:email")
                    .setParameter("username", username)
                    .setParameter("email", email)
                    .uniqueResult();
            if (user == null) {
                return true;
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return false;
    }

    public int getIdByLogin(String username) {
        Transaction transaction = null;
        UserEntity user = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            user = (UserEntity) session.createQuery("from UserEntity where username = :username")
                    .setParameter("username", username)
                    .uniqueResult();
            if (user != null) {
                return user.getId();
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return 0;
    }


}

