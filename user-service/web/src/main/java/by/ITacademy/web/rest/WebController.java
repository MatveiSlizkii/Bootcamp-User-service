package by.ITacademy.web.rest;

import by.ITacademy.core.dto.User;
import by.ITacademy.core.services.api.IUserService;
import by.ITacademy.core.services.exception.ValidationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class WebController {

    private final IUserService userService;

    public WebController(IUserService userService) {
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
