package halicmobilya.urun_takip_uygulamasi.dataAccess.abstracts;

import halicmobilya.urun_takip_uygulamasi.entities.concretes.ProcessType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessTypeDao extends JpaRepository<ProcessType, Integer> {

}
