package halicmobilya.urun_takip_uygulamasi.business.abstracts;

import halicmobilya.urun_takip_uygulamasi.core.utilities.results.DataResult;
import halicmobilya.urun_takip_uygulamasi.core.utilities.results.Result;
import halicmobilya.urun_takip_uygulamasi.entities.concretes.Model;

import java.util.List;

public interface ModelService {
    Result addModel(Model model);
    DataResult<List<Model>> getAllModel();
}
