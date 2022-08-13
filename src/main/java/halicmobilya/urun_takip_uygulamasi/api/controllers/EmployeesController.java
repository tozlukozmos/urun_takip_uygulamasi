package halicmobilya.urun_takip_uygulamasi.api.controllers;

import halicmobilya.urun_takip_uygulamasi.business.abstracts.EmployeeService;
import halicmobilya.urun_takip_uygulamasi.entities.concretes.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin
public class EmployeesController {
    private EmployeeService employeeService;

    @Autowired
    public EmployeesController(EmployeeService employeeService){
        super();
        this.employeeService = employeeService;
    }

    @PostMapping(value = "/v1/employees/addEmployee")
    public ResponseEntity<?> addEmployee(@Valid @RequestBody Employee employee){
        return ResponseEntity.ok(this.employeeService.addEmployee(employee));
    }

    @GetMapping(value = "/v1/employees/findById")
    public ResponseEntity<?> findById(@RequestParam int id){
        return ResponseEntity.ok(this.employeeService.findById(id));
    }

    @PatchMapping(value = "/v1/employees/updateEmployee")
    public ResponseEntity<?> updateEmployee(@Valid @RequestBody Employee employee){
        return ResponseEntity.ok(this.employeeService.updateEmployee(employee));
    }

    @DeleteMapping(value = "/v1/employees/deleteEmployee")
    public ResponseEntity<?> deleteEmployee(@RequestParam int id){
        return ResponseEntity.ok(this.employeeService.deleteEmployee(id));
    }

    @GetMapping(value = "/v1/employees/getAllEmployee")
    public ResponseEntity<?> getAllEmployee(){
        return ResponseEntity.ok(this.employeeService.getAllEmployee());
    }

    @GetMapping(value = "/v1/employees/getByDepartmentName")
    public ResponseEntity<?> getByDepartmentName(@RequestParam String departmentName){
        return ResponseEntity.ok(this.employeeService.getByDepartmentName(departmentName));
    }

    @GetMapping(value = "/v1/employees/getByEmail")
    public ResponseEntity<?> getByEmail(@RequestParam String email){
        return ResponseEntity.ok(this.employeeService.getByEmail(email));
    }
}
