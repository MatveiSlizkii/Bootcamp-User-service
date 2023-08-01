package by.ITacademy.dao.api;

import by.ITacademy.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IUserStorage extends JpaRepository<UserEntity, UUID> {
    /*
    Предоставляет всех пользователей с бд с фильтром по имейлам в порядке от a до z
     */
    List<UserEntity> findAllByOrderByEmailAsc();

    /*
    Проверка есть ли пользователей с данным имейлом
     */
    boolean existsUserEntityByEmail(String email);
}
