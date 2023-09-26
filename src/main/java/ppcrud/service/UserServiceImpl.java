package ppcrud.service;


import ppcrud.dao.UserDao;
import ppcrud.model.User;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;


    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public void updateUser(User user, long id) {
        userDao.updateUser(user, id);
    }

    @Override
    public void deleteUser(long id) {
        userDao.deleteUser(id);
    }

    @Override
    public User getUser(long id) {
        return userDao.getUser(id);
    }


    @Override
    public List<User> getUsers(String number) {
        return userDao.getUsers(number);
    }
}
