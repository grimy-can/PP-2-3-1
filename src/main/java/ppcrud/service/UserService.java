package ppcrud.service;

import ppcrud.model.User;
import java.util.List;


public interface UserService {

    void addUser(User user);

    void updateUser(User user, long id);

    User getUser(long id);

    void deleteUser(long id);

    List<User> getUsers(String number);
}
