package exam.domain.models.view;

import static exam.constants.Constants.DOTS;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 24.2.2019 г.
 * Time: 11:58 ч.
 */
public class DocumentListViewModel {

    private String id;
    private String title;

    public DocumentListViewModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {

        if (title.length() > 15) {
            title = title.substring(0, 12) + DOTS;
        }

        this.title = title;
    }
}