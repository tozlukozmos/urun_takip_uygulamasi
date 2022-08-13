package halicmobilya.urun_takip_uygulamasi.business.concretes;

import halicmobilya.urun_takip_uygulamasi.business.abstracts.MaterialService;
import halicmobilya.urun_takip_uygulamasi.core.utilities.results.*;
import halicmobilya.urun_takip_uygulamasi.dataAccess.abstracts.MaterialDao;
import halicmobilya.urun_takip_uygulamasi.dataAccess.abstracts.ProcessDao;
import halicmobilya.urun_takip_uygulamasi.entities.concretes.Material;
import halicmobilya.urun_takip_uygulamasi.entities.concretes.Process;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaterialManager implements MaterialService {
    private MaterialDao materialDao;
    private ProcessDao processDao;

    @Autowired
    public MaterialManager(MaterialDao materialDao, ProcessDao processDao) {
        super();
        this.materialDao = materialDao;
        this.processDao = processDao;
    }

    /* @Override
    public DataResult<Material> addMaterial(Material material, Process process) {
        this.processDao.save(process);
        return new SuccessDataResult<Material>(this.materialDao.save(material), "Materyal başarıyla eklendi.");
    }*/

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
    public DataResult<Material> getByReferenceNumber(Long referenceNumber) {
        return new SuccessDataResult<Material>(this.materialDao.getByReferenceNumber(referenceNumber), "Materyal başarıyla getirildi.");
    }

    @Override
    public DataResult<Material> getByMaterialNameAndTypeNameAndUnitNameAndSizeNameAndColorName(String materialName, String typeName, String unitName, String sizeName, String colorName) {
        return new SuccessDataResult<Material>(this.materialDao.getByMaterialNameAndTypeNameAndUnitNameAndSizeNameAndColorName(materialName, typeName, unitName, sizeName, colorName), "Materyal başarıyla getirildi.");
    }

}
