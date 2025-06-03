package com.gips.ourapp.mypage;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gips.ourapp.login.LoginEntity;

/**
 * マイページ画面用のデータ取得を担当するリポジトリ。
 * 主にログインユーザー名・試験結果履歴・問題情報などを取得する。
 */
@Repository
public interface MyPageRepository extends JpaRepository<LoginEntity, Integer> {

	/**
	 * 指定されたユーザー番号に対応するユーザー名を取得する。
	 *
	 * @param userNum ユーザー番号
	 * @return ユーザー名（例："田中 太郎"）
	 */
	@Query(value = "SELECT user_name FROM users WHERE user_num = :userNum", nativeQuery = true)
	String findUserName(@Param("userNum") int userNum);

	/**
	 * 指定されたユーザーの直近10件の試験記録（日付・スコア）を取得する。
	 *
	 * @param userNum ユーザー番号
	 * @return Object[] のリスト（[0]=LocalDateTime, [1]=int）
	 */
	@Query(value = """
			SELECT r.results_date, r.results_score
			FROM results r
			WHERE r.user_num = :userNum
			ORDER BY r.results_date DESC
			LIMIT 10
			""", nativeQuery = true)
	List<Object[]> findTestRecordRaw(@Param("userNum") int userNum);

	/**
	 * すべての問題文と正解の選択肢を取得する。
	 * ※ 正解選択肢は is_correct = TRUE に基づく。
	 *
	 * @return Object[] のリスト（[0]=問題ID, [1]=問題文, [2]=正解選択肢）
	 */
	@Query(value = """
			SELECT q.questions_id, q.questions_text, c.choices_text AS correct_choice
			FROM questions q
			JOIN choices c ON q.questions_id = c.questions_id
			WHERE c.is_correct = TRUE
			ORDER BY q.questions_id ASC
			""", nativeQuery = true)
	List<Object[]> findQuestionRaw();
}
