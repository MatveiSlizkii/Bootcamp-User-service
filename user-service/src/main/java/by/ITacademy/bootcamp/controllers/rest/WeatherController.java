package by.ITacademy.bootcamp.controllers.rest;

import by.ITacademy.bootcamp.model.dto.User;
import by.ITacademy.bootcamp.services.api.IUserService;
import by.ITacademy.bootcamp.services.exception.ValidationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class WeatherController {

    private final IUserService userService;

    public WeatherController(IUserService userService) {
        this.userService = userService;
    }

    /**
     * Созраняет пользователя в БД
     *
     * @param user
     * @return сохраненного User с информацией UUID, DtCreate, DtUpdate
     */
    @PostMapping(value = {"/create"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public User index(@RequestBody User user) {
        return userService.save(user);
    }

    /**
     * Предоставляет всех пользователей занесенных в БД с фильтром имейла по алфавиту
     * размер страницы прописан внутри
     * @param page номер страницы
     * @return
     */
    @GetMapping(value = {"", "/"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Page<User> index(@RequestParam int page) {
        if (page < 0) {
            throw new ValidationException("Указан неверный номер страницы");
        }
        int size = 10;
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        return userService.getAll(pageable);
    }

}
