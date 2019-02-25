package fdmc2.web.beans;

import fdmc2.service.interfacces.CatService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

import static fdmc2.constants.Constants.ALL_CAT_URL;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 13.2.2019 г.
 * Time: 12:49 ч.
 */
@Named
@RequestScoped
public class CatRemoveBean {

    private CatService catService;

    public CatRemoveBean() {
    }

    @Inject
    public CatRemoveBean(CatService catService) {
        this.catService = catService;
    }

    public void remove(String id) throws IOException {

        this.catService.removeCatById(id);

        ExternalContext externalContext = FacesContext.getCurrentInstance()
                .getExternalContext();

        externalContext.redirect(ALL_CAT_URL);
    }
}