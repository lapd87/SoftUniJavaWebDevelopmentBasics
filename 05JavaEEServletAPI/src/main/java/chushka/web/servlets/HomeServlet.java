package chushka.web.servlets;

import chushka.domain.models.view.ProductAllViewModel;
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
import java.util.List;
import java.util.stream.Collectors;

import static chushka.constants.Constants.*;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 29.1.2019 г.
 * Time: 11:39 ч.
 */

@WebServlet(HOME_URL)
public class HomeServlet extends HttpServlet {

    private final InputReader inputReader;

    private final ProductService productService;
    private final ModelMapper modelMapper;

    @Inject
    public HomeServlet(ProductService productService,
                       ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
        this.inputReader = new HtmlViewReader(HOME_FILE_NAME);
    }

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws IOException {

        String html = formatAllProductsList(this.inputReader
                .readLine());

        resp.getWriter().println(html);
    }

    private String formatAllProductsList(String html) {
        List<ProductAllViewModel> allProducts = this.productService
                .findAllProducts()
                .stream()
                .map(productServiceModel -> this.modelMapper
                        .map(productServiceModel, ProductAllViewModel.class))
                .collect(Collectors.toList());

        StringBuilder productsList = new StringBuilder();

        allProducts.forEach(productAllViewModel -> productsList
                .append(PRODUCTS_LIST
                        .replace(REPLACER_NAME,
                                productAllViewModel.getName()))
                .append(NEW_LINE));

        return html.replace(REPLACER_ALL, productsList);
    }
}