package halicmobilya.urun_takip_uygulamasi.api.controllers;

import halicmobilya.urun_takip_uygulamasi.business.abstracts.ColorService;
import halicmobilya.urun_takip_uygulamasi.entities.concretes.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin
public class ColorsController {
    private ColorService colorService;

    @Autowired
    public ColorsController(ColorService colorService){
        super();
        this.colorService = colorService;
    }

    @PostMapping(value = "/v1/colors/addColor")
    public ResponseEntity<?> addColor(@RequestBody Color color){
        return ResponseEntity.ok(this.colorService.addColor(color));
    }

    @GetMapping(value = "/v1/colors/getAllColor")
    public ResponseEntity<?> getAllColor(){
        return ResponseEntity.ok(this.colorService.getAllColor());
    }
}
