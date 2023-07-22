package az.millisoft.tapaz.repository;

import az.millisoft.tapaz.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
