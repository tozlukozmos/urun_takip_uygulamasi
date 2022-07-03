package halicmobilya.urun_takip_uygulamasi.api.controllers;

import halicmobilya.urun_takip_uygulamasi.business.abstracts.ProcessService;
import halicmobilya.urun_takip_uygulamasi.entities.concretes.Material;
import halicmobilya.urun_takip_uygulamasi.entities.concretes.Process;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin
public class ProcessesController {
    private ProcessService processService;

    @Autowired
    public ProcessesController(ProcessService processService){
        super();
        this.processService = processService;
    }

    @PostMapping(value = "/v1/processes/addProcess")
    public ResponseEntity<?> addProcess(@RequestBody Process process){
        return ResponseEntity.ok(this.processService.addProcess(process));
    }

    @GetMapping(value = "/v1/processes/getAllProcess")
    public ResponseEntity<?> getAllProcess(){
        return ResponseEntity.ok(this.processService.getAllProcess());
    }

}
