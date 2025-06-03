package com.gips.ourapp.duringExam;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Choice エンティティに対するデータベース操作を提供するリポジトリインターフェース。
 * Spring Data JPA により、基本的なCRUD操作に加え、独自の検索メソッドも利用可能。
 */
public interface ChoiceRepository extends JpaRepository<Choice, Integer> {

	/**
	 * 指定された設問IDに紐づく選択肢一覧を取得する。
	 *
	 * @param questionId 設問ID
	 * @return 該当する選択肢のリスト
	 */
	List<Choice> findByQuestionId(Integer questionId);
}
