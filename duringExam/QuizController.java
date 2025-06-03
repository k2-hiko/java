package com.gips.ourapp.duringExam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gips.ourapp.SessionBean;

/**
 * 試験中画面（/duringExam）を制御するコントローラクラス。
 * - 初回表示時に問題を取得しセッションに保存
 * - 回答送信時に正誤判定と記録を行い、次の問題または結果画面に遷移
 */
@Controller
public class QuizController {

	private final QuizService quizService;
	private final SessionBean sessionBean;

	/**
	 * コンストラクタ（QuizServiceとSessionBeanを注入）
	 */
	public QuizController(QuizService quizService, SessionBean sessionBean) {
		this.quizService = quizService;
		this.sessionBean = sessionBean;
	}

	/**
	 * 試験開始時の初回表示処理（10問ランダム取得）
	 * 問題と選択肢をMapとして取得し、順番と進行状況をセッションに保存。
	 * 
	 * @param session HTTPセッション
	 * @param model Thymeleafに渡すModel
	 * @return 試験画面ビュー（duringExam.html）
	 */
	@GetMapping("/duringExam")
	public String showFirstQuestion(HttpSession session, Model model) {
		Map<Integer, List<Choice>> questionMap = quizService.getRandomQuestions();
		List<Integer> questionOrder = new ArrayList<>(questionMap.keySet());

		Integer userNum = sessionBean.getUserNum();
		if (userNum == null) {
			return "redirect:/login";
		}

		// 試験情報をセッションに保存
		session.setAttribute("questionMap", questionMap);
		session.setAttribute("questionOrder", questionOrder);
		session.setAttribute("currentIndex", 0);
		session.setAttribute("answerList", new ArrayList<Answer>());
		session.setAttribute("user_num", userNum);

		return loadQuestion(model, questionMap, questionOrder, 0);
	}

	/**
	 * 解答送信後の処理。正誤判定を行い、回答リストに追加。
	 * 最後の問題であれば結果を保存し、結果画面に遷移。
	 * 
	 * @param session HTTPセッション
	 * @param questionId 回答対象の設問ID
	 * @param selectedChoice ユーザーが選択した選択肢
	 * @param action ボタンのアクション（未使用だがPOST送信用）
	 * @param model Thymeleafに渡すModel
	 * @return 次の問題、または結果画面への遷移パス
	 */
	@PostMapping("/duringExam")
	public String handleAnswer(HttpSession session,
			@RequestParam("questionId") Integer questionId,
			@RequestParam(value = "answer", required = false) String selectedChoice,
			@RequestParam("action") String action,
			Model model) {

		Map<Integer, List<Choice>> questionMap = (Map<Integer, List<Choice>>) session.getAttribute("questionMap");
		List<Integer> questionOrder = (List<Integer>) session.getAttribute("questionOrder");
		List<Answer> answerList = (List<Answer>) session.getAttribute("answerList");
		int currentIndex = (int) session.getAttribute("currentIndex");

		// 正誤判定（選択肢未選択でも空文字として扱う）
		boolean isCorrect = false;
		if (selectedChoice != null) {
			List<Choice> currentChoices = questionMap.get(questionId);
			isCorrect = quizService.judgeAnswer(currentChoices, selectedChoice);
		}

		// 回答記録
		answerList.add(new Answer(questionId, selectedChoice, isCorrect));
		session.setAttribute("answerList", answerList);

		// 最終問題なら試験終了 → 結果保存 → 結果画面へ
		if (currentIndex >= questionOrder.size() - 1) {
			Integer userNum = sessionBean.getUserNum();
			quizService.saveAnswersAndResult(answerList, userNum);
			return "redirect:/result";
		}

		// 次の問題へ
		currentIndex++;
		session.setAttribute("currentIndex", currentIndex);
		return loadQuestion(model, questionMap, questionOrder, currentIndex);
	}

	/**
	 * 問題表示用の共通処理。設問テキストと選択肢をModelに詰めてビューへ渡す。
	 * 
	 * @param model Thymeleafに渡すModel
	 * @param map 設問IDをキーとした選択肢マップ
	 * @param order 設問IDの出題順リスト
	 * @param index 現在の出題インデックス
	 * @return ビュー名（duringExam.html）
	 */
	private String loadQuestion(Model model, Map<Integer, List<Choice>> map, List<Integer> order, int index) {
		Integer questionId = order.get(index);
		List<Choice> choices = map.get(questionId);

		model.addAttribute("questionId", questionId);
		model.addAttribute("questionText", choices.get(0).getQuestion().getText());
		model.addAttribute("choices", choices);
		model.addAttribute("questionNumber", index + 1);

		return "duringExam";
	}
}
