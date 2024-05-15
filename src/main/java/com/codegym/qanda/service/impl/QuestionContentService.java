package com.codegym.qanda.service.impl;

import com.codegym.qanda.entity.QuestionContent;
import com.codegym.qanda.repository.IQuestionContentRepository;
import com.codegym.qanda.service.IQuestionContentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class QuestionContentService implements IQuestionContentService {
    private final IQuestionContentRepository questionContentRepository;

    @Override
    public Page<QuestionContent> findAll(Pageable pageable, String keyword) {
        return questionContentRepository.findAllByTittleContainsOrAnswerContainsOrContentContains(pageable, keyword, keyword, keyword);
    }

    @Override
    public QuestionContent findById(UUID id) {
        return questionContentRepository.findById(id).orElse(null);
    }

    @Override
    public QuestionContent save(QuestionContent questionContent) {
        return questionContentRepository.save(questionContent);
    }

    @Override
    public void deleteById(UUID id) {
        questionContentRepository.deleteById(id);
    }

    @Override
    public boolean existsById(UUID id) {
        return questionContentRepository.existsById(id);
    }
}
