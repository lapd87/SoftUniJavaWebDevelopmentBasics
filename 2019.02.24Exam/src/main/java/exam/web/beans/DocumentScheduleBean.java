package exam.web.beans;

import exam.domain.models.binding.DocumentBindingModel;
import exam.domain.models.service.DocumentServiceModel;
import exam.service.interfaces.DocumentService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static exam.constants.Constants.*;

@Named
@RequestScoped
public class DocumentScheduleBean {

    private DocumentBindingModel documentBindingModel;

    private DocumentService documentService;
    private ModelMapper modelMapper;

    public DocumentScheduleBean() {
    }

    @Inject
    public DocumentScheduleBean(DocumentService documentService, ModelMapper modelMapper) {
        this.documentService = documentService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void init() {
        this.documentBindingModel = new DocumentBindingModel();
    }

    public DocumentBindingModel getDocumentBindingModel() {
        return documentBindingModel;
    }

    public void setDocumentBindingModel(DocumentBindingModel documentBindingModel) {
        this.documentBindingModel = documentBindingModel;
    }

    public void scheduleDocument() throws IOException {

        DocumentServiceModel documentServiceModel = this.modelMapper
                .map(this.documentBindingModel, DocumentServiceModel.class);

        String scheduledId = this.documentService.documentSchedule(documentServiceModel);

        if (!scheduledId.isEmpty()) {
            ExternalContext externalContext = FacesContext.getCurrentInstance()
                    .getExternalContext();

            HttpSession session = (HttpSession) externalContext.getSession(false);

            session.setAttribute(PARAMETER_ID, scheduledId);

            FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .redirect(DETAILS_URL);
        }
    }
}