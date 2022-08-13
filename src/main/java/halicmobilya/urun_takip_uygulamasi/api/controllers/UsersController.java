package halicmobilya.urun_takip_uygulamasi.api.controllers;

import halicmobilya.urun_takip_uygulamasi.business.abstracts.UserService;
import halicmobilya.urun_takip_uygulamasi.core.entities.concretes.User;
import halicmobilya.urun_takip_uygulamasi.core.utilities.results.ErrorResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping(value = "/v1/users/updateUser")
    public ResponseEntity<?> updateUser(@RequestBody User user){
        if(this.userService.getByUsername(user.getUsername()).getData() == null || this.userService.getByUsername(user.getUsername()).getData().getUserId() == user.getUserId()){
            if(user.getPassword() != ""){
                user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            }
            return ResponseEntity.ok(this.userService.updateUser(user));
        } else {
            return ResponseEntity.status(400).body(new ErrorResult("Email zaten kullanılmaktadır."));
        }
    }

    @DeleteMapping(value = "/v1/users/deleteUser")
    public ResponseEntity<?> deleteUser(@RequestParam int id){
        return ResponseEntity.ok(this.userService.deleteUser(id));
    }

    @GetMapping(value = "/v1/users/getAllUser")
    public ResponseEntity<?> getAllUser(){
        return ResponseEntity.ok(this.userService.getAllUser());
    }

    @GetMapping(value = "/v1/users/getByUsername")
    public ResponseEntity<?> getByUsername(@RequestParam String username){
        return ResponseEntity.ok(this.userService.getByUsername(username));
    }

    @GetMapping(value = "/v1/users/getByEmail")
    public ResponseEntity<?> getByEmail(@RequestParam String email){
        return ResponseEntity.ok(this.userService.getByEmail(email));
    }
}
