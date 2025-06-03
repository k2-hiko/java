package com.gips.ourapp.duringExam;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * AnswerEntity に対するデータベース操作を行うリポジトリインターフェース。
 * Spring Data JPA により、基本的なCRUD操作が自動的に提供される。
 *
 * ・findById(), findAll(), save(), delete() などが自動で使用可能。
 */
public interface AnswerReposit extends JpaRepository<AnswerEntity, Integer> {
}
