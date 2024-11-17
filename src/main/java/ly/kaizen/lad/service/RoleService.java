package ly.kaizen.lad.service;

import ly.kaizen.lad.model.Role;

import java.util.Optional;
import java.util.List;

public interface RoleService {

    Role createRole(Role role);

    Optional<Role> getRoleById(Long id);

    List<Role> getAllRoles();

    Role updateRole(Long id, Role role);

    void deleteRole(Long id);
}
