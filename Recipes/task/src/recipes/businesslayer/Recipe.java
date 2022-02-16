package recipes.businesslayer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "recipe")
public class Recipe {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @NotBlank
    @NonNull
    private String name;

    @Column
    @JsonIgnore
//    @ManyToOne
//    @JoinColumn(name = "email")
    private String author;

    @Column
    @NotBlank
    @NonNull
    private String category;

    @Column
    private LocalDateTime date;

    @Column
    @NotBlank
    @NonNull
    private String description;

    @Column(name="ingredients")
    @ElementCollection
    @NotEmpty
    @Size(min = 1)
    private List<String> ingredients;

    @Column(name="directions")
    @ElementCollection
    @NotEmpty
    @Size(min = 1)
    private List<String> directions;
}


