package web.service;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import web.model.User;

import java.util.List;


public interface UserService {
    void add(User user);
    void removeById(Long id);
    void edit(User user);
    List<User> listUsers();
    User getUserById(Long id);
}
