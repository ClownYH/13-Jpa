package com.ohgiraffers.springjpa.category.service;

import com.ohgiraffers.springjpa.category.entity.Category;
import com.ohgiraffers.springjpa.category.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Integer findByCategory(int categoryCode){

        Category category = categoryRepository.findByCategoryCode(categoryCode); // 없는 값은 null로 반환되기 때문에 유효성을 따로 점검할 필요가 없음

        if(Objects.isNull(category.getCategoryCode())){
            return null;
        }

        return category.getCategoryCode();

    }

    public Category insertCategory(String categoryName) {

        // 이름 중복 여부
        Category foundCategory = categoryRepository.findByCategoryName(categoryName);

        if(!Objects.isNull(foundCategory)){
            return null;
        }

        Category insertCategory = new Category();
        insertCategory.setCategoryName(categoryName);

        Category result = categoryRepository.save(insertCategory);

        // result 유효성 검사가 문제가 있다면 그건 서버 문제이다.

        return result;
    }
}
