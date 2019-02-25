package metubeV2.domain.models.view;

import java.util.List;

public class UserHomeViewModel {

    private String username;
    private List<TubesUserHomeViewModel> tubes;

    public UserHomeViewModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<TubesUserHomeViewModel> getTubes() {
        return tubes;
    }

    public void setTubes(List<TubesUserHomeViewModel> tubes) {
        this.tubes = tubes;
    }
}
