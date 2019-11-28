package home.test.Controllers;

import home.test.Entity.InventoryItem;
import home.test.InventoryRepository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryItemRestController {

    @Autowired
    InventoryRepository repo;

    @DeleteMapping("/{id}")
    public ResponseEntity<InventoryItem> deleteItem(@PathVariable("id") Integer id){
        try{
            InventoryItem i1 = repo.findById(id).get();
            repo.delete(i1);
            return ResponseEntity.ok(i1);
        }catch (Exception e){
            return ResponseEntity.status(404).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryItem> updateItem(
            @PathVariable("id") Integer id,
            @RequestBody InventoryItem item){
        item.setId(id);
        repo.save(item);
        return ResponseEntity.ok(item);
    }

    @PostMapping
    public ResponseEntity<?> addNewItem(@RequestBody InventoryItem item){
        repo.save(item);
        return ResponseEntity.ok(item);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getItemById(@PathVariable Integer id){
        try{
            InventoryItem i1 = repo.findById(id).get();
            return ResponseEntity.ok(i1);
        }catch(Exception e){
            return ResponseEntity.status(404).body("No product was found with matching ID");
        }
    }

    @GetMapping
    public Iterable<InventoryItem> getAllItems(
            @RequestParam(name="_page", defaultValue = "1") Integer pageNum,
            @RequestParam(name="_limit", defaultValue = "50") Integer pageSize){
        Pageable p = PageRequest.of(pageNum-1, pageSize);
        return repo.findAll(p).getContent();
    }
}
