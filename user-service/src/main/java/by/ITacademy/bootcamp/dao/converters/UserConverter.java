package by.ITacademy.bootcamp.dao.converters;

import by.ITacademy.bootcamp.dao.entity.UserEntity;
import by.ITacademy.bootcamp.model.dto.User;
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
