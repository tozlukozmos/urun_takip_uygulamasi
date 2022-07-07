package halicmobilya.urun_takip_uygulamasi.api.controllers;

import halicmobilya.urun_takip_uygulamasi.business.abstracts.TypeService;
import halicmobilya.urun_takip_uygulamasi.entities.concretes.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin
public class TypesController {
    private TypeService typeService;

    @Autowired
    public  TypesController(TypeService typeService){
        super();
        this.typeService = typeService;
    }

    @PostMapping(value = "/v1/types/addType")
    public ResponseEntity<?> addType(@Valid @RequestBody Type type){
        return ResponseEntity.ok(this.typeService.addType(type));
    }

    @GetMapping(value = "/v1/types/getAllType")
    public ResponseEntity<?> getAllType(){
        return ResponseEntity.ok(this.typeService.getAllType());
    }
}
