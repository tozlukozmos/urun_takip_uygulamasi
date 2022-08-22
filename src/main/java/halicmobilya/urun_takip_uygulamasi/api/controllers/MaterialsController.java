package halicmobilya.urun_takip_uygulamasi.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import halicmobilya.urun_takip_uygulamasi.business.abstracts.MaterialService;
import halicmobilya.urun_takip_uygulamasi.core.utilities.results.ErrorResult;
import halicmobilya.urun_takip_uygulamasi.entities.concretes.Employee;
import halicmobilya.urun_takip_uygulamasi.entities.concretes.Material;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin
public class MaterialsController {
    private MaterialService materialService;

    @Autowired
    public MaterialsController(MaterialService materialService){
        super();
        this.materialService = materialService;
    }

    @Component
    public static class StringToMaterialConverter implements Converter<String, Material> {
        private final ObjectMapper objectMapper;
        public StringToMaterialConverter(ObjectMapper objectMapper) {
            this.objectMapper = objectMapper;
        }
        @Override
        @SneakyThrows
        public Material convert(String source) {
            return objectMapper.readValue(source, Material.class);
        }
    }

    @PostMapping(value = "/v1/materials/addMaterial")
    public ResponseEntity<?> addMaterial(@Valid @RequestParam("material") Material material, @RequestParam(value = "file", required = false) MultipartFile file){ //, @RequestParam("file") MultipartFile file
        if(this.materialService.getByReferenceNumber(material.getReferenceNumber()).getData() == null){
            if(this.materialService.getByMaterialNameAndTypeNameAndUnitNameAndSizeNameAndColorName(material.getMaterialName(), material.getTypeName(), material.getUnitName(), material.getSizeName(), material.getColorName()).getData() == null) {
                return ResponseEntity.ok(this.materialService.addMaterial(material, file));
            } else {
                Material currentMaterial = this.materialService.getByMaterialNameAndTypeNameAndUnitNameAndSizeNameAndColorName(material.getMaterialName(), material.getTypeName(), material.getUnitName(), material.getSizeName(), material.getColorName()).getData();
                currentMaterial.setAmount(currentMaterial.getAmount() + material.getAmount());
                return this.updateMaterial(currentMaterial, file);
            }
        } else {
            return ResponseEntity.status(400).body(new ErrorResult("QR kod başka bir materyale tanımlanmıştır."));
        }
    }

    @PatchMapping(value = "/v1/materials/updateMaterial")
    public ResponseEntity<?> updateMaterial(@Valid @RequestParam("material") Material material, @RequestParam(value = "file", required = false) MultipartFile file){
        return ResponseEntity.ok(this.materialService.updateMaterial(material, file));
    }

    @DeleteMapping(value = "/v1/materials/deleteMaterial/{materialId}")
    public ResponseEntity<?> deleteMaterial(@PathVariable int materialId){
        return ResponseEntity.ok(this.materialService.deleteMaterial(materialId));
    }

    @GetMapping(value = "/v1/materials/getAllMaterial")
    public ResponseEntity<?> getAllMaterial(){
        return ResponseEntity.ok(this.materialService.getAllMaterial());
    }

    @GetMapping(value = "/v1/materials/getById")
    public ResponseEntity<?> getById(@RequestParam("id") int id){
        return ResponseEntity.ok(this.materialService.getById(id));
    }

    @GetMapping(value = "/v1/materials/getByReferenceNumber/{referenceNumber}")
    public ResponseEntity<?> getByReferenceNumber(@PathVariable Long referenceNumber){
        return ResponseEntity.ok(this.materialService.getByReferenceNumber(referenceNumber));
    }

    @GetMapping(value = "/v1/images/materials/{materialId}")
    public ResponseEntity<?> getImageByMaterialId(@PathVariable int materialId, HttpServletRequest request){
        Material material = this.materialService.getById(materialId).getData().get();

        String mimeType = request.getServletContext().getMimeType(material.getImageName());

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(mimeType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + material.getImageName())
                .body(material.getImageData());
    }

}
