package employees.config;

import org.modelmapper.ModelMapper;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import static employees.constants.Constants.PERSISTENCE_NAME;

public class BeanConfiguration {

    @Produces
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Produces
    public EntityManager entityManager() {
        return Persistence
                .createEntityManagerFactory(PERSISTENCE_NAME)
                .createEntityManager();
    }
}
