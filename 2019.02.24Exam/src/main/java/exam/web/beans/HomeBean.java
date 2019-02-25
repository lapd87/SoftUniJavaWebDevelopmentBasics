package exam.web.beans;

import exam.domain.models.service.DocumentServiceModel;
import exam.domain.models.view.DocumentListViewModel;
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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static exam.constants.Constants.*;

@Named
@RequestScoped
public class HomeBean {

    List<List<DocumentListViewModel>> documentListViewModelList;

    private DocumentService documentService;
    private ModelMapper modelMapper;

    public HomeBean() {
    }

    @Inject
    public HomeBean(DocumentService documentService,
                    ModelMapper modelMapper) {
        this.documentService = documentService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void init() {

        List<DocumentServiceModel> allDocuments = this.documentService.findAllDocuments();

        List<DocumentListViewModel> documentListViewModels = allDocuments.stream()
                .map(d -> this.modelMapper.map(d, DocumentListViewModel.class))
                .collect(Collectors.toList());

        while (documentListViewModels.size() % COLUMNS_COUNT != 0) {
            documentListViewModels.add(new DocumentListViewModel());
        }


        List<List<DocumentListViewModel>> documentListViewModelList = new ArrayList<>();

        for (int i = 0; i < documentListViewModels.size(); i += 5) {
            documentListViewModelList.add(documentListViewModels.subList(i, i + 5));
        }

        this.documentListViewModelList = documentListViewModelList;
    }

    public List<List<DocumentListViewModel>> getDocumentListViewModelList() {
        return documentListViewModelList;
    }

    public void setDocumentListViewModelList(List<List<DocumentListViewModel>> documentListViewModelList) {
        this.documentListViewModelList = documentListViewModelList;
    }

    public void getDetails(String id, String url) throws IOException {

        ExternalContext externalContext = FacesContext.getCurrentInstance()
                .getExternalContext();

        HttpSession session = (HttpSession) externalContext.getSession(false);

        session.setAttribute(PARAMETER_ID, id);

        if (url.equals(DETAILS_URL)) {
            externalContext.redirect(DETAILS_URL);
        } else if (url.equals(PRINT_URL)) {
            externalContext.redirect(PRINT_URL);
        } else {
            externalContext.redirect(INDEX_URL);
        }
    }
}
