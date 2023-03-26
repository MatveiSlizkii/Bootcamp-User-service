package by.ITacademy.bootcamp.dao.api;

import by.ITacademy.bootcamp.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IUserStorage extends JpaRepository<UserEntity, UUID> {
    List<UserEntity> findAllByOrderByEmailAsc();

    boolean existsUserEntityByEmail(String email);
}
