package halicmobilya.urun_takip_uygulamasi.business.concretes;

import halicmobilya.urun_takip_uygulamasi.business.abstracts.DepartmentService;
import halicmobilya.urun_takip_uygulamasi.core.dataAccess.abstracts.DepartmentDao;
import halicmobilya.urun_takip_uygulamasi.core.entities.concretes.Department;
import halicmobilya.urun_takip_uygulamasi.core.utilities.results.DataResult;
import halicmobilya.urun_takip_uygulamasi.core.utilities.results.Result;
import halicmobilya.urun_takip_uygulamasi.core.utilities.results.SuccessDataResult;
import halicmobilya.urun_takip_uygulamasi.core.utilities.results.SuccessResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentManager implements DepartmentService {
    private DepartmentDao departmentDao;

    @Autowired
    public DepartmentManager(DepartmentDao departmentDao){
        super();
        this.departmentDao = departmentDao;
    }

    @Override
    public Result addDepartment(Department department) {
        this.departmentDao.save(department);
        return new SuccessResult("Birim başarıyla eklendi.");
    }

    @Override
    public DataResult<List<Department>> getAllDepartment() {
        return new SuccessDataResult<List<Department>>(this.departmentDao.findAll(), "Tüm birimler listelendi.");
    }
}
