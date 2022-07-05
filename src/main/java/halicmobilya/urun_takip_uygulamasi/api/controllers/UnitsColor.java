package halicmobilya.urun_takip_uygulamasi.api.controllers;

import halicmobilya.urun_takip_uygulamasi.business.abstracts.UnitService;
import halicmobilya.urun_takip_uygulamasi.entities.concretes.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin
public class UnitsColor {
    private UnitService unitService;

    @Autowired
    public UnitsColor(UnitService unitService) {
        this.unitService = unitService;
    }

    @PostMapping(value = "/v1/units/addUnit")
    public ResponseEntity<?> addUnit(@RequestBody Unit unit){
        return ResponseEntity.ok(this.unitService.addUnit(unit));
    }

    @GetMapping(value = "/v1/units/getAllUnit")
    public ResponseEntity<?> getAllUnit(){
        return ResponseEntity.ok(this.unitService.getAllUnit());
    }
}
