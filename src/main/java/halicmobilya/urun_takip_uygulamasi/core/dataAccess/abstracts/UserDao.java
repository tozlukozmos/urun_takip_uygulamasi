package halicmobilya.urun_takip_uygulamasi.core.dataAccess.abstracts;

import halicmobilya.urun_takip_uygulamasi.core.entities.concretes.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {
    User getByEmailAndPassword(String email, String password);

    User getByEmail(String email);
}
