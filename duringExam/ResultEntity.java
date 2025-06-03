package com.gips.ourapp.duringExam;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * ResultEntity は、1回の模擬試験におけるユーザーの結果情報を保持するエンティティクラス。
 * - ユーザーごとの得点（score）
 * - 試験実施日時（resultsDate）
 * を results テーブルに保存する。
 */
@Entity
@Table(name = "results")
public class ResultEntity {

	/** 結果ID（主キー、自動採番） */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "results_id")
	private Integer resultId;

	/** 試験を受けたユーザーのユーザー番号 */
	@Column(name = "user_num")
	private Integer userNum;

	/** この試験でのスコア（10点刻み） */
	@Column(name = "results_score")
	private Integer score;

	/** 試験を受けた日時 */
	@Column(name = "results_date")
	private LocalDateTime resultsDate;

	// --- Getter / Setter ---

	/**
	 * 結果IDを取得する。
	 * @return 結果ID
	 */
	public Integer getResultId() {
		return resultId;
	}

	/**
	 * 結果IDを設定する。
	 * @param resultId 結果ID
	 */
	public void setResultId(Integer resultId) {
		this.resultId = resultId;
	}

	/**
	 * ユーザー番号を取得する。
	 * @return ユーザー番号
	 */
	public Integer getUserNum() {
		return userNum;
	}

	/**
	 * ユーザー番号を設定する。
	 * @param userNum ユーザー番号
	 */
	public void setUserNum(Integer userNum) {
		this.userNum = userNum;
	}

	/**
	 * 試験スコアを取得する。
	 * @return スコア（0～100点）
	 */
	public Integer getScore() {
		return score;
	}

	/**
	 * 試験スコアを設定する。
	 * @param score スコア（0～100点）
	 */
	public void setScore(Integer score) {
		this.score = score;
	}

	/**
	 * 試験実施日時を取得する。
	 * @return 試験日
	 */
	public LocalDateTime getResultsDate() {
		return resultsDate;
	}

	/**
	 * 試験実施日時を設定する。
	 * @param resultsDate 試験日
	 */
	public void setResultsDate(LocalDateTime resultsDate) {
		this.resultsDate = resultsDate;
	}
}
