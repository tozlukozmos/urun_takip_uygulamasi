package halicmobilya.urun_takip_uygulamasi.api.controllers;

import halicmobilya.urun_takip_uygulamasi.business.abstracts.ProcessTypeService;
import halicmobilya.urun_takip_uygulamasi.entities.concretes.ProcessType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin
public class ProcessTypesController {
    private ProcessTypeService processTypeService;

    @Autowired
    public ProcessTypesController(ProcessTypeService processTypeService){
        super();
        this.processTypeService = processTypeService;
    }

    @PostMapping(value = "/v1/processTypes/addProcessType")
    public ResponseEntity<?> addProcessType(@Valid @RequestBody ProcessType processType){
        return ResponseEntity.ok(this.processTypeService.addProcessType(processType));
    }

    @GetMapping(value = "/v1/processTypes/getAllProcessType")
    public ResponseEntity<?> getAllProcessType(){
        return ResponseEntity.ok(this.processTypeService.getAllProcessType());
    }
}
