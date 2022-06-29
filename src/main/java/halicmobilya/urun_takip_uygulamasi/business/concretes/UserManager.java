package halicmobilya.urun_takip_uygulamasi.business.concretes;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import halicmobilya.urun_takip_uygulamasi.core.utilities.results.DataResult;
import halicmobilya.urun_takip_uygulamasi.core.utilities.results.Result;
import halicmobilya.urun_takip_uygulamasi.core.utilities.results.SuccessDataResult;
import halicmobilya.urun_takip_uygulamasi.core.utilities.results.SuccessResult;
import halicmobilya.urun_takip_uygulamasi.business.abstracts.UserService;
import halicmobilya.urun_takip_uygulamasi.core.dataAccess.abstracts.UserDao;
import halicmobilya.urun_takip_uygulamasi.core.entities.concretes.User;


@Service
public class UserManager implements UserService {
	
	
private UserDao userDao;
	
	@Autowired
	public UserManager(UserDao userDao) {
		super();
		this.userDao = userDao;
	}
	

	public DataResult<List<User>>  getAll() {
		
		return new SuccessDataResult<List<User>>(this.userDao.findAll(),"Kişiler Listelendi");
				
	}

	
	public Result add(User user) {
		this.userDao.save(user);
		return new SuccessResult("Kişi Eklendi");
	}
	
	public Result deleteById(int id) {
		this.userDao.deleteById(id);
		return new SuccessResult("Kişi Silindi");
	}
	
	public DataResult findById(int id) {
		
		return new SuccessDataResult<>(this.userDao.findById(id),"Kişi Listelendi") ;
	}
	
	public Result update(User user) {
		this.userDao.save(user);
		return new SuccessResult("Kişi Güncellendi");
	}
	

	
	
	

}
