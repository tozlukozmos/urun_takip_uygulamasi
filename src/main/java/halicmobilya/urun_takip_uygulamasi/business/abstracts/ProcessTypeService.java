package halicmobilya.urun_takip_uygulamasi.business.abstracts;

import halicmobilya.urun_takip_uygulamasi.core.utilities.results.DataResult;
import halicmobilya.urun_takip_uygulamasi.core.utilities.results.Result;
import halicmobilya.urun_takip_uygulamasi.entities.concretes.ProcessType;

import java.util.List;

public interface ProcessTypeService {
    Result addProcessType(ProcessType processType);
    DataResult<List<ProcessType>> getAllProcessType();
}
