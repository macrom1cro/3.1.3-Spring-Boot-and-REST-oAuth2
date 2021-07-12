package web.service;

import hiber.model.User;

import java.util.List;

public interface WebServiceUsers {
    List<User> getUserList();
    void addUserInList();
}
