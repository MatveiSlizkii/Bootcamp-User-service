package services;

import by.ITacademy.core.UserService;
import by.ITacademy.core.converters.UserConverter;
import by.ITacademy.core.converters.UserEntityConverter;
import by.ITacademy.core.dto.User;
import by.ITacademy.core.services.exception.ValidationError;
import by.ITacademy.core.services.exception.ValidationException;
import by.ITacademy.dao.api.IUserStorage;
import by.ITacademy.dao.entity.UserEntity;
import config.PullObjectsForTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock(strictness = Mock.Strictness.LENIENT)
    private IUserStorage userStorage;
    @Mock (strictness = Mock.Strictness.LENIENT)
    private ConversionService conversionService;
    @Autowired
    private UserEntityConverter userEntityConverter;
    @Autowired
    private UserConverter userConverter;
    @InjectMocks
    private UserService userService;

    PullObjectsForTest poft = new PullObjectsForTest();

    private final User userCorrect = poft.getUserCorrect();
    private final User userIncorrectAZAndLength = poft.getUserIncorrectNotAzAndLength();
    private final List<UserEntity> userEntities = poft.getUserEntities();
    private final UserEntity userEntity = poft.getUserEntity();

    @BeforeAll
    static void beforeAll() {

    }

    @Test
    void saveCorrect() {

        Mockito.when(userStorage.save(conversionService.convert(userCorrect, UserEntity.class))).thenReturn(userEntity);
        User user = userService.save(userCorrect);

        Assertions.assertNotNull(user.getUuid());
        Assertions.assertNotNull(user.getDtCreate());
        Assertions.assertNotNull(user.getDtUpdate());
    }
    @Test
    void saveIncorrect(){
        Mockito.when(userStorage.existsUserEntityByEmail(userCorrect.getEmail())).thenReturn(true);
        Assertions.assertThrows(ValidationException.class, () -> userService.save(userCorrect));

    }

    @Test
    void getAllExtension() {
        Mockito.when(userStorage.findAllByOrderByEmailAsc()).thenReturn(null);
        Assertions.assertThrows(ValidationException.class, () -> userService.getAll());
    }

    @Test
    void getAllCorrect() {
        Mockito.when(userStorage.findAllByOrderByEmailAsc()).thenReturn(userEntities);
        Assertions.assertAll(() -> userService.getAll());
    }

    @Test
    void validateUserCorrect() {
        //Когда пользователь правильный
        List<ValidationError> errors = new ArrayList<>();
        userService.validateUser(userCorrect, errors);
        Assertions.assertEquals(0, errors.size());
        Assertions.assertTrue(userService.validateUser(userCorrect, errors));
    }

    @Test
    void validateUserInCorrectAzLength() {
        List<ValidationError> errors1 = new ArrayList<>();
        userService.validateUser(userIncorrectAZAndLength, errors1);
        Assertions.assertEquals(6, errors1.size());
        Assertions.assertFalse(userService.validateUser(userIncorrectAZAndLength, errors1));
    }

    @Test
    void validateUserInCorrectNullField() {
        List<ValidationError> errors1 = new ArrayList<>();
        userService.validateUser(Mockito.mock(User.class), errors1);
        Assertions.assertEquals(4, errors1.size());
        Assertions.assertFalse(userService.validateUser(Mockito.mock(User.class), errors1));
    }

    @Test
    void validateUserInCorrectNullUser() {
        List<ValidationError> errors1 = new ArrayList<>();
        Assertions.assertFalse(userService.validateUser(null, errors1));
        Assertions.assertEquals(1, errors1.size());
    }

    @Test
    void validateUserInCorrectEmail() {
        User user = poft.getUserCorrect();
        user.setEmail("withoutAt");
        List<ValidationError> errors1 = new ArrayList<>();
        Assertions.assertFalse(userService.validateUser(user, errors1));
        Assertions.assertEquals(1, errors1.size());

        user.setEmail("@mail.com");
        List<ValidationError> errors3 = new ArrayList<>();
        Assertions.assertFalse(userService.validateUser(user, errors3));
        Assertions.assertEquals(1, errors3.size());

    }
}