package halicmobilya.urun_takip_uygulamasi.business.abstracts;

import halicmobilya.urun_takip_uygulamasi.core.utilities.results.DataResult;
import halicmobilya.urun_takip_uygulamasi.core.utilities.results.Result;
import halicmobilya.urun_takip_uygulamasi.entities.concretes.Material;

import java.util.List;
import java.util.Optional;

public interface MaterialService {
    Result addMaterial(Material material);
    DataResult<Material> updateMaterial(Material material);
    DataResult<List<Material>> getAllMaterial();
    DataResult<Optional<Material>> getById(int id);
    DataResult<List<Material>> getAllByColorId(int id);
    DataResult<List<Material>> getAllByTypeId(int id);
    DataResult<List<Material>> getAllByModelId(int id);

    DataResult<List<Material>> getAllByModelIdAndTypeIdAndColorId(int modelId, int typeId, int colorId);
}
