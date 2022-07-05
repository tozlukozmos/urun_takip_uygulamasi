package halicmobilya.urun_takip_uygulamasi.dataAccess.abstracts;

import halicmobilya.urun_takip_uygulamasi.entities.concretes.Material;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaterialDao extends JpaRepository<Material, Integer> {
    List<Material> getByColor_ColorId(int id);
    List<Material> getByType_TypeId(int id);
    List<Material> getByModel_ModelId(int id);
    List<Material> getByModel_ModelIdAndType_TypeIdAndColor_ColorId(int modelId, int typeId, int colorId);
    Material getByReferenceNumber(Long referenceNumber);
}
