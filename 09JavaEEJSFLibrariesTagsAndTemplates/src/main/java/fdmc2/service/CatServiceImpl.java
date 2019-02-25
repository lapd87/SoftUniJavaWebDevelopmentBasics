package fdmc2.service;

import fdmc2.domain.entities.Cat;
import fdmc2.domain.models.service.CatServiceModel;
import fdmc2.repository.interfaces.CatRepository;
import fdmc2.service.interfacces.CatService;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.persistence.RollbackException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static fdmc2.constants.Constants.WARNINGS_UNCHECKED;

public class CatServiceImpl implements CatService {

    private final CatRepository catRepository;
    private final ModelMapper modelMapper;

    @Inject
    public CatServiceImpl(CatRepository catRepository, ModelMapper modelMapper) {
        this.catRepository = catRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean catCreate(CatServiceModel catServiceModel) {
        Cat cat = this.modelMapper
                .map(catServiceModel, Cat.class);

        if (cat.getName().length() < 2
                || cat.getName().length() > 10
                || cat.getBreed().length() < 5
                || cat.getBreed().length() > 20
                || cat.getColor().isEmpty()
                || cat.getAge() == null
                || cat.getAge() < 1
                || cat.getAge() > 31
                || cat.getPrice() == null
                || cat.getPrice().compareTo(new BigDecimal(0.01)) < 0
                || cat.getAddedOn() == null) {
            return false;
        }

        try {
            this.catRepository.save(cat);
            return true;
        } catch (RollbackException rbe) {
            return false;
        }
    }

    @Override
    public Optional<CatServiceModel> findCatById(String id) {

        Optional catById = this.catRepository.findById(id);

        if (catById.isEmpty()) {
            return Optional.empty();
        }

        return Optional.ofNullable(this.modelMapper
                .map(catById.get(), CatServiceModel.class));
    }

    @SuppressWarnings(WARNINGS_UNCHECKED)
    @Override
    public List<CatServiceModel> findAllCats() {

        List<Cat> allCats = (List<Cat>) this.catRepository
                .findAll().orElseGet(ArrayList::new);

        return allCats.stream()
                .map(c -> this.modelMapper
                        .map(c, CatServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean removeCatById(String id) {
        Optional<CatServiceModel> catById = this.findCatById(id);

        if (catById.isEmpty()) {
            return false;
        }

        Cat cat = this.modelMapper
                .map(catById.get(), Cat.class);

        try {
            this.catRepository.removeById(cat.getId());
            return true;
        } catch (RollbackException rbe) {
            return false;
        }
    }
}
