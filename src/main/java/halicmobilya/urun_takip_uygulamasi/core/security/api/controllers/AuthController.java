package halicmobilya.urun_takip_uygulamasi.core.security.api.controllers;

import halicmobilya.urun_takip_uygulamasi.business.abstracts.UserService;
import halicmobilya.urun_takip_uygulamasi.core.entities.concretes.User;
import halicmobilya.urun_takip_uygulamasi.core.security.service.JwtUserDetailsService;
import halicmobilya.urun_takip_uygulamasi.core.security.utilities.JwtTokenUtil;
import halicmobilya.urun_takip_uygulamasi.core.utilities.results.ErrorResult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class AuthController {
    protected final Log logger = LogFactory.getLog(getClass());
    private UserService userService;
    private AuthenticationManager authenticationManager;
    private JwtUserDetailsService userDetailsService;
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    public AuthController(UserService userService, AuthenticationManager authenticationManager, JwtUserDetailsService userDetailsService, JwtTokenUtil jwtTokenUtil) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping(value = "/v1/auth/signUp")
    public ResponseEntity<?> signUp(@Valid @RequestBody User user){
        if(this.userService.getByUsername(user.getUsername()).getData() == null){
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            return ResponseEntity.ok(this.userService.signUp(user));
        } else {
            return ResponseEntity.status(400).body(new ErrorResult("Kullanıcı Adı zaten kullanılmaktadır."));
        }
    }

    @PostMapping(value = "/v1/auth/logIn")
    public ResponseEntity<?> logIn(@RequestBody User user){
        Map<String, Object> responseMap = new HashMap<>();
        try {
            if(this.userService.getByUsername(user.getUsername()).getData() != null){
                Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
                if (auth.isAuthenticated()) {
                    logger.info("Logged In");
                    UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
                    String token = jwtTokenUtil.generateToken(userDetails);
                    return ResponseEntity.ok(this.userService.logIn(user.getUsername(), token));
                } else {
                    return ResponseEntity.status(401).body(new ErrorResult("Kullanıcı Adı veya Şifre yanlış."));
                }
            } else {
                return ResponseEntity.status(404).body(new ErrorResult("Kullanıcı Adı veya Şifre yanlış."));
            }
        } catch (DisabledException e) {
            e.printStackTrace();
            responseMap.put("error", true);
            responseMap.put("message", "User is disabled");
            return ResponseEntity.status(500).body(responseMap);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body(new ErrorResult("Kullanıcı Adı veya Şifre yanlış."));
        } catch (Exception e) {
            e.printStackTrace();
            responseMap.put("error", true);
            responseMap.put("message", "Something went wrong");
            return ResponseEntity.status(500).body(responseMap);
        }
    }
}
