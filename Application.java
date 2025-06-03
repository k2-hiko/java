package com.gips.ourapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * アプリケーションのエントリーポイント（起動クラス）
 *
 * Spring Boot アプリケーション全体の起動処理を担うクラス。
 * @SpringBootApplication により、コンポーネントスキャン、オートコンフィギュレーションが有効化される。
 */
@SpringBootApplication // コンポーネントスキャンと自動構成を有効にするアノテーション
public class Application {

	/**
	 * メインメソッド（アプリケーションの起動処理）
	 * SpringApplication.run() を呼び出すことで Spring Boot アプリを起動する。
	 *
	 * @param args コマンドライン引数
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
