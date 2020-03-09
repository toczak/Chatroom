package main.DAO;


import main.HibernateUtil;
import main.model.PostEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PostDao {

    public void savePost(PostEntity post) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(post);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void updatePost(PostEntity post) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(post);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deletePost(int id) {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            PostEntity post = session.get(PostEntity.class, id);
            if (post != null) {
                session.delete(post);
                System.out.println("post is deleted");
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public PostEntity getPost(int id) {

        Transaction transaction = null;
        PostEntity post = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            post = session.get(PostEntity.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return post;
    }

    @SuppressWarnings("unchecked")
    public List<PostEntity> getAllPost(boolean isSortFromLatest) {

        Transaction transaction = null;
        List<PostEntity> listOfPost = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            if (isSortFromLatest)
                listOfPost = session.createQuery("from PostEntity order by date desc").getResultList();
            else
                listOfPost = session.createQuery("from PostEntity").getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return listOfPost;
    }

    public List<PostEntity> getAllPostByUser(String username) {

        Transaction transaction = null;
        List<PostEntity> listOfPost = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            listOfPost = session.createQuery("from PostEntity where userByIdUser.username = :username").setParameter("username",username).getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return listOfPost;
    }
}

