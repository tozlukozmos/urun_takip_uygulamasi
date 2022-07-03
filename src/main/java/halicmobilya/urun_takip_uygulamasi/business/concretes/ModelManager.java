package halicmobilya.urun_takip_uygulamasi.business.concretes;

import halicmobilya.urun_takip_uygulamasi.business.abstracts.ModelService;
import halicmobilya.urun_takip_uygulamasi.core.utilities.results.DataResult;
import halicmobilya.urun_takip_uygulamasi.core.utilities.results.Result;
import halicmobilya.urun_takip_uygulamasi.core.utilities.results.SuccessDataResult;
import halicmobilya.urun_takip_uygulamasi.core.utilities.results.SuccessResult;
import halicmobilya.urun_takip_uygulamasi.dataAccess.abstracts.ModelDao;
import halicmobilya.urun_takip_uygulamasi.entities.concretes.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelManager implements ModelService {
    private ModelDao modelDao;

    @Autowired
    public ModelManager(ModelDao modelDao){
        super();
        this.modelDao = modelDao;
    }

    @Override
    public Result addModel(Model model) {
        this.modelDao.save(model);
        return new SuccessResult("Model başarıyla eklendi.");
    }

    @Override
    public DataResult<List<Model>> getAllModel() {
        return new SuccessDataResult<List<Model>>(this.modelDao.findAll(), "Tüm modeller listelendi.");
    }
}
