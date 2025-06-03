package com.gips.ourapp.duringExam;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * 回答情報を表すエンティティクラス。
 * 試験中にユーザーが選択した回答内容と、それに対応する設問・正誤・日時を保持する。
 */
@Entity
@Table(name = "answers")
public class AnswerEntity {

	/** 回答ID（主キー） */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "answers_id")
	private Integer id;

	/** 回答者のユーザー番号 */
	@Column(name = "user_num")
	private Integer userNum;

	/** 回答した設問（外部キー: questions テーブル） */
	@ManyToOne
	@JoinColumn(name = "questions_id")
	private Question question;

	/** ユーザーが実際に入力または選択した回答のテキスト */
	@Column(name = "answer_text")
	private String answerText;

	/** 回答が正解かどうか（true: 正解, false: 不正解） */
	@Column(name = "answers_correct")
	private Boolean isCorrect;

	/** 回答日時（試験を受けた日時） */
	@Column(name = "answered_date")
	private LocalDateTime answeredDate;

	// --- Getter / Setter ---

	/**
	 * 回答IDを取得します。
	 * @return 回答ID
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 回答IDを設定します。
	 * @param id 回答ID
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * ユーザー番号を取得します。
	 * @return ユーザー番号
	 */
	public Integer getUserNum() {
		return userNum;
	}

	/**
	 * ユーザー番号を設定します。
	 * @param userNum ユーザー番号
	 */
	public void setUserNum(Integer userNum) {
		this.userNum = userNum;
	}

	/**
	 * 回答した設問を取得します。
	 * @return Question エンティティ
	 */
	public Question getQuestion() {
		return question;
	}

	/**
	 * 設問エンティティを設定します。
	 * @param question 回答対象の設問
	 */
	public void setQuestion(Question question) {
		this.question = question;
	}

	/**
	 * 回答テキストを取得します。
	 * @return 回答内容（選択肢など）
	 */
	public String getAnswerText() {
		return answerText;
	}

	/**
	 * 回答テキストを設定します。
	 * @param answerText 回答内容（選択肢など）
	 */
	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}

	/**
	 * 正解かどうかを取得します。
	 * @return true: 正解、false: 不正解
	 */
	public Boolean getIsCorrect() {
		return isCorrect;
	}

	/**
	 * 正解フラグを設定します。
	 * @param isCorrect true: 正解、false: 不正解
	 */
	public void setIsCorrect(Boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	/**
	 * 回答日時を取得します。
	 * @return 回答した日時
	 */
	public LocalDateTime getAnsweredDate() {
		return answeredDate;
	}

	/**
	 * 回答日時を設定します。
	 * @param answeredDate 回答した日時
	 */
	public void setAnsweredDate(LocalDateTime answeredDate) {
		this.answeredDate = answeredDate;
	}
}
