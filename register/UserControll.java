package com.gips.ourapp.register;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * ユーザー登録画面に関するリクエストを処理するコントローラークラス。
 */
@Controller
public class UserControll {

	/** ユーザー登録のサービスクラス */
	private final UserService userService;

	/**
	 * コンストラクタインジェクションにより UserService を注入
	 * 
	 * @param userService ユーザー登録用サービス
	 */
	public UserControll(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 新規登録画面の初期表示処理。
	 * ユーザーフォームをModelに追加して、ビュー「registerUser」を返す。
	 *
	 * @param model 画面表示用Model
	 * @return 新規登録画面のHTMLテンプレート名
	 */
	@GetMapping("/register") // http://localhost:8081/register
	public String showRegisterForm(Model model) {
		model.addAttribute("userForm", new UserForm());
		return "registerUser";
	}

	/**
	 * 新規ユーザーの登録処理。
	 * ユーザー情報をUserServiceでバリデーションと登録処理を行い、
	 * 成功時はログイン画面にリダイレクト、失敗時は登録画面に戻す。
	 *
	 * @param userForm ユーザー入力情報（ID、名前、パスワード等）
	 * @param model    エラーメッセージ表示用Model
	 * @return 登録成功時はリダイレクト、失敗時は再度登録画面
	 */
	@PostMapping("/register")
	public String registerUser(@ModelAttribute("userForm") UserForm userForm, Model model) {
		String result = userService.registerUser(userForm);

		if (!"success".equals(result)) {
			model.addAttribute("errorMessage", result);
			return "registerUser";
		}

		return "redirect:/login";
	}
}
