package recipes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import recipes.businesslayer.*;
import recipes.model.RecipeModel;

import javax.validation.*;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class RecipeController {

    List<Recipe> recipeList = new ArrayList<>();

    @Autowired
    RecipeService recipeService;

    // GET /api/recipe
//    @GetMapping("/api/recipe/{id}")
//    public Recipe retrieveRecipe(@PathVariable long id) {
//        Optional<Recipe> recipe = recipeService.findRecipeById(id);
//        if(recipe.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
//        } else {
//            return recipe.get();
//        }
//    }

    @GetMapping("/api/recipe/{id}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable Long id) {
        Optional<Recipe> recipe = recipeService.findRecipeById(id);

        return recipe.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));

//        return recipe.map(value -> new ResponseEntity<>(
//                new RecipeModel(
//                        value.getName(),
//                        value.getCategory(),
//                        value.getDate(),
//                        value.getDescription(), value.getIngredients(),
//                        value.getDirections()), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @GetMapping("/api/recipe/search")
    public ResponseEntity<List<RecipeModel>> searchRecipe(@RequestParam Optional<String> category, @RequestParam Optional<String> name) {
        if((category.isPresent() && name.isPresent()) ||
                (category.isEmpty() && name.isEmpty())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Recipe> recipeList = recipeService.searchRecipe(category.orElse(""), name.orElse(""));
        List<RecipeModel> recipeModelList = recipeList.stream().map(value ->  new RecipeModel(
                value.getName(),
                value.getCategory(),
                value.getDate(),
                value.getDescription(), value.getIngredients(),
                value.getDirections())).collect(Collectors.toList());
        return new ResponseEntity<>(recipeModelList, HttpStatus.OK);
    }

    @PutMapping("/api/recipe/{id}")
    public ResponseEntity<Object> updateRecipe(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id, @Valid @RequestBody Recipe recipe) {
//        if (userDetails.getUsername().equals(recipe.getAuthor())) {
//            Optional<Recipe> recipeAfter = this.recipeService.update(id, recipe);
//            if(recipeAfter.isPresent()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            } else {
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            }
//        } else {
//            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//        }


        Optional<Recipe> optionalRecipe = this.recipeService.findRecipeById(id);
        if(optionalRecipe.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            Recipe databaseRecipe = optionalRecipe.get();
            if (userDetails.getUsername().equals(databaseRecipe.getAuthor())) {
                Optional<Recipe> recipeAfter = this.recipeService.update(id, recipe);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }

    }

    // POST /api/recipe
    @PostMapping("/api/recipe/new")
    public ResponseEntity<Map<String, Long>> addRecipe(@AuthenticationPrincipal UserDetailsImpl userDetails, @Valid @RequestBody Recipe recipe) {
        recipe.setAuthor(userDetails.getUsername());
        Recipe recipeCreate = recipeService.save(recipe);
        Map<String, Long> idMap = new HashMap<>();
        idMap.put("id", recipeCreate.getId());
        return ResponseEntity.ok(idMap);
    }

    // DELETE /api/recipe/{id}
    @DeleteMapping("/api/recipe/{id}")
    public ResponseEntity<Object> delete(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable long id) {
        Optional<Recipe> optionalRecipe = this.recipeService.findRecipeById(id);
        if(optionalRecipe.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            Recipe recipe = optionalRecipe.get();
            if (userDetails.getUsername().equals(recipe.getAuthor())) {
                this.recipeService.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }
    }

    @Autowired
    UserService userService;
    @Autowired
    PasswordEncoder encoder;

    // POST /api/register
    @PostMapping("/api/register")
    public ResponseEntity<Object> register(@Valid @RequestBody User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        if (userService.findUserByEmail(user.getEmail()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            userService.save(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
