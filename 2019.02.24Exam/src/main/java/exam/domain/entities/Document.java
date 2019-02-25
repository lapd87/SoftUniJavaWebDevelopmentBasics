package exam.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import static exam.constants.Constants.*;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 24.2.2019 г.
 * Time: 09:55 ч.
 */
@Entity
@Table(name = DOC_ENTITIES)
public class Document extends BaseEntity {


    private String title;
    private String content;

    public Document() {
    }

    @Column(name = PARAMETER_TITLE, nullable = false)
    @NotNull
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = PARAMETER_CONTENT, nullable = false, columnDefinition = CONTENT_TYPE)
    @NotNull
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}