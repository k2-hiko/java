package com.gips.ourapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * ログアウト処理を担当するコントローラークラス
 */
@Controller
public class LogoutController {

	private final SessionBean sessionBean;

	/**
	 * コンストラクタインジェクションで SessionBean を受け取る
	 * @param sessionBean セッション管理用のBean
	 */
	public LogoutController(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	/**
	 * ログアウト処理を実行し、ログイン画面へ遷移
	 * @return ログイン画面へのリダイレクトパス
	 */
	@GetMapping("/logout")
	public String logout() {
		// セッションからユーザー情報を削除することでログアウト処理を行う
		sessionBean.setUserNum(null);

		// ログイン画面へリダイレクト
		return "redirect:/login";
	}
}
