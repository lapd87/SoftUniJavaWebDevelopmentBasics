package metubeV2.service;

import metubeV2.domain.entities.Tube;
import metubeV2.domain.models.service.TubeServiceModel;
import metubeV2.domain.models.service.UserServiceModel;
import metubeV2.repository.interfaces.TubeRepository;
import metubeV2.service.interfacces.TubeService;
import metubeV2.service.interfacces.UserService;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.Optional;

public class TubeServiceImpl implements TubeService {

    private final TubeRepository tubeRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Inject
    public TubeServiceImpl(TubeRepository tubeRepository, UserService userService, ModelMapper modelMapper) {
        this.tubeRepository = tubeRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean uploadTube(TubeServiceModel tubeServiceModel) {

        try {
            this.tubeRepository.save(this.getTube(tubeServiceModel));
            return true;
        } catch (NullPointerException ignored) {
            return false;
        }
    }

    @Override
    public boolean updateTube(TubeServiceModel tubeServiceModel) {

        try {
            this.tubeRepository.update(this.getTube(tubeServiceModel));
            return true;
        } catch (NullPointerException ignored) {
            return false;
        }
    }

    @Override
    public Optional<TubeServiceModel> findTubeById(String id) {
        Optional tube = this.tubeRepository.findById(id);

        if (tube.isEmpty()) {
            return Optional.empty();
        }

        return Optional.ofNullable(this.modelMapper
                .map(tube.get(), TubeServiceModel.class));
    }

    private Tube getTube(TubeServiceModel tubeServiceModel) {

        Optional<UserServiceModel> userServiceModel = this.userService
                .findUserByUsername(tubeServiceModel.getUploader().getUsername());

        if (userServiceModel.isEmpty()) {
            throw new NullPointerException();
        }

        tubeServiceModel
                .setUploader(userServiceModel.get());

        return this.modelMapper
                .map(tubeServiceModel, Tube.class);
    }
}
