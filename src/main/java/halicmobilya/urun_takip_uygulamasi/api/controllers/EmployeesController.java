package halicmobilya.urun_takip_uygulamasi.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import halicmobilya.urun_takip_uygulamasi.business.abstracts.EmployeeService;
import halicmobilya.urun_takip_uygulamasi.core.entities.concretes.User;
import halicmobilya.urun_takip_uygulamasi.entities.concretes.Employee;
import halicmobilya.urun_takip_uygulamasi.entities.concretes.Material;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    /*@GetMapping("/v1/ping")
    public String ping() {
        return "pong";
    }*/

    @Component
    public static class StringToEmployeeConverter implements Converter<String, Employee> {
        private final ObjectMapper objectMapper;
        public StringToEmployeeConverter(ObjectMapper objectMapper) {
            this.objectMapper = objectMapper;
        }
        @Override
        @SneakyThrows
        public Employee convert(String source) {
            return objectMapper.readValue(source, Employee.class);
        }
    }

    @PostMapping(value = "/v1/employees/addEmployee")
    public ResponseEntity<?> addEmployee(@Valid @RequestParam("employee") Employee employee,  @RequestParam(value = "file", required = false) MultipartFile file){
        return ResponseEntity.ok(this.employeeService.addEmployee(employee, file));
    }

    @GetMapping(value = "/v1/employees/findById")
    public ResponseEntity<?> findById(@RequestParam int id){
        return ResponseEntity.ok(this.employeeService.findById(id));
    }

    @PatchMapping(value = "/v1/employees/updateEmployee")
    public ResponseEntity<?> updateEmployee(@Valid @RequestParam("employee") Employee employee, @RequestParam(value = "file", required = false) MultipartFile file){
        return ResponseEntity.ok(this.employeeService.updateEmployee(employee, file));
    }

    @DeleteMapping(value = "/v1/employees/deleteEmployee/{employeeId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable int employeeId){
        return ResponseEntity.ok(this.employeeService.deleteEmployee(employeeId));
    }

    /*@DeleteMapping(value = "/v1/employees/deleteEmployees")
    public ResponseEntity<?> deleteEmployees(){
        // List<Integer> elements = Arrays.asList();
        List<Integer> elements = new ArrayList<>();
        elements.add(5);
        elements.add(4);
        return ResponseEntity.ok(this.employeeService.deleteEmployeesWithIds(elements));
    }*/

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

    @GetMapping(value = "/v1/images/employees/{employeeId}")
    public ResponseEntity<?> getImageByEmployeeId(@PathVariable int employeeId, HttpServletRequest request){
        Employee employee = this.employeeService.findById(employeeId).getData();

        String mimeType = request.getServletContext().getMimeType(employee.getImageName());

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(mimeType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + employee.getImageName())
                .body(employee.getImageData());
    }
}
