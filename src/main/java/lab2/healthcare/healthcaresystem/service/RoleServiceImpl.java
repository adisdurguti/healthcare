package lab2.healthcare.healthcaresystem.service;

import lab2.healthcare.healthcaresystem.models.Role;
import lab2.healthcare.healthcaresystem.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Override
    public Role getRoleName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public Role getRoleByAuthority(String authority) {
        return null;
    }
}
