package panda.repository.interfaces;

import panda.domain.entities.Receipt;
import panda.domain.entities.User;

import java.util.Optional;

public interface ReceiptRepository extends GenericRepository<Receipt, String> {

    Optional findAllReceiptsByUser(User user);
}
