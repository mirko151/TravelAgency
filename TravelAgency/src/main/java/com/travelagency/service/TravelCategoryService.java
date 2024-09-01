package com.travelagency.service;

import com.travelagency.model.TravelCategory;
import com.travelagency.repository.TravelCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TravelCategoryService {

    @Autowired
    private TravelCategoryRepository travelCategoryRepository;

    public List<TravelCategory> getAllCategories() {
        return travelCategoryRepository.findAll();
    }

    public TravelCategory saveCategory(TravelCategory category) {
        return travelCategoryRepository.save(category);
    }

    public TravelCategory getCategoryById(int id) {
        return travelCategoryRepository.findById(id).orElse(null);
    }

    public void deleteCategory(int id) {
        travelCategoryRepository.deleteById(id);
    }
}
