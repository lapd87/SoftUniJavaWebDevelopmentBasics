package fdmc2.web.beans;

import fdmc2.domain.entities.enums.Gender;
import fdmc2.domain.models.binding.CatCreateBindingModel;
import fdmc2.domain.models.service.CatServiceModel;
import fdmc2.service.interfacces.CatService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.Date;

import static fdmc2.constants.Constants.ALL_CAT_URL;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 13.2.2019 г.
 * Time: 11:43 ч.
 */
@Named
@RequestScoped
public class CatCreateBean {

    private CatCreateBindingModel catCreateBindingModel;

    private CatService catService;
    private ModelMapper modelMapper;
    private Gender[] genders;
    private Date today;

    public CatCreateBean() {
        this.catCreateBindingModel = new CatCreateBindingModel();
    }

    @Inject
    public CatCreateBean(CatService catService, ModelMapper modelMapper) {
        this();
        this.catService = catService;
        this.modelMapper = modelMapper;
    }

    public CatCreateBindingModel getCatCreateBindingModel() {
        return catCreateBindingModel;
    }

    public void setCatCreateBindingModel(CatCreateBindingModel catCreateBindingModel) {
        this.catCreateBindingModel = catCreateBindingModel;
    }

    public Gender[] getGenders() {
        return Gender.values();
    }

    public Date getToday() {
        return new Date();
    }

    public void create() throws IOException {
        CatServiceModel catServiceModel = this.modelMapper
                .map(this.catCreateBindingModel, CatServiceModel.class);

        boolean isRegistered = this.catService
                .catCreate(catServiceModel);

        if (isRegistered) {
            ExternalContext externalContext = FacesContext.getCurrentInstance()
                    .getExternalContext();

            externalContext.redirect(ALL_CAT_URL);
        }
    }
}