package exam.service.interfaces;

import exam.domain.models.service.DocumentServiceModel;

import java.util.List;
import java.util.Optional;

public interface DocumentService {

    String documentSchedule(DocumentServiceModel documentServiceModel);

    Optional findDocumentById(String id);

    List<DocumentServiceModel> findAllDocuments();

    boolean documentPrint(String id);
}
