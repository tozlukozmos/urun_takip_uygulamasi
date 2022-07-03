package halicmobilya.urun_takip_uygulamasi.dataAccess.abstracts;

import halicmobilya.urun_takip_uygulamasi.entities.concretes.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelDao extends JpaRepository<Model, Integer> {

}
