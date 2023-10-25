package com.xpl.k314.dao;

import com.xpl.k314.models.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDAOImpl implements RoleDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role getRoleById(int id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public List<Role> getRoleList() {
        return entityManager
                .createQuery("from Role", Role.class)
                .getResultList();
    }

    @Override
    public void saveRole(Role role) {
        entityManager.persist(role);
    }
}
