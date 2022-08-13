package halicmobilya.urun_takip_uygulamasi.core.dataAccess.abstracts;

import halicmobilya.urun_takip_uygulamasi.core.entities.concretes.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDao extends JpaRepository<User, Integer> {
    User getByUsername(String username);
    User getByFirstName(String firstName);
    User getByLastName(String lastName);
    List<User> getByDepartmentName(String departmentName);
    User getByEmail(String email);
    User getByUsernameAndPassword(String username, String password);
    User getByEmailAndPassword(String email, String password);
}
