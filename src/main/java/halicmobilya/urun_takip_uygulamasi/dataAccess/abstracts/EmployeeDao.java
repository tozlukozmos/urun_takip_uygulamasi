package halicmobilya.urun_takip_uygulamasi.dataAccess.abstracts;

import halicmobilya.urun_takip_uygulamasi.entities.concretes.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeDao extends JpaRepository<Employee, Integer> {
    Employee getByFirstName(String firstName);
    Employee getByLastName(String lastName);
    List<Employee> getByDepartmentName(String departmentName);
    Employee getByEmail(String email);
    // @Query("delete from Employee u where u.employeeId in ?1")
    // void deleteEmployeesWithIds(List<Integer> ids);

    // List<Employee> deleteAllByEmployeeId(List<Integer> ids);
}
