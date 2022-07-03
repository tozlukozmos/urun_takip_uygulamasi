package halicmobilya.urun_takip_uygulamasi.business.concretes;

import halicmobilya.urun_takip_uygulamasi.business.abstracts.ColorService;
import halicmobilya.urun_takip_uygulamasi.core.utilities.results.DataResult;
import halicmobilya.urun_takip_uygulamasi.core.utilities.results.Result;
import halicmobilya.urun_takip_uygulamasi.core.utilities.results.SuccessDataResult;
import halicmobilya.urun_takip_uygulamasi.core.utilities.results.SuccessResult;
import halicmobilya.urun_takip_uygulamasi.dataAccess.abstracts.ColorDao;
import halicmobilya.urun_takip_uygulamasi.entities.concretes.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColorManager implements ColorService {
    private ColorDao colorDao;

    @Autowired
    public ColorManager(ColorDao colorDao){
        super();
        this.colorDao = colorDao;
    }

    @Override
    public Result addColor(Color color) {
        this.colorDao.save(color);
        return new SuccessResult("Renk başarıyla eklendi.");
    }

    @Override
    public DataResult<List<Color>> getAllColor() {
        return new SuccessDataResult<List<Color>>(this.colorDao.findAll(), "Tüm renkler listelendi.");
    }

}
