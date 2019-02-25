package fdmc2.service.interfacces;

import fdmc2.domain.models.service.CatServiceModel;

import java.util.List;
import java.util.Optional;

public interface CatService {

    boolean catCreate(CatServiceModel catServiceModel);

    Optional<CatServiceModel> findCatById(String id);

    List<CatServiceModel> findAllCats();

    boolean removeCatById(String id);
}
