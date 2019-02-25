package fdmc2.web.beans;

import fdmc2.domain.models.view.CatListViewModel;
import fdmc2.service.interfacces.CatService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 13.2.2019 г.
 * Time: 12:13 ч.
 */
@Named
@RequestScoped
public class CatListBean {

    private List<CatListViewModel> catListViewModels;

    private CatService catService;
    private ModelMapper modelMapper;

    public CatListBean() {
        this.catListViewModels = new ArrayList<>();
    }

    @Inject
    public CatListBean(CatService catService, ModelMapper modelMapper) {
        this();
        this.catService = catService;
        this.modelMapper = modelMapper;
        this.catListViewModels = this.catService
                .findAllCats()
                .stream()
                .map(c -> this.modelMapper
                        .map(c, CatListViewModel.class))
                .collect(Collectors.toList());
    }

    public List<CatListViewModel> getCatListViewModels() {
        return catListViewModels;
    }

    public void sortCatListViewModels(String parameterName) {

        this.catListViewModels.sort((a, b) -> {
            try {
                Field declaredField = CatListViewModel.class.getDeclaredField(parameterName);
                declaredField.setAccessible(true);

                Comparable aObj = (Comparable) declaredField.get(a);
                Comparable bObj = (Comparable) declaredField.get(b);

                declaredField.setAccessible(false);

                int cmp;
                if (SortType.isSortAscending()) {
                    cmp = aObj.compareTo(bObj);
                } else {
                    cmp = bObj.compareTo(aObj);
                }

                return cmp;

            } catch (NoSuchFieldException | IllegalAccessException ignored) {
                return 0;
            }
        });

        SortType.reverseSort();
    }
}

class SortType {
    private static boolean sortAscending = true;

    public SortType() {
    }

    static void reverseSort() {
        sortAscending = !sortAscending;
    }

    static boolean isSortAscending() {
        return sortAscending;
    }
}
