package panda.web.beans;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static panda.constants.Constants.*;

@Named
@RequestScoped
public class UserLogoutBean {

    public UserLogoutBean() {
    }

    public void logoutUser() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance()
                .getExternalContext();

        HttpSession session = (HttpSession) externalContext.getSession(false);


        session.removeAttribute(PARAMETER_USERNAME);
        session.removeAttribute(PARAMETER_ROLE);

        externalContext.invalidateSession();

        externalContext.redirect(INDEX_URL);
    }
}
