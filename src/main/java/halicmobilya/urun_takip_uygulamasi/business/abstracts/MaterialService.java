package halicmobilya.urun_takip_uygulamasi.business.abstracts;

import halicmobilya.urun_takip_uygulamasi.core.utilities.results.DataResult;
import halicmobilya.urun_takip_uygulamasi.core.utilities.results.Result;
import halicmobilya.urun_takip_uygulamasi.entities.concretes.Material;
import halicmobilya.urun_takip_uygulamasi.entities.concretes.Process;

import java.util.List;
import java.util.Optional;

public interface MaterialService {
    // DataResult<Material> addMaterial(Material material, Process process);
    DataResult<Material> addMaterial(Material material);
    DataResult<Material> updateMaterial(Material material);
    DataResult<List<Material>> getAllMaterial();
    DataResult<Optional<Material>> getById(int id);
    DataResult<Material> getByReferenceNumber(Long referenceNumber);
    DataResult<Material> getByMaterialNameAndTypeNameAndUnitNameAndSizeNameAndColorName(
        String materialName,
        String typeName,
        String unitName,
        String sizeName,
        String colorName
    );
}
