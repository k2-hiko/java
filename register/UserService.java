package com.gips.ourapp.register;

import java.sql.Timestamp;

import org.springframework.stereotype.Service;

/**
 * ユーザー登録を行うサービスクラス。
 * 入力バリデーション、重複チェック、DB登録を担当する。
 */
@Service
public class UserService {

	// UserRepository のインスタンス
	private final UserRepository userRepository;

	// コンストラクタインジェクションでリポジトリを受け取る
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	/**
	 * ユーザー登録処理。
	 * フォーム入力のバリデーションを行い、DBへ保存する。
	 *
	 * @param form ユーザー登録フォーム
	 * @return "success" またはエラーメッセージ
	 */
	public String registerUser(UserForm form) {
		// 入力バリデーション実行
		String validationError = authentication(form);
		if (!"success".equals(validationError)) {
			return validationError; // エラー時はその内容を返す
		}

		// UserForm を UserEntity に変換して登録処理へ
		UserEntity user = new UserEntity();
		user.setUserId(form.getUserId());
		user.setUserName(form.getUserName());
		user.setPassword(form.getPassword());
		user.setCreatedDate(new Timestamp(System.currentTimeMillis()));

		// 保存処理（DBへの登録）
		try {
			userRepository.save(user);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "登録に失敗しました：" + e.getMessage();
		}
	}

	/**
	 * 入力バリデーションと重複チェックを行う。
	 *
	 * @param form 入力されたフォーム情報
	 * @return "success" またはエラーメッセージ
	 */
	private String authentication(UserForm form) {

		// --- 必須入力チェック ---
		if (form.getUserId() == null || form.getUserId().trim().isEmpty()) {
			return "ユーザーIDを入力してください。";
		}
		if (form.getUserName() == null || form.getUserName().trim().isEmpty()) {
			return "ユーザー名を入力してください。";
		}
		if (form.getPassword() == null || form.getPassword().trim().isEmpty()) {
			return "パスワードを入力してください。";
		}
		if (!form.getPassword().equals(form.getConfirmPassword())) {
			return "パスワードが一致しません。";
		}

		// --- 桁数チェック ---
		if (form.getUserId().length() > 30) {
			return "ユーザーIDは30文字以内で入力してください。";
		}
		if (form.getPassword().length() < 5) {
			return "パスワードは5文字以上で入力してください。";
		}
		if (form.getPassword().length() > 20) {
			return "パスワードは20文字以内で入力してください。";
		}
		if (form.getUserName().length() > 10) {
			return "ユーザー名は10文字以内で入力してください。";
		}

		// --- 形式チェック ---
		if (!form.getUserId().matches("^[\\w.-]+@[\\w.-]+\\.\\w+$")) {
			return "ユーザーIDはメールアドレス形式で入力してください。";
		}
		if (!form.getPassword().matches(".*[A-Z].*")) {
			return "パスワードには少なくとも1つの大文字を含めてください。";
		}
		if (!form.getPassword().matches(".*\\d.*")) {
			return "パスワードには少なくとも1つの数字を含めてください。";
		}

		// --- 重複チェック ---
		try {
			if (userRepository.findByUserId(form.getUserId()) != null) {
				return "このユーザーIDはすでに使われています。";
			}
			if (userRepository.findByUserName(form.getUserName()) != null) {
				return "このユーザー名はすでに使われています。";
			}
			return "success"; // すべて通過した場合
		} catch (Exception e) {
			e.printStackTrace();
			return "DB内部エラーが発生しました。再度お試しください。";
		}
	}
}
