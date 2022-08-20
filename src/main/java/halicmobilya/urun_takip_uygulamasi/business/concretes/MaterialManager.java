package halicmobilya.urun_takip_uygulamasi.business.concretes;

import halicmobilya.urun_takip_uygulamasi.business.abstracts.MaterialService;
import halicmobilya.urun_takip_uygulamasi.core.utilities.results.*;
import halicmobilya.urun_takip_uygulamasi.dataAccess.abstracts.MaterialDao;
import halicmobilya.urun_takip_uygulamasi.entities.concretes.Material;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
    public DataResult<Material> addMaterial(Material material, MultipartFile file) {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        material.setImageUrl(fileName);
        Material savedMaterial = this.materialDao.save(material);
        String uploadDir = "/images/materials/" + savedMaterial.getMaterialId();
        Path uploadPath = Paths.get(uploadDir);

        if(!Files.exists(uploadPath)) {
            try {
                Files.createDirectories(uploadPath);
                InputStream inputStream = file.getInputStream();
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException("Could not initialize folder for upload!");
            }
        }

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
