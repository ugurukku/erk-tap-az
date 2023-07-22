package az.millisoft.tapaz.service.impl;

import az.millisoft.tapaz.entity.Category;
import az.millisoft.tapaz.repository.CategoryRepository;
import az.millisoft.tapaz.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    @Override
    public List<Category> getAll() {
        return repository.findAll();
    }
}
