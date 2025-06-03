package com.gips.ourapp.startExam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 試験開始画面の表示を担当するコントローラークラス
 */
@Controller
public class ExamController {

	/**
	 * 試験開始画面の初期表示処理
	 * 
	 * @return 試験開始画面のビュー名（startExam.html）
	 */
	@GetMapping("/start") // URLが /start の場合にマッピング
	public String showStart() {
		// 試験開始画面のテンプレート（startExam.html）を返却する
		return "startExam";
	}
}
