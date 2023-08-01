package config;


import by.ITacademy.core.dto.User;
import by.ITacademy.dao.entity.RoleUser;
import by.ITacademy.dao.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class PullObjectsForTest {
    public PullObjectsForTest() {
    }

    User userCorrect = User.Builder.createBuilder()
            .setName("Matty")
            .setSurname("Slizkiy")
            .setPatronymic("Alexandrovich")
            .setEmail("slizkii.biz@gmail.com")
            .setRole(RoleUser.ADMINISTRATOR)
            .build();
    User userIncorrectNotAzAndLength = User.Builder.createBuilder()
            .setName("МатвейМатвейМатвейМатвей")
            .setSurname("СлизкийСлизкийСлизкийСлизкийСлизкийСлизкий")
            .setPatronymic("АлександровичАлександровичАлександровичАлександрович")
            .setEmail("slizkii.biz@gmail.com")
            .setRole(RoleUser.ADMINISTRATOR)
            .build();
    List<UserEntity> userEntities = new ArrayList<>();
    UserEntity userEntity = UserEntity.Builder.createBuilder()
            .setName("Matty")
            .setSurname("Slizkiy")
            .setPatronymic("Alexandrovich")
            .setEmail("slizkii.biz@gmail.com")
            .setRole(RoleUser.ADMINISTRATOR)
            .build();

    public User getUserCorrect() {
        return userCorrect;
    }

    public User getUserIncorrectNotAzAndLength() {
        return userIncorrectNotAzAndLength;
    }

    public List<UserEntity> getUserEntities() {
        userEntities.add(userEntity);
        userEntities.add(userEntity);
        userEntities.add(userEntity);
        return userEntities;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }
}
