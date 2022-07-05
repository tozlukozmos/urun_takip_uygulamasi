package halicmobilya.urun_takip_uygulamasi.business.concretes;

import halicmobilya.urun_takip_uygulamasi.business.abstracts.UnitService;
import halicmobilya.urun_takip_uygulamasi.core.utilities.results.DataResult;
import halicmobilya.urun_takip_uygulamasi.core.utilities.results.Result;
import halicmobilya.urun_takip_uygulamasi.core.utilities.results.SuccessDataResult;
import halicmobilya.urun_takip_uygulamasi.core.utilities.results.SuccessResult;
import halicmobilya.urun_takip_uygulamasi.dataAccess.abstracts.UnitDao;
import halicmobilya.urun_takip_uygulamasi.entities.concretes.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitManager implements UnitService {
    private UnitDao unitDao;

    @Autowired
    public UnitManager(UnitDao unitDao) {
        this.unitDao = unitDao;
    }

    @Override
    public Result addUnit(Unit unit) {
        this.unitDao.save(unit);
        return new SuccessResult("Miktar birimi başarıyla eklendi.");
    }

    @Override
    public DataResult<List<Unit>> getAllUnit() {
        return new SuccessDataResult<List<Unit>>(this.unitDao.findAll(), "Tüm miktar birimleri listelendi.");
    }
}
