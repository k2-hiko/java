package com.gips.ourapp.result;

import java.util.Map;

/**
 * 試験結果に関する情報を保持するフォームクラス。
 * 問題ごとの正誤結果（resultMap）や、正解数（correctCount）を管理する。
 */
public class ResultInfo {

	/**
	 * 問題文（String）と、その問題に対する正誤（true：正解、false：不正解）を格納するマップ
	 */
	private Map<String, Boolean> resultMap;

	/**
	 * 正解数（例：10問中7問正解なら 7）
	 */
	private int correctCount;

	/**
	 * resultMap の getter
	 * 
	 * @return 問題文と正誤の対応マップ
	 */
	public Map<String, Boolean> getResultMap() {
		return resultMap;
	}

	/**
	 * resultMap の setter
	 * 
	 * @param map 問題文と正誤の対応マップ
	 */
	public void setResultMap(Map<String, Boolean> map) {
		this.resultMap = map;
	}

	/**
	 * correctCount の getter
	 * 
	 * @return 正解数（整数）
	 */
	public int getCorrectCount() {
		return correctCount;
	}

	/**
	 * correctCount の setter
	 * 
	 * @param count 正解数を設定する
	 */
	public void setCorrectCount(int count) {
		this.correctCount = count;
	}

}
