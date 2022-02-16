package recipes.businesslayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import recipes.persistence.RecipeRepository;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Optional<Recipe> findRecipeById(long id) {
        return this.recipeRepository.findById(id);
    }

    public Recipe save (Recipe recipe) {
        recipe.setDate(LocalDateTime.now());
        return this.recipeRepository.save(recipe);
    }

    public Optional<Recipe> update (Long id, Recipe recipe) {
        Optional<Recipe> recipeBeforeOpt = this.recipeRepository.findById(id);
        if (recipeBeforeOpt.isEmpty()) return Optional.empty();
        Recipe recipeBefore = recipeBeforeOpt.get();
        recipeBefore.setName(recipe.getName());
        recipeBefore.setCategory(recipe.getCategory());
        recipeBefore.setDirections(recipe.getDirections());
        recipeBefore.setDescription(recipe.getDescription());
        recipeBefore.setIngredients(recipe.getIngredients());
        // recipeBefore.setDate(LocalDateTime.now());
        return Optional.of(this.save(recipeBefore));
    }

    public void deleteById(long id) {
        this.recipeRepository.deleteById(id);
    }

    public List<Recipe> searchRecipe(String category, String name) {
        if(!category.isEmpty()) {
            return this.recipeRepository.findAllByCategoryIgnoreCaseOrderByDateDesc(category);
        } else if(!name.isEmpty()) {
            return this.recipeRepository.findAllByNameContainingIgnoreCaseOrderByDateDesc(name);
        } else return Collections.emptyList();
    }
}
