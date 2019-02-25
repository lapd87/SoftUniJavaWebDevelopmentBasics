package metubeV2.repository.interfaces;

import metubeV2.domain.entities.Tube;

public interface TubeRepository extends GenericRepository<Tube, String> {

    Tube update(Tube tube);
}
