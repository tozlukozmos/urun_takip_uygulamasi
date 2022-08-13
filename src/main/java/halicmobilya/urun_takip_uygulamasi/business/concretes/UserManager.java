package halicmobilya.urun_takip_uygulamasi.business.concretes;

import halicmobilya.urun_takip_uygulamasi.business.abstracts.UserService;
import halicmobilya.urun_takip_uygulamasi.core.dataAccess.abstracts.UserDao;
import halicmobilya.urun_takip_uygulamasi.core.entities.concretes.User;
import halicmobilya.urun_takip_uygulamasi.core.utilities.results.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManager implements UserService {
    private UserDao userDao;

    @Autowired
    public UserManager(UserDao userDao){
        super();
        this.userDao = userDao;
    }

    @Override
    public Result signUp(User user) {
        this.userDao.save(user);
        return new SuccessResult("Kullanıcı başarıyla eklendi.");
    }

    @Override
    public DataResult<User> logIn(String username, String token) {
        return new SuccessDataResult<User>(this.userDao.getByUsername(username), "Başarıyla giriş yapıldı.", token);
    }

    @Override
    public DataResult<User> updateUser(User user) {
        return new SuccessDataResult<User>(this.userDao.save(user), "Kullanıcı başarıyla güncellendi.");
    }

    @Override
    public Result deleteUser(int id) {
        this.userDao.deleteById(id);
        return new SuccessResult("Kullanıcı başarıyla silindi.");
    }

    @Override
    public DataResult<List<User>> getAllUser() {
        return new SuccessDataResult<List<User>>(this.userDao.findAll(), "Tüm kullanıcılar listelendi.");
    }

    @Override
    public DataResult<User> getByUsername(String username) {
        return new SuccessDataResult<User>(this.userDao.getByUsername(username), "Kullanıcı başarıyla bulundu.");
    }

    @Override
    public DataResult<User> getByEmail(String email) {
        return new SuccessDataResult<User>(this.userDao.getByEmail(email), "Kullanıcı başarıyla bulundu.");
    }
}
