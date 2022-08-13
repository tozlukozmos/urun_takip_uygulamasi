package halicmobilya.urun_takip_uygulamasi.dataAccess.abstracts;

import halicmobilya.urun_takip_uygulamasi.entities.concretes.Material;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaterialDao extends JpaRepository<Material, Integer> {
    Material getByReferenceNumber(Long referenceNumber);
    Material getByMaterialNameAndTypeNameAndUnitNameAndSizeNameAndColorName(
        String materialName,
        String typeName,
        String unitName,
        String sizeName,
        String colorName
    );
}
