package halicmobilya.urun_takip_uygulamasi.business.abstracts;

import java.util.List;
import halicmobilya.urun_takip_uygulamasi.core.utilities.results.*;
import halicmobilya.urun_takip_uygulamasi.core.entities.concretes.User;

public interface UserService {
	DataResult<List<User>>  getAll();
	Result add(User user);
	Result deleteById(int id);
	DataResult findById(int id);
	Result update(User user);
}