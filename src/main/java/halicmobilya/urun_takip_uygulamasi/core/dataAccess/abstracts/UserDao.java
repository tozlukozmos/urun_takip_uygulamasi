package halicmobilya.urun_takip_uygulamasi.core.dataAccess.abstracts;
import org.springframework.data.jpa.repository.JpaRepository;
import halicmobilya.urun_takip_uygulamasi.core.entities.concretes.User;

public interface UserDao extends JpaRepository<User,Integer> {

}