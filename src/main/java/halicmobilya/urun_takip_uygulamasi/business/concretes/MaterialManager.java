package halicmobilya.urun_takip_uygulamasi.business.concretes;

import halicmobilya.urun_takip_uygulamasi.business.abstracts.MaterialService;
import halicmobilya.urun_takip_uygulamasi.core.utilities.results.*;
import halicmobilya.urun_takip_uygulamasi.dataAccess.abstracts.MaterialDao;
import halicmobilya.urun_takip_uygulamasi.entities.concretes.Material;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaterialManager implements MaterialService {
    private MaterialDao materialDao;

    @Autowired
    public MaterialManager(MaterialDao materialDao) {
        super();
        this.materialDao = materialDao;
    }

    @Override
    public DataResult<Material> addMaterial(Material material) {
        return new SuccessDataResult<Material>(this.materialDao.save(material), "Materyal başarıyla eklendi.");
    }

    @Override
    public DataResult<Material> updateMaterial(Material material) {
        return new SuccessDataResult<Material>(this.materialDao.save(material), "Materyal başarıyla güncellendi.");
    }

    @Override
    public DataResult<List<Material>> getAllMaterial() {
        return new SuccessDataResult<List<Material>>(this.materialDao.findAll(), "Tüm materyaller listelendi.");
    }

    @Override
    public DataResult<Optional<Material>> getById(int id) {
        try {
            return new SuccessDataResult<Optional<Material>>(this.materialDao.findById(id), "Materyal başarıyla getirildi.");
        } catch (Exception e){
            return new ErrorDataResult("Materyal güncellenirken hata oluştu.");
        }
    }

    @Override
    public DataResult<List<Material>> getAllByColorId(int id) {
        return new SuccessDataResult<List<Material>>(this.materialDao.getByColor_ColorId(id), "Aynı renge sahip materyaller listelendi.");
    }

    @Override
    public DataResult<List<Material>> getAllByTypeId(int id) {
        return new SuccessDataResult<List<Material>>(this.materialDao.getByType_TypeId(id), "Aynı cinse sahip materyaller listelendi.");
    }

    @Override
    public DataResult<List<Material>> getAllByModelId(int id) {
        return new SuccessDataResult<List<Material>>(this.materialDao.getByModel_ModelId(id), "Aynı modele sahip materyaller listelendi.");
    }

    @Override
    public DataResult<List<Material>> getAllByModelIdAndTypeIdAndColorId(int modelId, int typeId, int colorId) {
        return new SuccessDataResult<List<Material>>(this.materialDao.getByModel_ModelIdAndType_TypeIdAndColor_ColorId(modelId, typeId, colorId), "Aynı materyaller listelendi.");
    }

    @Override
    public DataResult<Material> getByReferenceNumber(Long referenceNumber) {
        return new SuccessDataResult<Material>(this.materialDao.getByReferenceNumber(referenceNumber), "Materyal başarıyla getirildi.");
    }

}
