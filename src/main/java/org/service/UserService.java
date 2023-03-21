package org.service;

import org.persistence.dao.UserDao;
import org.persistence.entities.User;

public class UserService {
    private UserDao userDao = new UserDao();

    public User getUserById(Long id) {
        return userDao.findById(id);
    }

    public void saveUser(User user) {
        userDao.save(user);
    }
}
