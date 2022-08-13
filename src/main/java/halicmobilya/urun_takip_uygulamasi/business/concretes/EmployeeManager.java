package halicmobilya.urun_takip_uygulamasi.business.concretes;

import halicmobilya.urun_takip_uygulamasi.business.abstracts.EmployeeService;
import halicmobilya.urun_takip_uygulamasi.core.utilities.results.DataResult;
import halicmobilya.urun_takip_uygulamasi.core.utilities.results.Result;
import halicmobilya.urun_takip_uygulamasi.core.utilities.results.SuccessDataResult;
import halicmobilya.urun_takip_uygulamasi.core.utilities.results.SuccessResult;
import halicmobilya.urun_takip_uygulamasi.dataAccess.abstracts.EmployeeDao;
import halicmobilya.urun_takip_uygulamasi.entities.concretes.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeManager implements EmployeeService {
    private EmployeeDao employeeDao;

    @Autowired
    public EmployeeManager(EmployeeDao employeeDao){
        super();
        this.employeeDao = employeeDao;
    }

    @Override
    public Result addEmployee(Employee employee) {
        this.employeeDao.save(employee);
        return new SuccessResult("Personel başarıyla eklendi.");
    }

    @Override
    public DataResult<Employee> findById(int id) {
        return new SuccessDataResult<Employee>(this.employeeDao.findById(id).get(), "Personel başarıyla bulundu.");
    }

    @Override
    public DataResult<Employee> updateEmployee(Employee employee) {
        return new SuccessDataResult<Employee>(this.employeeDao.save(employee), "Personel başarıyla güncellendi.");
    }

    @Override
    public Result deleteEmployee(int id) {
        this.employeeDao.deleteById(id);
        return new SuccessResult("Personel başarıyla silindi.");
    }

    @Override
    public DataResult<List<Employee>> getAllEmployee() {
        return new SuccessDataResult<List<Employee>>(this.employeeDao.findAll(), "Tüm personel listelendi.");
    }

    @Override
    public DataResult<List<Employee>> getByDepartmentName(String departmentName) {
        return new SuccessDataResult<List<Employee>>(this.employeeDao.getByDepartmentName(departmentName), "Alakalı tüm personel listelendi.");
    }

    @Override
    public DataResult<Employee> getByEmail(String email) {
        return new SuccessDataResult<Employee>(this.employeeDao.getByEmail(email), "Personel başarıyla bulundu.");
    }
}
