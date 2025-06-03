package com.gips.ourapp.result;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gips.ourapp.SessionBean;

/**
 * 試験結果画面の表示を担当するコントローラクラス。
 * /result のリクエストを処理し、スコア・受験日時・問題別正誤を画面に表示する。
 */
@Controller
public class ResultController {

	// サービスクラスのインスタンス
	private final ResultService resultService;
	// セッション情報保持クラスのインスタンス
	private final SessionBean sessionBean;

	// コンストラクタによる依存性注入
	public ResultController(ResultService resultService, SessionBean sessionBean) {
		this.resultService = resultService;
		this.sessionBean = sessionBean;
	}

	/**
	 * 試験結果画面の初期表示処理
	 * 
	 * @param model 画面に渡す属性情報を格納するModel
	 * @return 結果画面(result.html)へのパス
	 */
	@GetMapping("/result")
	public String showResult(Model model) {

		// セッションからユーザー番号を取得（未ログインならログイン画面へ）
		Integer userNum = sessionBean.getUserNum();
		if (userNum == null) {
			return "redirect:/login";
		}

		// スコアと受験日時を取得
		Integer score = resultService.getScore(userNum);
		LocalDateTime date = resultService.getAnsweredDate(userNum);

		// 問題ごとの正誤情報などを含む結果情報を取得
		ResultInfo result = resultService.getResultMap(userNum);

		// Viewに渡す属性を設定
		model.addAttribute("score", score); // 得点
		model.addAttribute("date", date); // 試験日時
		model.addAttribute("resultMap", result.getResultMap()); // 問題文と正誤（Map）
		model.addAttribute("correctCount", result.getCorrectCount()); // 正解数
		model.addAttribute("totalCount", result.getResultMap().size()); // 全体の問題数

		// 結果画面（result.html）を返却
		return "result";
	}
}
