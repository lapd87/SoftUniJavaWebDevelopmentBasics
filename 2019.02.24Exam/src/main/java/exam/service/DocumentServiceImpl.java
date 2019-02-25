package exam.service;

import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;
import exam.domain.entities.Document;
import exam.domain.models.service.DocumentServiceModel;
import exam.repository.interfaces.DocumentRepository;
import exam.service.interfaces.DocumentService;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.persistence.RollbackException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static exam.constants.Constants.*;

public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final ModelMapper modelMapper;

    @Inject
    public DocumentServiceImpl(DocumentRepository documentRepository, ModelMapper modelMapper) {
        this.documentRepository = documentRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public String documentSchedule(DocumentServiceModel documentServiceModel) {
        Document document = this.modelMapper.map(documentServiceModel, Document.class);

        if (document.getTitle().isEmpty()
                || document.getContent().isEmpty()) {
            return EMPTY_STRING;
        }

        try {
            Document saved = this.documentRepository.save(document);
            return saved.getId();

        } catch (RollbackException rbe) {
            return EMPTY_STRING;
        }
    }

    @Override
    public Optional findDocumentById(String id) {
        Optional optionalDocument = this.documentRepository
                .findById(id);

        if (optionalDocument.isEmpty()) {
            return Optional.empty();
        }

        DocumentServiceModel documentServiceModel = this.modelMapper
                .map(optionalDocument.get(), DocumentServiceModel.class);

        return Optional.ofNullable(documentServiceModel);
    }

    @SuppressWarnings(WARNINGS_UNCHECKED)
    @Override
    public List<DocumentServiceModel> findAllDocuments() {
        Optional optionalDocuments = this.documentRepository.findAll();

        if (optionalDocuments.isEmpty()) {
            return new ArrayList<>();
        }

        List<Document> allUsers = (List<Document>) optionalDocuments.get();

        return allUsers.stream()
                .map(d -> this.modelMapper.map(d, DocumentServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean documentPrint(String id) {
        Optional documentById = this.documentRepository.findById(id);

        if (documentById.isEmpty()) {
            return false;
        }

        Document document = this.modelMapper
                .map(documentById.get(), Document.class);

        boolean isCreated = this.createPdf(document);

//        if (!isCreated) {
//            return false;
//        }

        try {
            this.documentRepository.deleteById(document.getId());
            return true;
        } catch (RollbackException rbe) {
            return false;
        }
    }

    private void parseMarkdownToHTML(Document document) {

        Parser parser = Parser.builder().build();

        Node content = parser.parse("# " + document.getTitle()
                + System.lineSeparator() + document.getContent());
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        document.setContent(renderer.render(content));
    }

    private boolean createPdf(Document document) {

        this.parseMarkdownToHTML(document);

        try {
            File dir = new File("output");
            dir.mkdirs();
            File pdf = new File(dir, "output.pdf");
            pdf.createNewFile();

            OutputStream file = new FileOutputStream(pdf);
            com.itextpdf.text.Document doc = new com.itextpdf.text.Document();
            PdfWriter.getInstance(doc, file);
            doc.open();
            HTMLWorker htmlWorker = new HTMLWorker(doc);
            //TODO throws exception
            htmlWorker.parse(new StringReader(document.getContent()));
            doc.close();
            file.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
