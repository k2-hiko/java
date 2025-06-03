package com.gips.ourapp.mypage;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gips.ourapp.SessionBean;

/**
 * マイページ画面の表示を担当するコントローラクラス。
 * - /mypage（GET）でマイページ初期表示処理を行う。
 * - ログイン状態をセッションで判定し、必要な情報をサービス経由で取得する。
 */
@Controller
public class MyPageController {

	/** マイページ情報を取得するサービスクラス */
	private final MyPageService service;

	/** セッションにユーザー情報を保持するためのBean */
	private final SessionBean sessionBean;

	/**
	 * コンストラクタインジェクション
	 * 
	 * @param service マイページ情報取得用サービス
	 * @param sessionBean セッション管理用Bean
	 */
	public MyPageController(MyPageService service, SessionBean sessionBean) {
		this.service = service;
		this.sessionBean = sessionBean;
	}

	/**
	 * マイページ初期表示処理（GET）。
	 * セッションにユーザーがログインしているかを確認し、
	 * マイページに必要な情報を取得して画面へ渡す。
	 * 
	 * @param model ビューへ渡すモデルオブジェクト
	 * @return マイページビュー名（myPage.html）
	 */
	@GetMapping("/mypage")
	public String showMypage(Model model) {
		// 未ログインならログインページへリダイレクト
		if (sessionBean.getUserNum() == null) {
			return "redirect:/login";
		}

		// ログインユーザーの情報を取得
		Integer userNum = sessionBean.getUserNum();
		MyPageForm myPageData = service.getMyPage(userNum);

		// ビューにデータを渡す
		model.addAttribute("myPageData", myPageData);

		// マイページビューを表示
		return "myPage";
	}
}
