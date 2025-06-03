package com.gips.ourapp;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * セッションスコープで保持される Bean クラス
 *
 * このクラスはユーザーごとにセッション単位でインスタンスが生成される。
 * ユーザー情報など、ログイン後に必要な情報を一時的に保持するのに利用する。
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS) // セッションスコープで管理
@Data // Getter / Setter などを自動生成（Lombok）
public class SessionBean implements Serializable {

	/**
	 * ログイン中のユーザーの一意なID（user_num）
	 * ログイン時にセットし、ログアウト時にnullにする。
	 */
	private Integer userNum;
}
