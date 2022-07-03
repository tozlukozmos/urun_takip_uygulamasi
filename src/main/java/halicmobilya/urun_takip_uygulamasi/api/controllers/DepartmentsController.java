package halicmobilya.urun_takip_uygulamasi.api.controllers;

import halicmobilya.urun_takip_uygulamasi.business.abstracts.DepartmentService;
import halicmobilya.urun_takip_uygulamasi.core.entities.concretes.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin
public class DepartmentsController {
    private DepartmentService departmentService;

    @Autowired
    public DepartmentsController(DepartmentService departmentService){
        super();
        this.departmentService = departmentService;
    }

    @PostMapping(value = "/v1/departments/addDepartment")
    public ResponseEntity<?> addDepartment(@RequestBody Department department){
        return ResponseEntity.ok(this.departmentService.addDepartment(department));
    }

    @GetMapping(value = "/v1/departments/getAllDepartment")
    public ResponseEntity<?> getAllDepartment(){
        return ResponseEntity.ok(this.departmentService.getAllDepartment());
    }
}
