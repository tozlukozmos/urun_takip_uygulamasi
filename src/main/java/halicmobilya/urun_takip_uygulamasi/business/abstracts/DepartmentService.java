package halicmobilya.urun_takip_uygulamasi.business.abstracts;

import halicmobilya.urun_takip_uygulamasi.core.entities.concretes.Department;
import halicmobilya.urun_takip_uygulamasi.core.utilities.results.DataResult;
import halicmobilya.urun_takip_uygulamasi.core.utilities.results.Result;

import java.util.List;

public interface DepartmentService {
    Result addDepartment(Department department);
    DataResult<List<Department>> getAllDepartment();
}
