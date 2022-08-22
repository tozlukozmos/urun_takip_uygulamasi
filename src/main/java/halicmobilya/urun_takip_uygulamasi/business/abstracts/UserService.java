package halicmobilya.urun_takip_uygulamasi.business.abstracts;

import halicmobilya.urun_takip_uygulamasi.core.entities.concretes.User;
import halicmobilya.urun_takip_uygulamasi.core.utilities.results.DataResult;
import halicmobilya.urun_takip_uygulamasi.core.utilities.results.Result;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    Result signUp(User user, MultipartFile file);
    DataResult<User> logIn(String username, String password);
    DataResult<User> getUserById(int id);
    DataResult<User> updateUser(User user, MultipartFile file);
    Result deleteUser(int id);
    DataResult<List<User>> getAllUser();
    DataResult<User> getByUsername(String username);
    DataResult<User> getByEmail(String email);
}
