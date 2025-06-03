package com.gips.ourapp.duringExam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

/**
 * 試験中処理に関するビジネスロジックを提供するサービスクラス。
 * - 問題のランダム取得
 * - 回答の正誤判定
 * - 試験結果のスコア計算とDB保存処理
 */
@Service
public class QuizService {

	private final ChoiceRepository choiceRepository;
	private final AnswerReposit answerRepository;
	private final ResultReposit resultRepository;

	/**
	 * コンストラクタ（各リポジトリを注入）
	 */
	public QuizService(ChoiceRepository choiceRepository,
			AnswerReposit answerRepository,
			ResultReposit resultRepository) {
		this.choiceRepository = choiceRepository;
		this.answerRepository = answerRepository;
		this.resultRepository = resultRepository;
	}

	/**
	 * 全選択肢を取得し、設問ごとにまとめた上でランダムに10問を選んで返す。
	 * 
	 * @return ランダムに選ばれた設問IDをキーとする選択肢リストのMap
	 */
	public Map<Integer, List<Choice>> getRandomQuestions() {
		List<Choice> allChoices = choiceRepository.findAll();

		// Questionごとに選択肢をグルーピング
		Map<Integer, List<Choice>> grouped = allChoices.stream()
				.filter(c -> c.getQuestion() != null && c.getQuestion().getId() != null)
				.collect(Collectors.groupingBy(c -> c.getQuestion().getId()));

		// ランダムに10件抽出
		List<Integer> ids = new ArrayList<>(grouped.keySet());
		Collections.shuffle(ids);
		List<Integer> selected = ids.subList(0, Math.min(10, ids.size()));

		// 選ばれた問題のみをマップに格納
		Map<Integer, List<Choice>> result = new LinkedHashMap<>();
		for (Integer id : selected) {
			result.put(id, grouped.get(id));
		}

		return result;
	}

	/**
	 * ユーザーが選択した選択肢が正解かどうかを判定する。
	 * 
	 * @param choices 設問に対するすべての選択肢
	 * @param selectedText ユーザーが選択したテキスト
	 * @return 正解であれば true、不正解であれば false
	 */
	public boolean judgeAnswer(List<Choice> choices, String selectedText) {
		return choices.stream()
				.anyMatch(c -> Boolean.TRUE.equals(c.getIsCorrect()) && c.getText().equals(selectedText));
	}

	/**
	 * 回答結果のリストから正解数を計算し、得点（10点×正解数）を返す。
	 * 
	 * @param resultList 正誤情報（true/false）のリスト
	 * @return 得点（10点刻み）
	 */
	public int finishExam(List<Boolean> resultList) {
		return (int) resultList.stream().filter(b -> b).count() * 10;
	}

	/**
	 * 回答情報と得点をデータベースに保存する。
	 * - 各設問ごとの回答（AnswerEntity）
	 * - 試験全体の得点（ResultEntity）
	 * 
	 * @param answerList 回答情報のリスト
	 * @param userNum 回答者のユーザー番号
	 */
	public void saveAnswersAndResult(List<Answer> answerList, Integer userNum) {
		for (Answer ans : answerList) {
			AnswerEntity entity = new AnswerEntity();

			// ユーザー番号を設定
			entity.setUserNum(userNum);

			// QuestionのIDのみ持つダミーオブジェクトを設定
			Question question = new Question();
			question.setId(ans.getQuestionId());
			entity.setQuestion(question);

			// 回答内容・正誤・日時を設定
			entity.setAnswerText(ans.getSelectedChoice());
			entity.setIsCorrect(ans.isCorrect());
			entity.setAnsweredDate(LocalDateTime.now());

			// 保存
			answerRepository.save(entity);
		}

		// スコア計算と結果の保存
		int score = (int) answerList.stream().filter(Answer::isCorrect).count() * 10;
		ResultEntity result = new ResultEntity();
		result.setUserNum(userNum);
		result.setScore(score);
		result.setResultsDate(LocalDateTime.now());
		resultRepository.save(result);
	}
}
