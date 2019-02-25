package chushka.web.servlets;

import chushka.domain.models.service.ProductServiceModel;
import chushka.domain.models.view.ProductDetailsViewModel;
import chushka.io.HtmlViewReader;
import chushka.io.interfaces.InputReader;
import chushka.service.interfaces.ProductService;
import chushka.util.ModelMapper;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static chushka.constants.Constants.*;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 29.1.2019 г.
 * Time: 11:39 ч.
 */

@WebServlet(PRODUCT_DETAILS_URL)
public class ProductsDetailsServlet extends HttpServlet {

    private final InputReader inputReader;

    private final ProductService productService;
    private final ModelMapper modelMapper;

    @Inject
    public ProductsDetailsServlet(ProductService productService,
                                  ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
        this.inputReader = new HtmlViewReader(PRODUCT_DETAILS_FILE_NAME);
    }

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws IOException {

        String html = formatDetails(this.inputReader
                .readLine(), req);

        resp.getWriter().println(html);
    }

    private String formatDetails(String html,
                                 HttpServletRequest req) {

        String productName = req.getQueryString()
                .split(QUERY_ARG_SPLITTER)[1];

        ProductServiceModel productServiceModel = this.productService
                .findProductByName(productName);

        ProductDetailsViewModel productDetailsViewModel = this.modelMapper.map(productServiceModel,
                ProductDetailsViewModel.class);

        return html.replace(REPLACER_NAME, productDetailsViewModel.getName())
                .replace(REPLACER_DESCR, productDetailsViewModel.getDescription())
                .replace(REPLACER_TYPE, productDetailsViewModel.getType());
    }
}