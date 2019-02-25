package panda.web.beans;

import org.modelmapper.ModelMapper;
import panda.service.interfaces.ReceiptService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

import static panda.constants.Constants.INDEX_URL;

@Named
@RequestScoped
public class ReceiptCreateBean {

    private ReceiptService receiptService;
    private ModelMapper modelMapper;

    public ReceiptCreateBean() {
    }

    @Inject
    public ReceiptCreateBean(ReceiptService receiptService,
                             ModelMapper modelMapper) {
        this.receiptService = receiptService;
        this.modelMapper = modelMapper;
    }


    public void createReceipt(String packageId,
                              String username)
            throws IOException {

        boolean isCreated = this.receiptService
                .receiptCreate(packageId, username);

        if (isCreated) {
            FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .redirect(INDEX_URL);
        }
    }
}
