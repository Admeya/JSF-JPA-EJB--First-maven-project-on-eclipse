package net.java.ejb.entities;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class UserEJB {
    @PersistenceContext(unitName = "net.java.ejb")
    private EntityManager entityManager;

    public List<UserEntity> findUsers() {
        TypedQuery<UserEntity> query = entityManager.createNamedQuery("UserEntity.getAll", UserEntity.class);
        return query.getResultList();
    }

    public UserEntity addNew(UserEntity userbd) {
        entityManager.persist(userbd);
        return userbd;
    }

    public List<UserEntity> checkUserExist(UserEntity userEntity) {
        List<UserEntity> ue = entityManager.createNamedQuery("UserEntity.getRegisterUser", UserEntity.class)
                .setParameter("uLogin", userEntity.getUserLogin()).setParameter("uPass", userEntity.getUserPassword())
                .getResultList();

        return ue;
    }

    public UserEntity findRegisterUser(UserEntity userbd) {

        return userbd;
    }
}
