package com.gips.ourapp.duringExam;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ResultEntity に対するデータベース操作を提供するリポジトリインターフェース。
 * Spring Data JPA により、基本的なCRUD操作が自動的に提供される。
 *
 * 利用可能な主なメソッド：
 * - findAll()：全結果の取得
 * - findById(id)：結果IDによる検索
 * - save(entity)：新規保存または更新
 * - delete(entity)：削除
 *
 * 必要に応じて、ユーザーごとの結果取得や日付順ソートなどの
 * カスタムメソッドを追加可能。
 */
public interface ResultReposit extends JpaRepository<ResultEntity, Integer> {
}
