package com.gips.ourapp.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.gips.ourapp.SessionBean;

/**
 * ログイン画面の表示およびログイン認証処理を担当するコントローラクラス。
 * - /login（GET）：ログイン画面の初期表示
 * - /login（POST）：フォーム入力値によるユーザー認証
 */
@Controller
public class LoginController {

	/** 認証処理を提供するサービス */
	private final LoginService loginService;

	/** ログインユーザー情報を保持するセッションBean */
	private final SessionBean sessionBean;

	/**
	 * コンストラクタインジェクション
	 * @param loginService ログイン用サービス
	 * @param sessionBean セッション管理用のBean
	 */
	public LoginController(LoginService loginService, SessionBean sessionBean) {
		this.loginService = loginService;
		this.sessionBean = sessionBean;
	}

	/**
	 * ログイン画面の初期表示処理（GET）。
	 * フォーム用のLoginFormをModelに追加し、login.htmlを返す。
	 * 
	 * @param model ビューに渡すModelオブジェクト
	 * @return ログイン画面ビュー名
	 */
	@GetMapping("/login")
	public String showLoginForm(Model model) {
		model.addAttribute("loginForm", new LoginForm());
		return "login";
	}

	/**
	 * ログイン認証処理（POST）。
	 * フォームで入力されたユーザーIDとパスワードを使って認証を行い、
	 * 成功時はセッションにユーザー番号を保存、失敗時はエラーメッセージを返却する。
	 * 
	 * @param form 画面から送信されたログインフォーム
	 * @param model ビューに渡すModelオブジェクト
	 * @return 認証成功時は /start へリダイレクト、失敗時は login に戻る
	 */
	@PostMapping("/login")
	public String login(@ModelAttribute("loginForm") LoginForm form, Model model) {
		// ログイン認証（成功 → "success"、失敗 → エラーメッセージ）
		String result = loginService.authentication(form);

		if (!"success".equals(result)) {
			// 認証失敗：エラーメッセージ表示して再表示
			model.addAttribute("errorMessage", result);
			return "login";
		}

		// 認証成功：ログインユーザーの情報を取得
		LoginEntity loginUser = loginService.getLoginUser(form.getUserid());

		// ユーザー番号をセッションに保存
		sessionBean.setUserNum(loginUser.getUserNum());

		// 試験開始画面へリダイレクト
		return "redirect:/start";
	}
}
