package halicmobilya.urun_takip_uygulamasi.business.concretes;

import halicmobilya.urun_takip_uygulamasi.business.abstracts.EmployeeService;
import halicmobilya.urun_takip_uygulamasi.core.utilities.results.DataResult;
import halicmobilya.urun_takip_uygulamasi.core.utilities.results.Result;
import halicmobilya.urun_takip_uygulamasi.core.utilities.results.SuccessDataResult;
import halicmobilya.urun_takip_uygulamasi.core.utilities.results.SuccessResult;
import halicmobilya.urun_takip_uygulamasi.dataAccess.abstracts.EmployeeDao;
import halicmobilya.urun_takip_uygulamasi.entities.concretes.Employee;
import halicmobilya.urun_takip_uygulamasi.entities.concretes.Material;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
    public Result addEmployee(Employee employee, MultipartFile file) {
        /*if(file != null) {
            try {
                String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                employee.setImageName(fileName);
                employee.setImageData(file.getBytes());
                this.employeeDao.save(employee);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } else {
            this.employeeDao.save(employee);
        }*/

        /*if(file == null) {
            this.employeeDao.save(employee);
        } else {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            employee.setImageUrl(fileName);
            Employee savedEmployee = this.employeeDao.save(employee);
            String uploadDir = "./images/employees/" + savedEmployee.getEmployeeId();
            Path uploadPath = Paths.get(uploadDir);

            if(!Files.exists(uploadPath)) {
                try {
                    Files.createDirectories(uploadPath);
                    InputStream inputStream = file.getInputStream();
                    Path filePath = uploadPath.resolve(fileName);
                    Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    throw new RuntimeException("Could not initialize folder for upload!");
                }
            }
        }*/
        this.employeeDao.save(employee);
        return new SuccessResult("Personel başarıyla eklendi.");
    }

    @Override
    public DataResult<Employee> findById(int id) {
        return new SuccessDataResult<Employee>(this.employeeDao.findById(id).get(), "Personel başarıyla bulundu.");
    }

    @Override
    public DataResult<Employee> updateEmployee(Employee employee, MultipartFile file) {

        /*if(file != null) {
            try {
                String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                employee.setImageName(fileName);
                employee.setImageData(file.getBytes());
                return new SuccessDataResult<Employee>(this.employeeDao.save(employee), "Personel başarıyla güncellendi.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } else {
            return new SuccessDataResult<Employee>(this.employeeDao.save(employee), "Personel başarıyla güncellendi.");
        }*/

        /*if(file != null) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            employee.setImageUrl(fileName);
            Employee savedEmployee = this.employeeDao.save(employee);
            String uploadDir = "./images/employees/" + savedEmployee.getEmployeeId();
            Path uploadPath = Paths.get(uploadDir);

            if(!Files.exists(uploadPath)) {
                try {
                    Files.createDirectories(uploadPath);
                    InputStream inputStream = file.getInputStream();
                    Path filePath = uploadPath.resolve(fileName);
                    Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    throw new RuntimeException("Could not initialize folder for upload!");
                }
            }

            return new SuccessDataResult<Employee>(savedEmployee, "Personel başarıyla güncellendi.");
        } else {
            return new SuccessDataResult<Employee>(this.employeeDao.save(employee), "Personel başarıyla güncellendi.");
        }*/

        return new SuccessDataResult<Employee>(this.employeeDao.save(employee), "Personel başarıyla güncellendi.");
    }

    @Override
    public Result deleteEmployee(int id) {
        this.employeeDao.deleteById(id);
        return new SuccessResult("Personel başarıyla silindi.");
    }

    /*@Transactional
    @Override
    public Result deleteEmployeesWithIds(List<Integer> ids) {
        this.employeeDao.deleteEmployeesWithIds(ids);
        return new SuccessResult("Personeller başarıyla silindi.");
    }*/

    @Override
    public DataResult<List<Employee>> getAllEmployee() {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        return new SuccessDataResult<List<Employee>>(this.employeeDao.findAll(sort), "Tüm personel listelendi.");
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
