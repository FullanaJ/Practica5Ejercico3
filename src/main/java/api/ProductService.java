package api;
import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService
{
    @Override
    public Set<Date> findAll()
    {
        Set<Date> products = new HashSet();
        products.add(new Date(100,3 , 2023));
        products.add(new Date(2,4, 2024));
        products.add(new Date(2, 5, 2024));
        products.add(new Date(3, 7, 2024));
        products.add(new Date(4, 2, 2024));
        products.add(new Date(5,10 , 2024));
        return products;
    }
}