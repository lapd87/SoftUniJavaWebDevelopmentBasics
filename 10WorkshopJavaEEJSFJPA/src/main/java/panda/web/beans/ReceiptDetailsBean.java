package panda.web.beans;

import org.modelmapper.ModelMapper;
import panda.domain.models.service.ReceiptServiceModel;
import panda.domain.models.view.ReceiptDetailsViewModel;
import panda.service.interfaces.ReceiptService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.Optional;

import static panda.constants.Constants.DATE_TIME_FORMATTER;
import static panda.constants.Constants.INDEX_URL;

@Named
@RequestScoped
public class ReceiptDetailsBean {

    private ReceiptDetailsViewModel receiptDetailsViewModel;

    private ReceiptService receiptService;
    private ModelMapper modelMapper;

    public ReceiptDetailsBean() {
    }

    @Inject
    public ReceiptDetailsBean(ReceiptService ReceiptService,
                              ModelMapper modelMapper) {
        this.receiptService = ReceiptService;
        this.modelMapper = modelMapper;
    }

    public ReceiptDetailsViewModel getReceiptDetailsViewModel() {
        return receiptDetailsViewModel;
    }

    public void setReceiptDetailsViewModel(ReceiptDetailsViewModel receiptDetailsViewModel) {
        this.receiptDetailsViewModel = receiptDetailsViewModel;
    }

    public void getReceipt(String id) throws IOException {
        Optional<ReceiptServiceModel> receiptById = this.receiptService
                .findReceiptById(id);

        if (receiptById.isEmpty()) {
            FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .redirect(INDEX_URL);
        } else {
            ReceiptServiceModel receiptServiceModel = receiptById.get();

            ReceiptDetailsViewModel model = this.modelMapper
                    .map(receiptServiceModel, ReceiptDetailsViewModel.class);

            model.setIssuedOn(receiptServiceModel
                    .getIssuedOn()
                    .format(DATE_TIME_FORMATTER));
            model.getaPackage()
                    .setRecipient(receiptServiceModel.getaPackage()
                            .getRecipient()
                            .getUsername());

            this.receiptDetailsViewModel = model;
        }
    }
}
