package exam.domain.models.binding;

import exam.domain.entities.BaseEntity;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 24.2.2019 г.
 * Time: 09:55 ч.
 */
public class DocumentBindingModel extends BaseEntity {

    private String title;
    private String content;

    public DocumentBindingModel() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}