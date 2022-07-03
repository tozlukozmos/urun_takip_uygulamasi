package halicmobilya.urun_takip_uygulamasi.dataAccess.abstracts;

import halicmobilya.urun_takip_uygulamasi.entities.concretes.Color;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorDao extends JpaRepository<Color, Integer> {

}
