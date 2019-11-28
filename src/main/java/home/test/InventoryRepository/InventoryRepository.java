package home.test.InventoryRepository;

import home.test.Entity.InventoryItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends PagingAndSortingRepository<InventoryItem, Integer> {

}
