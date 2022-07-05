package halicmobilya.urun_takip_uygulamasi.api.controllers;

import halicmobilya.urun_takip_uygulamasi.business.abstracts.UserService;
import halicmobilya.urun_takip_uygulamasi.core.entities.concretes.User;
import halicmobilya.urun_takip_uygulamasi.core.utilities.results.ErrorDataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin
public class UsersController {
    private UserService userService;

    @Autowired
    public UsersController(UserService userService){
        super();
        this.userService = userService;
    }

    /* @PostMapping(value = "/v1/users/signUp")
    public ResponseEntity<?> signUp(@Valid @RequestBody User user){
        return ResponseEntity.ok(this.userService.signUp(user));
    }

    @PostMapping(value = "/v1/users/logIn")
    public ResponseEntity<?> logIn(@RequestBody User user){
        return ResponseEntity.ok(this.userService.logIn(user.getEmail(), user.getPassword()));
    } */

    @PostMapping(value = "/v1/users/addUser")
    public ResponseEntity<?> addUser(@RequestBody User user){
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return ResponseEntity.ok(this.userService.addUser(user));
    }

    @PutMapping(value = "/v1/users/updateUser")
    public ResponseEntity<?> updateUser(@Valid @RequestBody User user){
        return ResponseEntity.ok(this.userService.updateUser(user));
    }

    @DeleteMapping(value = "/v1/users/deleteUser")
    public ResponseEntity<?> deleteUser(@RequestParam int id){
        return ResponseEntity.ok(this.userService.deleteUser(id));
    }

    @GetMapping(value = "/v1/users/getAllUser")
    public ResponseEntity<?> getAllUser(){
        return ResponseEntity.ok(this.userService.getAllUser());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions){
        Map<String, String> validationErrors = new HashMap<String, String>();
        for(FieldError fieldError : exceptions.getBindingResult().getFieldErrors()){
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        ErrorDataResult<Object> errors = new ErrorDataResult<Object>(validationErrors, "Doğrulama işlemi başarısız oldu.");
        return errors;
    }
}
