package ly.kaizen.lad.service;

import ly.kaizen.lad.model.User;

import java.util.Optional;
import java.util.List;

public interface UserService {

    User createUser(User user);

    Optional<User> getUserById(Long id);

    Optional<User> getUserByUsername(String username);

    List<User> getAllUsers();

    User updateUser(Long id, User user);

    void deleteUser(Long id);
}
