package com.gips.ourapp.duringExam;

/**
 * ユーザーが試験中に選択した回答を表すクラス。
 * 各回答は1つの設問に対する選択肢と正誤情報を持つ。
 */
public class Answer {

	/** 回答対象の設問ID */
	private Integer questionId;

	/** ユーザーが選択した選択肢の内容 */
	private String selectedChoice;

	/** 回答が正解かどうかのフラグ */
	private boolean isCorrect;

	/**
	 * コンストラクタ
	 * 
	 * @param questionId 設問のID
	 * @param selectedChoice 選択された選択肢
	 * @param isCorrect 正解であるかどうか
	 */
	public Answer(Integer questionId, String selectedChoice, boolean isCorrect) {
		this.questionId = questionId;
		this.selectedChoice = selectedChoice;
		this.isCorrect = isCorrect;
	}

	/**
	 * 設問IDを取得する。
	 * @return 設問ID
	 */
	public Integer getQuestionId() {
		return questionId;
	}

	/**
	 * ユーザーが選択した選択肢を取得する。
	 * @return 選択肢
	 */
	public String getSelectedChoice() {
		return selectedChoice;
	}

	/**
	 * 回答が正解かどうかを取得する。
	 * @return true: 正解, false: 不正解
	 */
	public boolean isCorrect() {
		return isCorrect;
	}
}
