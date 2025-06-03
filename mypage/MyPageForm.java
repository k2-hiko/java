package com.gips.ourapp.mypage;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

/**
 * マイページ画面に表示するデータを格納するフォームクラス。
 * ユーザー名、試験結果のリスト、設問ごとの記録を含む。
 */
@Data
public class MyPageForm {

	/** ユーザー名（画面上部に表示） */
	private String userName;

	/** 直近の試験結果（得点・日付）の一覧 */
	private List<ScoreInfo> scoreList;

	/** 最新の試験で出題された問題情報（問題文・正解） */
	private List<QuestionInfo> questionList;

	/**
	 * 内部クラス：試験結果の情報を表す。
	 * 日付と得点を保持し、スコア一覧表示に使用される。
	 */
	@Data
	public static class ScoreInfo {
		/** 試験日（受験日時） */
		private LocalDateTime answeredDate;

		/** 試験の得点（0〜100点） */
		private int resultsScore;
	}

	/**
	 * 内部クラス：設問ごとの情報を表す。
	 * 問題文と正解の選択肢を保持する。
	 */
	@Data
	public static class QuestionInfo {
		/** 問題文（設問内容） */
		private String questionsText;

		/** 正しい選択肢（正解） */
		private String correctChoice;
	}
}
