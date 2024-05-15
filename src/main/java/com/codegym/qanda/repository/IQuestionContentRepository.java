package com.codegym.qanda.repository;

import com.codegym.qanda.entity.QuestionContent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IQuestionContentRepository extends JpaRepository<QuestionContent, UUID> {
    Page<QuestionContent> findAllByTittleContainsOrAnswerContainsOrContentContains(Pageable pageable, String tittle, String answer, String content);
}
