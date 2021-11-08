package ru.gb.online.store.services;

import ru.gb.online.store.entities.SystemUser;
import ru.gb.online.store.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findByUserName(String userName);
    boolean save(SystemUser systemUser);
}
