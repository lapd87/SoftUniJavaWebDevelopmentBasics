package chushka.web.servlets;

import chushka.domain.entities.ProductType;
import chushka.domain.models.service.ProductServiceModel;
import chushka.io.HtmlViewReader;
import chushka.io.interfaces.InputReader;
import chushka.service.interfaces.ProductService;
import chushka.util.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

import static chushka.constants.Constants.*;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 29.1.2019 г.
 * Time: 11:39 ч.
 */

@WebServlet(PRODUCT_CREATE_URL)
public class ProductsCreateServlet extends HttpServlet {

    private final InputReader inputReader;

    private final ProductService productService;
    private final ModelMapper modelMapper;

    @Inject
    public ProductsCreateServlet(ProductService productService,
                                 ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
        this.inputReader = new HtmlViewReader(PRODUCT_CREATE_FILE_NAME);
    }

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws IOException {

        String html = formatTypeOptions(this.inputReader
                .readLine());

        resp.getWriter().println(html);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductServiceModel productServiceModel = new ProductServiceModel();

        productServiceModel.setName(req.getParameter(PARAMETER_NAME));
        productServiceModel.setDescription(req.getParameter(PARAMETER_DESCR));
        productServiceModel.setType(req.getParameter(PARAMETER_TYPE));

        this.productService.saveProduct(productServiceModel);

        resp.sendRedirect(PRODUCT_ALL_URL);
    }

    private String formatTypeOptions(String html) {
        StringBuilder typeOptions = new StringBuilder();

        Arrays.stream(ProductType.values())
                .forEach(type -> typeOptions
                        .append(String
                                .format(PRODUCT_TYPE_OPTION,
                                        type.name(),
                                        type.name())));

        return html.replace(REPLACER_TYPES, typeOptions);
    }
}