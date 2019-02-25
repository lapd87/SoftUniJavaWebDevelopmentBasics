package metubeV2.service.interfacces;

import metubeV2.domain.models.service.TubeServiceModel;

import java.util.Optional;

public interface TubeService {

    boolean uploadTube(TubeServiceModel tubeServiceModel);

    boolean updateTube(TubeServiceModel tubeServiceModel);

    Optional<TubeServiceModel> findTubeById(String id);
}
