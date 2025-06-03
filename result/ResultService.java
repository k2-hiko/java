package com.gips.ourapp.result;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * 試験結果画面に必要なデータを取得・加工するサービスクラス
 */
@Service
public class ResultService {

	// Result（スコア）を管理するリポジトリ
	private final ResultRepository resultRepository;
	// 回答情報を管理するリポジトリ
	private final AnswerRepository answerRepository;

	// コンストラクタインジェクション
	public ResultService(ResultRepository resultRepository, AnswerRepository answerRepository) {
		this.resultRepository = resultRepository;
		this.answerRepository = answerRepository;
	}

	/**
	 * 指定ユーザーの最新の試験日を取得
	 * 
	 * @param num ユーザー番号
	 * @return 最新の試験日
	 */
	public LocalDateTime getAnsweredDate(Integer num) {
		return answerRepository.findLatestAnsweredDate(num);
	}

	/**
	 * 指定ユーザーの最新のスコアを取得
	 * 
	 * @param num ユーザー番号
	 * @return 最新スコア
	 */
	public Integer getScore(Integer num) {
		return resultRepository.findLatestScore(num);
	}

	/**
	 * 指定ユーザーの最新試験における正解数を取得
	 * 
	 * @param num ユーザー番号
	 * @return 正解数
	 */
	public int getCorrectCount(Integer num) {
		return answerRepository.countCorrectAnswers(num);
	}

	/**
	 * 指定ユーザーの最新試験における回答数（問題数）を取得
	 * 
	 * @param num ユーザー番号
	 * @return 回答数
	 */
	public int getTotalCount(Integer num) {
		return answerRepository.countAnswers(num);
	}

	/**
	 * 指定ユーザーの最新試験における問題文と正誤の一覧を取得し、ResultInfo として返す
	 * 
	 * @param num ユーザー番号
	 * @return ResultInfo（正解数と問題ごとの正誤Mapを含む）
	 */
	public ResultInfo getResultMap(Integer num) {
		// 結果を格納するインスタンスを生成
		ResultInfo result = new ResultInfo();

		// 最新試験の上位10問を取得（ページング指定）
		Pageable topTen = PageRequest.of(0, 10);
		List<Object[]> results = answerRepository.findTop10AnswerResultsWithQuestion(num, topTen);

		// 問題文と正誤を保持するマップ
		Map<String, Boolean> resultMap = new LinkedHashMap<>();
		int correctCount = 0;

		// 各結果データを処理してマップと正解数を作成
		for (Object[] row : results) {
			String questionText = (String) row[0];
			Boolean isCorrect = (Boolean) row[1];
			resultMap.put(questionText, isCorrect);
			if (Boolean.TRUE.equals(isCorrect)) {
				correctCount++;
			}
		}

		// 結果データを ResultInfo にセット
		result.setCorrectCount(correctCount);
		result.setResultMap(resultMap);
		return result;
	}
}
