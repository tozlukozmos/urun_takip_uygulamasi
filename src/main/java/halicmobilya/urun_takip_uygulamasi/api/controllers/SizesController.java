package halicmobilya.urun_takip_uygulamasi.api.controllers;

import halicmobilya.urun_takip_uygulamasi.business.abstracts.SizeService;
import halicmobilya.urun_takip_uygulamasi.entities.concretes.Size;
import halicmobilya.urun_takip_uygulamasi.entities.concretes.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin
public class SizesController {
    private SizeService sizeService;

    @Autowired
    public SizesController(SizeService sizeService) {
        this.sizeService = sizeService;
    }

    @PostMapping(value = "/v1/sizes/addSize")
    public ResponseEntity<?> addSize(@RequestBody Size size){
        return ResponseEntity.ok(this.sizeService.addSize(size));
    }

    @GetMapping(value = "/v1/sizes/getAllSize")
    public ResponseEntity<?> getAllSize(){
        return ResponseEntity.ok(this.sizeService.getAllSize());
    }
}
