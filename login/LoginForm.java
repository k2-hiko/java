package com.gips.ourapp.login;

import lombok.Data;

/**
 * ログイン画面のフォーム入力情報を保持するクラス。
 * ユーザーが入力したIDとパスワードをコントローラに渡すために使用される。
 */
@Data
public class LoginForm {

	/** ユーザーID（ログイン用） */
	private String userid;

	/** パスワード（ログイン用） */
	private String password;
}
