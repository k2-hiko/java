package com.gips.ourapp.mypage;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gips.ourapp.mypage.MyPageForm.QuestionInfo;
import com.gips.ourapp.mypage.MyPageForm.ScoreInfo;

/**
 * マイページの表示に必要なデータを取得し、MyPageForm に詰めて返却するサービスクラス。
 */
@Service
public class MyPageService {

	/** マイページ用のデータ取得を行うリポジトリ */
	private final MyPageRepository repository;

	/**
	 * コンストラクタインジェクションでリポジトリを注入
	 *
	 * @param repository MyPageRepository インスタンス
	 */
	public MyPageService(MyPageRepository repository) {
		this.repository = repository;
	}

	/**
	 * 指定ユーザーのマイページデータを生成するメソッド。
	 * ユーザー名・試験履歴・問題情報をリポジトリから取得し、MyPageForm に格納して返す。
	 *
	 * @param userNum ユーザー番号（ログイン済みユーザー）
	 * @return MyPageForm マイページ表示に必要なデータ
	 */
	public MyPageForm getMyPage(int userNum) {
		MyPageForm form = new MyPageForm();

		// (1) ユーザー名を取得してフォームにセット
		String userName = repository.findUserName(userNum);
		form.setUserName(userName);

		// (2) 試験記録を取得 → ScoreInfo に変換して格納
		List<Object[]> rawList = repository.findTestRecordRaw(userNum);
		List<ScoreInfo> scoreList = new ArrayList<>();
		for (Object[] row : rawList) {
			Timestamp timestamp = (Timestamp) row[0];
			Integer score = (Integer) row[1];

			ScoreInfo info = new ScoreInfo();
			info.setAnsweredDate(timestamp.toLocalDateTime());
			info.setResultsScore(score);
			scoreList.add(info);
		}
		form.setScoreList(scoreList);

		// (3) 問題文と正解の選択肢を取得 → QuestionInfo に変換して格納
		List<Object[]> rawQuestionList = repository.findQuestionRaw();
		List<QuestionInfo> questionList = new ArrayList<>();
		for (Object[] row : rawQuestionList) {
			String questionsText = (String) row[1];
			String correctChoice = (String) row[2];

			QuestionInfo info = new QuestionInfo();
			info.setQuestionsText(questionsText);
			info.setCorrectChoice(correctChoice);
			questionList.add(info);
		}
		form.setQuestionList(questionList);

		// (4) すべての情報を詰めたフォームを返却
		return form;
	}
}
