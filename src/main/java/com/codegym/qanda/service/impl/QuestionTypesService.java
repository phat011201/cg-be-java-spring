package com.codegym.qanda.service.impl;

import com.codegym.qanda.entity.QuestionTypes;
import com.codegym.qanda.repository.IQuestionTypesRepository;
import com.codegym.qanda.service.IQuestionTypesService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class QuestionTypesService implements IQuestionTypesService {
    private final IQuestionTypesRepository questionTypesRepository;

    @Override
    public Page<QuestionTypes> findAll(Pageable pageable, String keyword) {
        pageable = Pageable.unpaged();
        return questionTypesRepository.findAllByTypeContains(pageable, keyword);
    }

    @Override
    public QuestionTypes findById(UUID id) {
        return null;
    }

    @Override
    public QuestionTypes save(QuestionTypes questionTypes) {
        return questionTypesRepository.save(questionTypes);
    }

    @Override
    public void deleteById(UUID id) {

    }

    @Override
    public boolean existsById(UUID id) {
        return false;
    }
}
