package halicmobilya.urun_takip_uygulamasi.business.concretes;

import halicmobilya.urun_takip_uygulamasi.business.abstracts.ProcessTypeService;
import halicmobilya.urun_takip_uygulamasi.core.utilities.results.DataResult;
import halicmobilya.urun_takip_uygulamasi.core.utilities.results.Result;
import halicmobilya.urun_takip_uygulamasi.core.utilities.results.SuccessDataResult;
import halicmobilya.urun_takip_uygulamasi.core.utilities.results.SuccessResult;
import halicmobilya.urun_takip_uygulamasi.dataAccess.abstracts.ProcessTypeDao;
import halicmobilya.urun_takip_uygulamasi.entities.concretes.ProcessType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessTypeManager implements ProcessTypeService {
    private ProcessTypeDao processTypeDao;

    @Autowired
    public ProcessTypeManager(ProcessTypeDao processTypeDao){
        super();
        this.processTypeDao = processTypeDao;
    }

    @Override
    public Result addProcessType(ProcessType processType) {
        this.processTypeDao.save(processType);
        return new SuccessResult("İşlem türü başarıyla eklendi.");
    }

    @Override
    public DataResult<List<ProcessType>> getAllProcessType() {
        return new SuccessDataResult<List<ProcessType>>(this.processTypeDao.findAll(), "Tüm işlem türleri listelendi.");
    }
}
