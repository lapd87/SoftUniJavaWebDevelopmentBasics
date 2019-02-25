package metubeV2.domain.models.view;

import java.util.List;

public class UserProfileViewModel {

    private String username;
    private String email;
    private List<TubesUserProfileViewModel> tubes;

    public UserProfileViewModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<TubesUserProfileViewModel> getTubes() {
        return tubes;
    }

    public void setTubes(List<TubesUserProfileViewModel> tubes) {
        this.tubes = tubes;
    }
}
