package halicmobilya.urun_takip_uygulamasi.business.concretes;

import halicmobilya.urun_takip_uygulamasi.business.abstracts.TypeService;
import halicmobilya.urun_takip_uygulamasi.core.utilities.results.DataResult;
import halicmobilya.urun_takip_uygulamasi.core.utilities.results.Result;
import halicmobilya.urun_takip_uygulamasi.core.utilities.results.SuccessDataResult;
import halicmobilya.urun_takip_uygulamasi.core.utilities.results.SuccessResult;
import halicmobilya.urun_takip_uygulamasi.dataAccess.abstracts.TypeDao;
import halicmobilya.urun_takip_uygulamasi.entities.concretes.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeManager implements TypeService {
    private TypeDao typeDao;

    @Autowired
    public TypeManager(TypeDao typeDao){
        super();
        this.typeDao = typeDao;
    }

    @Override
    public Result addType(Type type) {
        this.typeDao.save(type);
        return new SuccessResult("Cins başarıyla eklendi.");
    }

    @Override
    public DataResult<List<Type>> getAllType() {
        return new SuccessDataResult<List<Type>>(this.typeDao.findAll(), "Tüm cinsler listelendi.");
    }
}
