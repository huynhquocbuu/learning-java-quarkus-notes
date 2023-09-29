package learning.quarkus.notes.configuration.pesistences.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import learning.quarkus.shared.entities.Role;
import learning.quarkus.shared.entities.User;
import learning.quarkus.shared.enums.ERole;

import java.util.List;

@ApplicationScoped
public class RoleRepository {
    @Inject
    EntityManager entityManager;

    public List<Role> getAll(){
        return entityManager
                .createQuery("SELECT r FROM Role r", Role.class)
                .getResultList();
    }

    public Role findById(ERole roleId){
//        return (Role) entityManager
//                .createNativeQuery("SELECT * FROM roles where id = :roleId")
//                .setParameter("roleId", roleId.name())
//                .getSingleResult();

        return entityManager
                .createQuery("select r from Role r where r.id = :roleId", Role.class)
                .setParameter("roleId", roleId)
                .getSingleResult();

    }
}
