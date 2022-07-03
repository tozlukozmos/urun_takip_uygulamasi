package halicmobilya.urun_takip_uygulamasi.api.controllers;

import halicmobilya.urun_takip_uygulamasi.business.abstracts.MaterialService;
import halicmobilya.urun_takip_uygulamasi.entities.concretes.Material;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/v1/materials/addMaterial")
    public ResponseEntity<?> addMaterial(@RequestBody Material material){
        return ResponseEntity.ok(this.materialService.addMaterial(material));
    }

    @PutMapping(value = "/v1/materials/updateMaterial")
    public ResponseEntity<?> updateMaterial(@RequestBody Material material){
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

    @GetMapping(value = "/v1/materials/getAllByColorId")
    public ResponseEntity<?> getAllByColorId(@RequestParam("id") int id){
        return ResponseEntity.ok(this.materialService.getAllByColorId(id));
    }

    @GetMapping(value = "/v1/materials/getAllByTypeId")
    public ResponseEntity<?> getAllByTypeId(@RequestParam("id") int id){
        return ResponseEntity.ok(this.materialService.getAllByTypeId(id));
    }

    @GetMapping(value = "/v1/materials/getAllByModelId")
    public ResponseEntity<?> getAllByModelId(@RequestParam("id") int id){
        return ResponseEntity.ok(this.materialService.getAllByModelId(id));
    }

    @GetMapping(value = "/v1/materials/getAllByModelIdAndTypeIdAndColorId")
    public ResponseEntity<?> getAllByModelIdAndTypeIdAndColorId(@RequestParam("modelId") int modelId, @RequestParam("typeId") int typeId, @RequestParam("colorId") int colorId){
        return ResponseEntity.ok(this.materialService.getAllByModelIdAndTypeIdAndColorId(modelId, typeId, colorId));
    }

}
