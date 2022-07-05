package halicmobilya.urun_takip_uygulamasi.business.abstracts;

import halicmobilya.urun_takip_uygulamasi.core.utilities.results.DataResult;
import halicmobilya.urun_takip_uygulamasi.core.utilities.results.Result;
import halicmobilya.urun_takip_uygulamasi.entities.concretes.Unit;

import java.util.List;

public interface UnitService {
    Result addUnit(Unit unit);
    DataResult<List<Unit>> getAllUnit();
}
