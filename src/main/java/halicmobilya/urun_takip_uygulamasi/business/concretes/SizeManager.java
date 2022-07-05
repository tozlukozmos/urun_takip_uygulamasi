package halicmobilya.urun_takip_uygulamasi.business.concretes;

import halicmobilya.urun_takip_uygulamasi.business.abstracts.SizeService;
import halicmobilya.urun_takip_uygulamasi.core.utilities.results.DataResult;
import halicmobilya.urun_takip_uygulamasi.core.utilities.results.Result;
import halicmobilya.urun_takip_uygulamasi.core.utilities.results.SuccessDataResult;
import halicmobilya.urun_takip_uygulamasi.core.utilities.results.SuccessResult;
import halicmobilya.urun_takip_uygulamasi.dataAccess.abstracts.SizeDao;
import halicmobilya.urun_takip_uygulamasi.entities.concretes.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeManager implements SizeService {
    private SizeDao sizeDao;

    @Autowired
    public SizeManager(SizeDao sizeDao) {
        this.sizeDao = sizeDao;
    }

    @Override
    public Result addSize(Size size) {
        this.sizeDao.save(size);
        return new SuccessResult("Materyal boyutu başarıyla eklendi.");
    }

    @Override
    public DataResult<List<Size>> getAllSize() {
        return new SuccessDataResult<List<Size>>(this.sizeDao.findAll(), "Tüm materyal boyutları listelendi.");
    }
}
