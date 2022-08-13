package halicmobilya.urun_takip_uygulamasi.dataAccess.abstracts;

import halicmobilya.urun_takip_uygulamasi.entities.concretes.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeDao extends JpaRepository<Employee, Integer> {
    Employee getByFirstName(String firstName);
    Employee getByLastName(String lastName);
    List<Employee> getByDepartmentName(String departmentName);
    Employee getByEmail(String email);
}
