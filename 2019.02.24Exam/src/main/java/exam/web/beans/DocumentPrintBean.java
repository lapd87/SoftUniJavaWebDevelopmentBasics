package exam.web.beans;

import exam.domain.models.service.DocumentServiceModel;
import exam.domain.models.view.DocumentViewModel;
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
import java.util.Optional;

import static exam.constants.Constants.*;

@Named
@RequestScoped
public class DocumentPrintBean {

    private DocumentViewModel documentViewModel;

    private DocumentService documentService;
    private ModelMapper modelMapper;

    public DocumentPrintBean() {
    }

    @Inject
    public DocumentPrintBean(DocumentService documentService, ModelMapper modelMapper) {
        this.documentService = documentService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void init() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance()
                .getExternalContext();

        HttpSession session = (HttpSession) externalContext.getSession(false);

        String id;
        try {
            id = (String) session.getAttribute(PARAMETER_ID);
        } catch (NullPointerException npe) {
            externalContext
                    .redirect(INDEX_URL);
            return;
        }

        Optional documentById = this.documentService.findDocumentById(id);

        if (documentById.isEmpty()) {
            externalContext
                    .redirect(INDEX_URL);
        } else {
            DocumentServiceModel documentServiceModel = (DocumentServiceModel) documentById.get();

            this.documentViewModel = this.modelMapper
                    .map(documentServiceModel, DocumentViewModel.class);
        }
    }

    public DocumentViewModel getDocumentViewModel() {
        return documentViewModel;
    }

    public void setDocumentViewModel(DocumentViewModel documentViewModel) {
        this.documentViewModel = documentViewModel;
    }

    public void printDocument(String id) throws IOException {

        boolean isPrinted = this.documentService.documentPrint(id);

        if (isPrinted) {
            FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .redirect(HOME_URL);
        }
    }
}