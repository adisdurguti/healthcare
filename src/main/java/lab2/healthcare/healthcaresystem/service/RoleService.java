package lab2.healthcare.healthcaresystem.service;

import lab2.healthcare.healthcaresystem.models.Role;

public interface RoleService {
    Role getRoleName(String name);

    Role getRoleByAuthority(String authority);
}
