package com.codegym.qanda.repository;

import com.codegym.qanda.entity.QuestionTypes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IQuestionTypesRepository extends JpaRepository<QuestionTypes, UUID> {
    Page<QuestionTypes> findAllByTypeContains(Pageable pageable, String keyword);
}
