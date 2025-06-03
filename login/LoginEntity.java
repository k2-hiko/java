package com.gips.ourapp.login;

import java.io.Serializable;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

/**
 * LoginEntity は、ログインユーザーの情報を保持するエンティティクラス。
 * users テーブルに対応し、ログイン認証やユーザー管理に利用される。
 */
@Entity
@Table(name = "users")
@Data
public class LoginEntity implements Serializable {

	/** ユーザー番号（主キー、自動採番） */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_num")
	private Integer userNum;

	/** ログインID（ユニーク） */
	@Column(name = "user_id")
	private String userId;

	/** ログイン用パスワード（暗号化未対応） */
	@Column(name = "password")
	private String password;

	/** ユーザー表示名 */
	@Column(name = "user_name")
	private String userName;

	/** ユーザー登録日時 */
	@Column(name = "created_date")
	private Timestamp createdDate;
}
