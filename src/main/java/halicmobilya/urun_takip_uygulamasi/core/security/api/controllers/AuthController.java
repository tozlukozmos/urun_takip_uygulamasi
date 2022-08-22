package halicmobilya.urun_takip_uygulamasi.core.security.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import halicmobilya.urun_takip_uygulamasi.business.abstracts.UserService;
import halicmobilya.urun_takip_uygulamasi.core.entities.concretes.User;
import halicmobilya.urun_takip_uygulamasi.core.security.service.JwtUserDetailsService;
import halicmobilya.urun_takip_uygulamasi.core.security.utilities.JwtTokenUtil;
import halicmobilya.urun_takip_uygulamasi.core.utilities.results.ErrorResult;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin
@Slf4j
public class AuthController {
    protected final Log logger = LogFactory.getLog(getClass());
    private UserService userService;
    private AuthenticationManager authenticationManager;
    private JwtUserDetailsService userDetailsService;
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    public AuthController(UserService userService,AuthenticationManager authenticationManager, JwtUserDetailsService userDetailsService, JwtTokenUtil jwtTokenUtil) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    /*@PostMapping(value = "/v1/auth/addFile")
    public ResponseEntity<?> addFile(@RequestParam(value = "file", required = false) MultipartFile file) throws IOException { //, @RequestParam("file") MultipartFile file

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        Image image = new Image();
        image.setImageName(fileName);
        image.setImageData(file.getBytes());

        imageService.addImage(image);

        String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/auth/")
                .path(fileName)
                .toUriString();

        String contentType = file.getContentType();

        return ResponseEntity.ok(url);
    }

    @GetMapping(value = "/v1/auth/{fileName}")
    public ResponseEntity<byte[]> getFile(@PathVariable String fileName, HttpServletRequest request) throws IOException { //, @RequestParam("file") MultipartFile file

        Image image = imageService.getByImageName(fileName).getData();

        String mimeType = request.getServletContext().getMimeType(image.getImageName());

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(mimeType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + image.getImageName())
                .body(image.getImageData());
    }*/

    @Component
    public static class StringToUserConverter implements Converter<String, User> {
        private final ObjectMapper objectMapper;
        public StringToUserConverter(ObjectMapper objectMapper) {
            this.objectMapper = objectMapper;
        }
        @Override
        @SneakyThrows
        public User convert(String source) {
            return objectMapper.readValue(source, User.class);
        }
    }

    @PostMapping(value = "/v1/auth/signUp")
    public ResponseEntity<?> signUp(@Valid @RequestParam("user") User user, @RequestParam(value = "file", required = false) MultipartFile file){
        if(this.userService.getByUsername(user.getUsername()).getData() == null){
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            return ResponseEntity.ok(this.userService.signUp(user, file));
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
