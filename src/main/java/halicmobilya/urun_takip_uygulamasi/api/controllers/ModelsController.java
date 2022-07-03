package halicmobilya.urun_takip_uygulamasi.api.controllers;

import halicmobilya.urun_takip_uygulamasi.business.abstracts.ModelService;
import halicmobilya.urun_takip_uygulamasi.entities.concretes.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin
public class ModelsController {
    private ModelService modelService;

    @Autowired
    public ModelsController(ModelService modelService){
        super();
        this.modelService = modelService;
    }

    @PostMapping(value = "/v1/models/addModel")
    public ResponseEntity<?> addModel(@RequestBody Model model){
        return ResponseEntity.ok(this.modelService.addModel(model));
    }

    @GetMapping(value = "/v1/models/getAllModel")
    public ResponseEntity<?> getAllModel(){
        return ResponseEntity.ok(this.modelService.getAllModel());
    }
}
