package halicmobilya.urun_takip_uygulamasi.api.controllers;

import halicmobilya.urun_takip_uygulamasi.business.abstracts.UserService;
import halicmobilya.urun_takip_uygulamasi.core.entities.concretes.User;
import halicmobilya.urun_takip_uygulamasi.core.utilities.results.ErrorResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
    public ResponseEntity<?> updateUser(@Valid @RequestParam("user") User user, @RequestParam(value = "file", required = false) MultipartFile file){
        if(this.userService.getByUsername(user.getUsername()).getData() == null || this.userService.getByUsername(user.getUsername()).getData().getUserId() == user.getUserId()){
            if(user.getPassword() != ""){
                user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            }
            return ResponseEntity.ok(this.userService.updateUser(user, file));
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

    /*@GetMapping(value = "/v1/images/users/{userId}")
    public ResponseEntity<?> getImageByUserId(@PathVariable int userId, HttpServletRequest request){
        User user = this.userService.getUserById(userId).getData();

        String mimeType = request.getServletContext().getMimeType(user.getImageName());

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(mimeType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + user.getImageName())
                .body(user.getImageData());
    }*/
}
