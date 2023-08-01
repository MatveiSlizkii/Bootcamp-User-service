package by.ITacademy.bootcamp.services;

import by.ITacademy.bootcamp.dao.api.IUserStorage;
import by.ITacademy.bootcamp.dao.entity.UserEntity;
import by.ITacademy.bootcamp.model.dto.User;
import by.ITacademy.bootcamp.services.api.IUserService;
import by.ITacademy.bootcamp.services.exception.ValidationError;
import by.ITacademy.bootcamp.services.exception.ValidationException;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {
    private final IUserStorage userStorage;
    private final ConversionService conversionService;

    public UserService(IUserStorage userStorage, ConversionService conversionService) {
        this.userStorage = userStorage;
        this.conversionService = conversionService;
    }

    @Override
    public User save(User user) {
        List<ValidationError> errors = new ArrayList<>();
        if (existUserByEmail(user.getEmail())) {
            errors.add(new ValidationError("Email",
                    "Пользователь с данным Email уже зарегистрирован"));
        }
        validateUser(user, errors);
        if (!errors.isEmpty()) {
            throw new ValidationException("Переданы некорректные данные", errors);
        }
        LocalDateTime ldt = LocalDateTime.now();
        user.setUuid(UUID.randomUUID());
        user.setDtCreate(ldt);
        user.setDtUpdate(ldt);
        user.setEmail(user.getEmail().toLowerCase());
        UserEntity userEntity = conversionService.convert(user, UserEntity.class);
        userStorage.save(userEntity);
        return user;
    }

    @Override
    public List<User> getAll() {
        List<UserEntity> userEntities = userStorage.findAllByOrderByEmailAsc();
        if (userEntities == null) {
            throw new ValidationException("Записей в БД не найдено");
        }
        List<User> users = userEntities.stream()
                .map(o -> conversionService.convert(o, User.class)).collect(Collectors.toList());
        return users;
    }

    @Override
    public Page<User> getAll(Pageable pageable) {
        List<User> users = this.getAll();
        System.out.println(pageable.getPageNumber());
        if (pageable.getPageNumber() * pageable.getPageSize() > users.size()) {
            throw new ValidationException("Указан недопустимый номер страницы");
        }
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), users.size());
        return new PageImpl<>(users.subList(start, end), pageable, users.size());
    }

    @Override
    public boolean existUserByEmail(String email) {
        return userStorage.existsUserEntityByEmail(email);
    }

    @Override
    public boolean validateUser(User user, List<ValidationError> errors) {
        boolean result = true;
        String errorLength = "Превышено максимальное количество символов";
        String errorAz = "Использованы недопустимые символы";
        String errorNull = "Вы не ввели обязательное поле";
        if (user == null) {
            errors.add(new ValidationError("User", "Вы передали пустого пользователя"));
            result = false;
            return result;
        }

        if (user.getName() == null) {
            errors.add(new ValidationError("Name", errorNull));
            result = false;
        } else {
            if (user.getName().length() > 20) {
                errors.add(new ValidationError("Name", errorLength));
                result = false;
            }
            if (!(user.getName().matches("[a-zA-Z]+"))) {
                errors.add(new ValidationError("Name", errorAz));
                result = false;
            }
        }

        if (user.getSurname() == null) {
            errors.add(new ValidationError("Surname", errorNull));
            result = false;
        } else {
            if (user.getSurname().length() > 40) {
                errors.add(new ValidationError("Surname", errorLength));
                result = false;
            }
            if (!(user.getSurname().matches("[a-zA-Z]+"))) {
                errors.add(new ValidationError("Surname", errorAz));
                result = false;
            }
        }

        if (user.getPatronymic() == null) {
            errors.add(new ValidationError("Patronymic", errorNull));
            result = false;
        } else {
            if (user.getPatronymic().length() > 40) {
                errors.add(new ValidationError("Patronymic", errorLength));
                result = false;
            }
            if (!(user.getPatronymic().matches("[a-zA-Z]+"))) {
                errors.add(new ValidationError("Patronymic", errorAz));
                result = false;
            }
        }
        if (user.getEmail() == null) {
            errors.add(new ValidationError("Email", errorNull));
            result = false;
        } else {
            if (!user.getEmail().matches("^[a-zA-Z0-9+_.-]+@+[a-zA-Z0-9.-]+.+$")) {
                errors.add(new ValidationError("Email", "Указан неверный формат Email"));
                result = false;
            }
        }
        return result;
    }


}
