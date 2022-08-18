package halicmobilya.urun_takip_uygulamasi.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import halicmobilya.urun_takip_uygulamasi.business.abstracts.MaterialService;
import halicmobilya.urun_takip_uygulamasi.core.entities.concretes.User;
import halicmobilya.urun_takip_uygulamasi.core.utilities.results.ErrorResult;
import halicmobilya.urun_takip_uygulamasi.entities.concretes.Material;
import halicmobilya.urun_takip_uygulamasi.entities.concretes.Process;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

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

    /*@PostMapping(value = "/v1/materials/addMaterial")
    public ResponseEntity<?> addMaterial(@Valid @RequestBody Material material, @Valid @RequestBody Process process){
        if(this.materialService.getByReferenceNumber(material.getReferenceNumber()).getData() == null){
            return ResponseEntity.ok(this.materialService.addMaterial(material, process));
        } else {
            return ResponseEntity.status(400).body(new ErrorResult("QR kod başka bir materyale tanımlanmıştır."));
        }
    }*/

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
    public ResponseEntity<?> addMaterial(@Valid @RequestParam("material") Material material, @RequestParam("file") MultipartFile file){
        if(this.materialService.getByReferenceNumber(material.getReferenceNumber()).getData() == null){
            if(this.materialService.getByMaterialNameAndTypeNameAndUnitNameAndSizeNameAndColorName(material.getMaterialName(), material.getTypeName(), material.getUnitName(), material.getSizeName(), material.getColorName()).getData() == null) {
                return ResponseEntity.ok(this.materialService.addMaterial(material, file));
            } else {
                Material currentMaterial = this.materialService.getByMaterialNameAndTypeNameAndUnitNameAndSizeNameAndColorName(material.getMaterialName(), material.getTypeName(), material.getUnitName(), material.getSizeName(), material.getColorName()).getData();
                currentMaterial.setAmount(currentMaterial.getAmount() + material.getAmount());
                System.out.println(currentMaterial.getAmount());
                return this.updateMaterial(currentMaterial);
                // return ResponseEntity.status(400).body(new ErrorResult("Aynı materyal zaten kayıtlıdır."));
            }
        } else {
            return ResponseEntity.status(400).body(new ErrorResult("QR kod başka bir materyale tanımlanmıştır."));
        }
    }

    @PatchMapping(value = "/v1/materials/updateMaterial")
    public ResponseEntity<?> updateMaterial(@Valid @RequestBody Material material){
        return ResponseEntity.ok(this.materialService.updateMaterial(material));
    }

    @GetMapping(value = "/v1/materials/getAllMaterials")
    public ResponseEntity<?> getAllMaterials(){
        return ResponseEntity.ok(this.materialService.getAllMaterial());
    }

    @GetMapping(value = "/v1/materials/getById")
    public ResponseEntity<?> getById(@RequestParam("id") int id){
        return ResponseEntity.ok(this.materialService.getById(id));
    }

    @GetMapping(value = "/v1/materials/getByReferenceNumber")
    public ResponseEntity<?> getByReferenceNumber(@RequestParam("referenceNumber") Long referenceNumber){
        return ResponseEntity.ok(this.materialService.getByReferenceNumber(referenceNumber));
    }

}
