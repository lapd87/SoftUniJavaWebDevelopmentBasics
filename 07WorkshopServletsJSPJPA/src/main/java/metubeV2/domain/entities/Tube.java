package metubeV2.domain.entities;

import javax.persistence.*;

import static metubeV2.constants.Constants.*;

@Entity
@Table(name = TUBE_ENTITIES)
public class Tube extends BaseEntity {

    private String title;
    private String author;
    private String description;
    private String youtubeId;
    private long views;
    private User uploader;

    public Tube() {
    }

    @Column(name = PARAMETER_TITLE, nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = PARAMETER_AUTHOR, nullable = false)
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Column(name = PARAMETER_DESC)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = PARAMETER_LINK_ID, nullable = false, updatable = false)
    public String getYoutubeId() {
        return youtubeId;
    }

    public void setYoutubeId(String youtubeId) {
        this.youtubeId = youtubeId;
    }

    @Column(name = PARAMETER_VIEWS)
    public long getViews() {
        return views;
    }

    public void setViews(long views) {
        this.views = views;
    }

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = PARAMETER_UPLOADER, referencedColumnName = PARAMETER_ID)
    public User getUploader() {
        return uploader;
    }

    public void setUploader(User uploader) {
        this.uploader = uploader;
    }
}
