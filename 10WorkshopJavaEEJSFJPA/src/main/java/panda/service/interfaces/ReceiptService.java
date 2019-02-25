package panda.service.interfaces;

import panda.domain.models.service.ReceiptServiceModel;

import java.util.List;
import java.util.Optional;

public interface ReceiptService {

    boolean receiptCreate(String packageId, String username);

    List<ReceiptServiceModel> findAllReceiptsByUsername(String username);

    Optional<ReceiptServiceModel> findReceiptById(String id);
}
