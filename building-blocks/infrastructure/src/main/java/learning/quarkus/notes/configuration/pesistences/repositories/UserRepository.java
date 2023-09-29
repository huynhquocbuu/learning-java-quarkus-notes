package learning.quarkus.notes.configuration.pesistences.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import learning.quarkus.shared.entities.User;

import java.util.List;

@ApplicationScoped
public class UserRepository {
    @Inject
    EntityManager entityManager;

    public List<User> getAll() {
        return entityManager
                .createQuery("SELECT u FROM User u", User.class)
                .getResultList();
    }

    public User add(User user){
        entityManager.persist(user);
        entityManager.flush();
        return user;
    }
}
