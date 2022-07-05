package halicmobilya.urun_takip_uygulamasi.dataAccess.abstracts;

import halicmobilya.urun_takip_uygulamasi.entities.concretes.Size;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SizeDao extends JpaRepository<Size, Integer> {

}
