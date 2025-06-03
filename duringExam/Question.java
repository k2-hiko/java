package com.gips.ourapp.duringExam;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Question エンティティクラス。
 * 試験で出題される1つの設問を表す。
 * 各設問は問題文と、それに紐づく複数の選択肢（Choice）を持つ。
 */
@Entity
@Table(name = "questions")
public class Question {

	/** 設問ID（主キー） */
	@Id
	@Column(name = "questions_id")
	private Integer id;

	/** 設問の本文（問題文） */
	@Column(name = "questions_text")
	private String text;

	/** この設問に紐づく選択肢のリスト */
	@OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
	private List<Choice> choices;

	/**
	 * 設問IDを取得する。
	 * @return 設問ID
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 設問IDを設定する。
	 * @param id 設問ID
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 設問の本文を取得する。
	 * @return 設問のテキスト
	 */
	public String getText() {
		return text;
	}

	/**
	 * 設問の本文を設定する。
	 * @param text 設問のテキスト
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * 設問に紐づく選択肢リストを取得する。
	 * @return 選択肢のリスト
	 */
	public List<Choice> getChoices() {
		return choices;
	}

	/**
	 * 設問に紐づく選択肢リストを設定する。
	 * @param choices 選択肢のリスト
	 */
	public void setChoices(List<Choice> choices) {
		this.choices = choices;
	}
}
