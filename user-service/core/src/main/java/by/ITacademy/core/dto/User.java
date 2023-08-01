package by.ITacademy.core.dto;

import by.ITacademy.core.serializer.UserFullNameSerializer;
import by.ITacademy.dao.entity.RoleUser;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDateTime;
import java.util.UUID;

@JsonSerialize(using = UserFullNameSerializer.class)
public class User {
    private UUID uuid;
    private String name;
    private String surname;
    private String patronymic;
    private String email;
    private RoleUser role;
    private LocalDateTime dtCreate;
    private LocalDateTime dtUpdate;

    public User() {
    }

    public User(UUID uuid, String name, String surname,
                String patronymic, String email, RoleUser role,
                LocalDateTime dtCreate, LocalDateTime dtUpdate) {
        this.uuid = uuid;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.email = email;
        this.role = role;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RoleUser getRole() {
        return role;
    }

    public void setRole(RoleUser role) {
        this.role = role;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }

    public LocalDateTime getDtUpdate() {
        return dtUpdate;
    }

    public void setDtUpdate(LocalDateTime dtUpdate) {
        this.dtUpdate = dtUpdate;
    }

    @Override
    public String toString() {
        return "User{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", dtCreate=" + dtCreate +
                ", dtUpdate=" + dtUpdate +
                '}';
    }

    public static class Builder {
        private UUID uuid;
        private String name;
        private String surname;
        private String patronymic;
        private String email;
        private RoleUser role;
        private LocalDateTime dtCreate;
        private LocalDateTime dtUpdate;

        private Builder() {

        }

        public Builder setUuid(UUID uuid) {
            this.uuid = uuid;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder setPatronymic(String patronymic) {
            this.patronymic = patronymic;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setRole(RoleUser role) {
            this.role = role;
            return this;
        }

        public Builder setDtCreate(LocalDateTime dtCreate) {
            this.dtCreate = dtCreate;
            return this;
        }

        public Builder setDtUpdate(LocalDateTime dtUpdate) {
            this.dtUpdate = dtUpdate;
            return this;
        }

        public static Builder createBuilder() {
            return new Builder();
        }

        public User build() {
            return new User(uuid, name, surname,
                    patronymic, email, role,
                    dtCreate, dtUpdate);
        }
    }
    }
