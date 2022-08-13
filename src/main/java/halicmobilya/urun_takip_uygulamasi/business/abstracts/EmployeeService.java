package halicmobilya.urun_takip_uygulamasi.business.abstracts;

import halicmobilya.urun_takip_uygulamasi.core.utilities.results.DataResult;
import halicmobilya.urun_takip_uygulamasi.core.utilities.results.Result;
import halicmobilya.urun_takip_uygulamasi.entities.concretes.Employee;

import java.util.List;

public interface EmployeeService {
    Result addEmployee(Employee employee);
    DataResult<Employee> findById(int id);
    DataResult<Employee> updateEmployee(Employee employee);
    Result deleteEmployee(int id);
    DataResult<List<Employee>> getAllEmployee();
    DataResult<List<Employee>> getByDepartmentName(String departmentName);
    DataResult<Employee> getByEmail(String email);
}
