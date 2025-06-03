package com.gips.ourapp.login;

import java.util.Optional;

import org.springframework.stereotype.Service;

/**
 * ログインに関するビジネスロジックを提供するサービスクラス。
 * - 入力値のバリデーション
 * - ユーザーIDとパスワードの認証
 * - 認証後のユーザー情報取得
 */
@Service
public class LoginService {

	/** ユーザー情報にアクセスするリポジトリ */
	private final LoginRepository loginRepository;

	/**
	 * コンストラクタインジェクション
	 * @param loginRepository ユーザー認証に用いるリポジトリ
	 */
	public LoginService(LoginRepository loginRepository) {
		this.loginRepository = loginRepository;
	}

	/**
	 * ログイン認証処理を行う。
	 * ユーザーIDとパスワードを検証し、失敗時には適切なエラーメッセージを返却する。
	 *
	 * @param form ログイン画面からの入力フォーム
	 * @return 認証成功時："success"、失敗時：エラーメッセージ
	 */
	public String authentication(LoginForm form) {
		// 入力チェック（どちらかが空欄ならエラー）
		if (form.getUserid() == null || form.getUserid().isBlank() ||
				form.getPassword() == null || form.getPassword().isBlank()) {
			return "ユーザIDとパスワードを入力してください。";
		}

		// ユーザーIDに一致するレコードを検索
		Optional<LoginEntity> userOpt = loginRepository.findByUserId(form.getUserid());

		// ユーザーが見つからなかった場合
		if (userOpt.isEmpty()) {
			return "ユーザーID が見つかりません。";
		}

		LoginEntity user = userOpt.get();

		// パスワードが一致しない場合
		if (!user.getPassword().equals(form.getPassword())) {
			return "パスワードが正しくありません。";
		}

		// 認証成功
		return "success";
	}

	/**
	 * 認証後のログインユーザー情報を取得する。
	 *
	 * @param userId ユーザーID
	 * @return 該当するLoginEntity（存在しない場合はnull）
	 */
	public LoginEntity getLoginUser(String userId) {
		return loginRepository.findByUserId(userId).orElse(null);
	}
}
