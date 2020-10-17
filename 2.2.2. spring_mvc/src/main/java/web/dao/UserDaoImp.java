package web.dao;

import web.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

//   @Autowired

      @Autowired
   private SessionFactory sessionFactory;


   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   public void removeById(Long id) {
      User user = (User) sessionFactory.getCurrentSession().load(User.class, new Long(id));
      if (user != null) {
         sessionFactory.getCurrentSession().delete(user);
      }
   }

   @Override
   public void edit(User user) {
      sessionFactory.getCurrentSession().update(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getUserById(Long id) {
      return sessionFactory.getCurrentSession().load(User.class, id);
   }

}
