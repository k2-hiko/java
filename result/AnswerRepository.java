package com.gips.ourapp.result;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gips.ourapp.duringExam.AnswerEntity;

/**
 * AnswerEntity に対するデータアクセスを行うリポジトリインタフェース。
 * 試験結果画面に必要な情報（正解数、問題文、回答日など）を取得する。
 */
public interface AnswerRepository extends JpaRepository<AnswerEntity, Integer> {

	/**
	 * 指定されたユーザーの最新の回答日時（最も新しい試験日）を取得する。
	 *
	 * @param num ユーザー番号
	 * @return 最新の回答日時
	 */
	@Query("SELECT MAX(a.answeredDate) FROM AnswerEntity a WHERE a.userNum = :userNum")
	LocalDateTime findLatestAnsweredDate(@Param("userNum") Integer num);

	/**
	 * 指定されたユーザーの最新の試験における回答数（全体の問題数）をカウントする。
	 *
	 * @param num ユーザー番号
	 * @return 最新試験の問題数
	 */
	@Query("SELECT COUNT(a) FROM AnswerEntity a WHERE a.userNum = :userNum AND a.answeredDate = (" +
			"SELECT MAX(b.answeredDate) FROM AnswerEntity b WHERE b.userNum = :userNum)")
	int countAnswers(@Param("userNum") Integer num);

	/**
	 * 指定されたユーザーの最新の試験における正解数をカウントする。
	 *
	 * @param num ユーザー番号
	 * @return 最新試験での正解数
	 */
	@Query("SELECT COUNT(a) FROM AnswerEntity a WHERE a.userNum = :userNum AND a.isCorrect = true AND a.answeredDate = ("
			+
			"SELECT MAX(b.answeredDate) FROM AnswerEntity b WHERE b.userNum = :userNum)")
	int countCorrectAnswers(@Param("userNum") Integer num);

	/**
	 * 指定されたユーザーの最新の試験における各問題の「問題文」と「正誤結果」を最大10件まで取得する。
	 * 出力順は answeredDate の降順および id 昇順。
	 *
	 * @param num ユーザー番号
	 * @param pageable ページ制限（最大10件）
	 * @return 問題文と正誤結果のペアのリスト
	 */
	@Query("SELECT q.text, a.isCorrect FROM AnswerEntity a " +
			"JOIN a.question q " +
			"WHERE a.userNum = :userNum AND FUNCTION('DATE', a.answeredDate) = (" +
			"   SELECT FUNCTION('DATE', MAX(b.answeredDate)) FROM AnswerEntity b WHERE b.userNum = :userNum) " +
			"ORDER BY a.answeredDate DESC, a.id ASC")
	List<Object[]> findTop10AnswerResultsWithQuestion(@Param("userNum") Integer num, Pageable pageable);
}
