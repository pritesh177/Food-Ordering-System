package com.FOS.Foody.Controller;

import com.FOS.Foody.Service.CategoryService;
import com.FOS.Foody.Service.UserService;
import com.FOS.Foody.model.Category;
import com.FOS.Foody.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    public CategoryService categoryService;
    @Autowired
    public UserService userService;

    @PostMapping("/admin/category")
    public ResponseEntity<Category>createCategory(@RequestBody Category category,
                                                  @RequestHeader("Authorization") String jwt) throws Exception {
        User user=userService.findByJwtToken(jwt);
        Category createcategory=categoryService.createCategory(category.getName(), user.getId());

        return new ResponseEntity<>(createcategory, HttpStatus.CREATED);
    }

    @GetMapping("/category/restaurant")
    public ResponseEntity<List<Category>>getRestautrantCategory(
            @RequestHeader("Authorization") String jwt) throws Exception {
        User user=userService.findByJwtToken(jwt);
        List<Category> listcategory=categoryService.findCategoryByRestaurantId(user.getId());

        return new ResponseEntity<>(listcategory, HttpStatus.OK);
    }
}
