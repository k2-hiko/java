package com.gips.ourapp.result;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gips.ourapp.duringExam.ResultEntity;

/**
 * 試験結果（results テーブル）へのアクセスを行うリポジトリインタフェース。
 * ResultEntity を対象とし、主に最新のスコアを取得するクエリを提供する。
 */
public interface ResultRepository extends JpaRepository<ResultEntity, Integer> {

	/**
	 * 指定ユーザーの最新の試験結果のスコアを取得する。
	 * resultId の降順に並べて先頭（最新）を取得する。
	 * 
	 * @param user_num 対象ユーザーのユーザー番号
	 * @return 最新のスコア（Integer）
	 */
	@Query("SELECT r.score FROM ResultEntity r WHERE r.userNum = :userNum ORDER BY r.resultId DESC LIMIT 1")
	Integer findLatestScore(@Param("userNum") Integer user_num);

}
