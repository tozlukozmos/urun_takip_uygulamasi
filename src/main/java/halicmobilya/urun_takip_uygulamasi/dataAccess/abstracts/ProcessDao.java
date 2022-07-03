package halicmobilya.urun_takip_uygulamasi.dataAccess.abstracts;

import halicmobilya.urun_takip_uygulamasi.entities.concretes.Material;
import halicmobilya.urun_takip_uygulamasi.entities.concretes.Process;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProcessDao extends JpaRepository<Process, Integer> {

}
