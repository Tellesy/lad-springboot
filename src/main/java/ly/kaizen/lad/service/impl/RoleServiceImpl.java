package ly.kaizen.lad.service.impl;

import ly.kaizen.lad.model.Role;
import ly.kaizen.lad.repository.RoleRepository;
import ly.kaizen.lad.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Optional<Role> getRoleById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role updateRole(Long id, Role role) {
        return roleRepository.findById(id)
                .map(existingRole -> {
                    existingRole.setName(role.getName());
                    existingRole.setDescription(role.getDescription());
                    return roleRepository.save(existingRole);
                })
                .orElseThrow(() -> new IllegalArgumentException("Role not found with id: " + id));
    }

    @Override
    public void deleteRole(Long id) {
        if (roleRepository.existsById(id)) {
            roleRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Role not found with id: " + id);
        }
    }
}
