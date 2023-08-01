package by.ITacademy.core.services.api;

import by.ITacademy.core.dto.User;
import by.ITacademy.core.services.exception.ValidationError;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserService {
    /**
     * Сохраняет передаваемого пользователя с уникальным Имейл
     * @param user
     * @return схраненного пользователя с uuid, dt_create, dt_update
     */
    User save (User user);
    List<User> getAll ();

    /**
     * Предоставляет сведения о всех пользователях с фильтром
     * по имейлам от a-z в формате Pageable
     * @param pageable
     * @return
     */
    Page<User> getAll(Pageable pageable);

    /**
     * Валидация юзера
     * @param user
     * @param errors
     * @return
     */
    boolean validateUser (User user, List<ValidationError> errors);

    /**
     * Проверка на уникальность имейла
     * @param email
     * @return
     */
    boolean existUserByEmail (String email);
}
