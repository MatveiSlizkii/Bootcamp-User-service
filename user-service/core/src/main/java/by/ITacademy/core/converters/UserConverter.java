package by.ITacademy.core.converters;

import by.ITacademy.core.dto.User;
import by.ITacademy.dao.entity.UserEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class UserConverter implements Converter<UserEntity, User> {
    @Override
    public User convert(UserEntity source) {

        return User.Builder.createBuilder()
                .setUuid(source.getUuid())
                .setName(source.getName())
                .setSurname(source.getSurname())
                .setPatronymic(source.getPatronymic())
                .setEmail(source.getEmail())
                .setRole(source.getRole())
                .setDtCreate(source.getDtCreate())
                .setDtUpdate(source.getDtUpdate())
                .build();
    }

    @Override
    public <U> Converter<UserEntity, U> andThen(Converter<? super User, ? extends U> after) {
        return Converter.super.andThen(after);
    }


}
