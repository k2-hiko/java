package com.gips.ourapp.result;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * 問題情報を保持するエンティティクラス。
 * questions テーブルと対応し、問題IDおよび問題文を管理する。
 */
@Entity
@Table(name = "questions")
public class QuestionEntity {

	// 主キー（自動採番）: 問題ID
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer questionId;

	// 問題文（ユーザーに表示するクイズの内容）
	private String text;

	// --- Getter / Setter（必要に応じて追加してください） ---
}
