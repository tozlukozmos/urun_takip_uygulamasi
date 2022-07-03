package halicmobilya.urun_takip_uygulamasi.dataAccess.abstracts;

import halicmobilya.urun_takip_uygulamasi.entities.concretes.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeDao extends JpaRepository<Type, Integer> {

}
