package web.dao;

import org.hibernate.Session;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
@Transactional
public class UserDaoImp implements UserDao {

   @PersistenceContext
      private EntityManager em;

   @Override
   @Transactional
   public void add(User user) {
      em.persist(user);
   }

   @Override
   @Transactional
   public void removeById(Long id) {
      User user = em.find(User.class, id);
      em.remove(user);
   }

   @Override
   @Transactional
   public void edit(User user) {
      em.merge(user);
   }

   @Override
   @Transactional
   public List<User> listUsers() {
//      EntityManager et = em.createEntityManager();
//      et.getTransaction().begin();
      return em.createQuery("select r from User r").getResultList();
   }

   @Transactional
   @Override
   public User getUserById(Long id) {
      return em.find(User.class, id);
   }


}
