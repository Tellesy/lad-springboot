package ly.kaizen.lad.service.impl;

import ly.kaizen.lad.model.Permission;
import ly.kaizen.lad.repository.PermissionRepository;
import ly.kaizen.lad.service.PermissionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;

    public PermissionServiceImpl(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public Permission createPermission(Permission permission) {
        return permissionRepository.save(permission);
    }

    @Override
    public Optional<Permission> getPermissionById(Long id) {
        return permissionRepository.findById(id);
    }

    @Override
    public List<Permission> getAllPermissions() {
        return permissionRepository.findAll();
    }

    @Override
    public Permission updatePermission(Long id, Permission permission) {
        return permissionRepository.findById(id)
                .map(existingPermission -> {
                    existingPermission.setName(permission.getName());
                    existingPermission.setDescription(permission.getDescription());
                    return permissionRepository.save(existingPermission);
                })
                .orElseThrow(() -> new IllegalArgumentException("Permission not found with id: " + id));
    }

    @Override
    public void deletePermission(Long id) {
        if (permissionRepository.existsById(id)) {
            permissionRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Permission not found with id: " + id);
        }
    }
}
