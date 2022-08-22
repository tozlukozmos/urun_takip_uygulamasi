package halicmobilya.urun_takip_uygulamasi.business.concretes;

import halicmobilya.urun_takip_uygulamasi.business.abstracts.UserService;
import halicmobilya.urun_takip_uygulamasi.core.dataAccess.abstracts.UserDao;
import halicmobilya.urun_takip_uygulamasi.core.entities.concretes.User;
import halicmobilya.urun_takip_uygulamasi.core.utilities.results.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    public Result signUp(User user, MultipartFile file) {
        /*if(file != null) {
            try {
                String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                user.setImageName(fileName);
                user.setImageData(file.getBytes());
                this.userDao.save(user);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } else {
            this.userDao.save(user);
        }*/
        /*if(file == null) {
            this.userDao.save(user);
        } else {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            user.setImageUrl(fileName);
            User savedUser = this.userDao.save(user);
            String uploadDir = "./images/users/" + savedUser.getUserId();
            Path uploadPath = Paths.get(uploadDir);

            if(!Files.exists(uploadPath)) {
                try {
                    Files.createDirectories(uploadPath);
                    InputStream inputStream = file.getInputStream();
                    Path filePath = uploadPath.resolve(fileName);
                    Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    throw new RuntimeException("Could not initialize folder for upload!");
                }
            }
        }*/
        this.userDao.save(user);
        return new SuccessResult("Kullanıcı başarıyla eklendi.");
    }

    @Override
    public DataResult<User> logIn(String username, String token) {
        return new SuccessDataResult<User>(this.userDao.getByUsername(username), "Başarıyla giriş yapıldı.", token);
    }

    @Override
    public DataResult<User> getUserById(int id) {
        return new SuccessDataResult<User>(this.userDao.findById(id).get());
    }

    @Override
    public DataResult<User> updateUser(User user, MultipartFile file) {
        /*if(file != null) {
            try {
                String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                user.setImageName(fileName);
                user.setImageData(file.getBytes());
                return new SuccessDataResult<User>(this.userDao.save(user), "Kullanıcı başarıyla güncellendi.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } else {
            return new SuccessDataResult<User>(this.userDao.save(user), "Kullanıcı başarıyla güncellendi.");
        }*/

        /*if(file != null) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            user.setImageUrl(fileName);
            User savedUser = this.userDao.save(user);
            String uploadDir = "./images/users/" + savedUser.getUserId();
            Path uploadPath = Paths.get(uploadDir);

            if(!Files.exists(uploadPath)) {
                try {
                    Files.createDirectories(uploadPath);
                    InputStream inputStream = file.getInputStream();
                    Path filePath = uploadPath.resolve(fileName);
                    Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    throw new RuntimeException("Could not initialize folder for upload!");
                }
            }
            return new SuccessDataResult<User>(savedUser, "Kullanıcı başarıyla güncellendi.");

        } else {
            return new SuccessDataResult<User>(this.userDao.save(user), "Kullanıcı başarıyla güncellendi.");
        }*/
        return new SuccessDataResult<User>(this.userDao.save(user), "Kullanıcı başarıyla güncellendi.");
    }

    @Override
    public Result deleteUser(int id) {
        this.userDao.deleteById(id);
        return new SuccessResult("Kullanıcı başarıyla silindi.");
    }

    @Override
    public DataResult<List<User>> getAllUser() {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        return new SuccessDataResult<List<User>>(this.userDao.findAll(sort), "Tüm kullanıcılar listelendi.");
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
