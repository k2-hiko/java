package com.gips.ourapp.register;

import lombok.Data;

/**
 * 新規ユーザー登録フォームの入力情報を保持するクラス。
 * 入力チェックやバリデーションの対象にもなる。
 */
@Data
public class UserForm {

	/** ユーザーID（ログイン時に使用） */
	private String userId;

	/** ユーザー名（ニックネームや表示名） */
	private String userName;

	/** パスワード（ログイン用） */
	private String password;

	/** パスワード確認用フィールド（確認一致チェックに使用） */
	private String confirmPassword;
}
