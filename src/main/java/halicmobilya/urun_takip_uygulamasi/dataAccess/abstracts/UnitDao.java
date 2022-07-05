package halicmobilya.urun_takip_uygulamasi.dataAccess.abstracts;

import halicmobilya.urun_takip_uygulamasi.entities.concretes.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnitDao extends JpaRepository<Unit, Integer> {

}
