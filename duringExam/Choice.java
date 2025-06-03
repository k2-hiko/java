package com.gips.ourapp.duringExam;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Choice エンティティクラス。
 * 試験問題に対する選択肢を表す。
 * 各選択肢は特定の Question に紐づき、テキスト内容と正解フラグを持つ。
 */
@Entity
@Table(name = "choices")
public class Choice {

	/** 選択肢ID（主キー） */
	@Id
	@Column(name = "choices_id")
	private Integer id;

	/** 紐づく設問（外部キー: questions_id） */
	@ManyToOne
	@JoinColumn(name = "questions_id")
	private Question question;

	/** 選択肢のテキスト内容 */
	@Column(name = "choices_text")
	private String text;

	/** この選択肢が正解かどうか（true: 正解） */
	@Column(name = "is_correct")
	private Boolean isCorrect;

	// --- Getter / Setter ---

	/**
	 * 選択肢IDを取得する。
	 * @return 選択肢ID
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 選択肢IDを設定する。
	 * @param id 選択肢ID
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 紐づく設問を取得する。
	 * @return Question エンティティ
	 */
	public Question getQuestion() {
		return question;
	}

	/**
	 * 紐づく設問を設定する。
	 * @param question 設問エンティティ
	 */
	public void setQuestion(Question question) {
		this.question = question;
	}

	/**
	 * 選択肢のテキストを取得する。
	 * @return 選択肢テキスト
	 */
	public String getText() {
		return text;
	}

	/**
	 * 選択肢のテキストを設定する。
	 * @param text 選択肢テキスト
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * 正解フラグを取得する。
	 * @return true: 正解、false: 不正解
	 */
	public Boolean getIsCorrect() {
		return isCorrect;
	}

	/**
	 * 正解フラグを設定する。
	 * @param isCorrect true: 正解、false: 不正解
	 */
	public void setIsCorrect(Boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	/**
	 * 選択肢テキストを取得する（別名メソッド）。
	 * @return 選択肢テキスト
	 */
	public String getChoicesText() {
		return text;
	}
}
