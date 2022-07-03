package halicmobilya.urun_takip_uygulamasi.core.dataAccess.abstracts;

import halicmobilya.urun_takip_uygulamasi.core.entities.concretes.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentDao extends JpaRepository<Department, Integer> {

}
