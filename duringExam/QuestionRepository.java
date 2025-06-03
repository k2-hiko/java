package com.gips.ourapp.duringExam;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Question エンティティに対するデータベース操作を提供するリポジトリインターフェース。
 * Spring Data JPA により、基本的なCRUD操作が自動的に使用可能となる。
 *
 * 利用可能な主なメソッド：
 * - findAll()：すべての設問を取得
 * - findById(id)：IDで設問を取得
 * - save(entity)：新規保存または更新
 * - delete(entity)：設問の削除
 */
public interface QuestionRepository extends JpaRepository<Question, Integer> {
}
