package panda.web.beans;

import org.modelmapper.ModelMapper;
import panda.domain.models.view.ReceiptViewModel;
import panda.service.interfaces.ReceiptService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

import static panda.constants.Constants.DATE_TIME_FORMATTER;
import static panda.constants.Constants.PARAMETER_USERNAME;

@Named
@RequestScoped
public class UserReceiptsBean {

    private List<ReceiptViewModel> receiptViewModels;

    private ReceiptService receiptService;
    private ModelMapper modelMapper;

    public UserReceiptsBean() {
    }

    @Inject
    public UserReceiptsBean(ReceiptService receiptService, ModelMapper modelMapper) {
        this.receiptService = receiptService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void initReceipts() {

        ExternalContext externalContext = FacesContext
                .getCurrentInstance()
                .getExternalContext();

        HttpSession session = (HttpSession) externalContext
                .getSession(false); //TODO check

        String username = (String) session.getAttribute(PARAMETER_USERNAME);

        this.receiptViewModels = this.receiptService
                .findAllReceiptsByUsername(username)
                .stream()
                .map(r -> {
                    ReceiptViewModel receiptViewModel = this.modelMapper.map(r, ReceiptViewModel.class);
                    receiptViewModel.setRecipient(r.getRecipient().getUsername());
                    receiptViewModel.setIssuedOn(r.getIssuedOn().format(DATE_TIME_FORMATTER));
                    return receiptViewModel;
                })
                .collect(Collectors.toList());
    }

    public List<ReceiptViewModel> getReceiptViewModels() {
        return receiptViewModels;
    }

    public void setReceiptViewModels(List<ReceiptViewModel> receiptViewModels) {
        this.receiptViewModels = receiptViewModels;
    }
}
