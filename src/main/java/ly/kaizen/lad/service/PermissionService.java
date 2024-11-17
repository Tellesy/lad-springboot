package ly.kaizen.lad.service;

import ly.kaizen.lad.model.Permission;

import java.util.Optional;
import java.util.List;

public interface PermissionService {

    Permission createPermission(Permission permission);

    Optional<Permission> getPermissionById(Long id);

    List<Permission> getAllPermissions();

    Permission updatePermission(Long id, Permission permission);

    void deletePermission(Long id);
}
