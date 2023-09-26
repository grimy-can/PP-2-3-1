package ppcrud.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ppcrud.model.User;
import javax.persistence.*;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;


@Repository
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {

    private static final Logger logger = Logger.getLogger(UserDaoImpl.class.getName());

    @PersistenceContext
    private EntityManager entityManager;


    @Transactional
    @Override
    public void addUser(User user) {
        entityManager.persist(user);
        entityManager.flush();

        logger.info("new user: " + user.getAlias());
    }

    @Transactional
    @Override
    public void updateUser(User user, long id) {

        User newDataUser = entityManager.find(User.class, user.getId());
        entityManager.detach(newDataUser);
        newDataUser.setName(user.getName());
        newDataUser.setLastName(user.getLastName());
        newDataUser.setAge(user.getAge());
        newDataUser.setAlias(user.getAlias());
        newDataUser.setOccupation(user.getOccupation());
        newDataUser.setIsAlive(user.getIsAlive());
        entityManager.merge(user);

        logger.info("updated user: " + user.getAlias());
    }

    @Transactional(readOnly = true)
    @Override
    public User getUser(long id) {
        Query query = entityManager.createQuery("from User u WHERE u.id=:id");
        query.setParameter("id", id);
        User user = (User) query.getSingleResult();
        logger.info("user info: " + user.getAlias());
        return user;
    }


    @Transactional
    @Override
    public void deleteUser(long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
        logger.info("deleted: " + user.getAlias());

    }

    @Transactional
    @Override
    @SuppressWarnings("unchecked")
    public List<User> getUsers(String number) {
        List<User> list = entityManager.createQuery("from User ").getResultList();
        int counter = list.size();
        if (number != null) {
            counter = Integer.parseInt(number);
        }
        logger.info("founded: " + list.size());
        return list.stream().limit(counter).collect(Collectors.toList());
    }
}
