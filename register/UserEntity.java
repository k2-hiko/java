package com.gips.ourapp.register;

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
 * ユーザー情報を表すエンティティクラス。
 * users テーブルとマッピングされる。
 */
@Entity
@Table(name = "users")
@Data
public class UserEntity implements Serializable {

	/** 主キー：ユーザーの識別子（自動採番） */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_num")
	private Integer userNum;

	/** ログイン用のユーザーID（ユニーク） */
	@Column(name = "user_id")
	private String userId;

	/** パスワード（平文保存は非推奨） */
	@Column(name = "password")
	private String password;

	/** ユーザーの表示名（ニックネームなど） */
	@Column(name = "user_name")
	private String userName;

	/** ユーザー登録日時 */
	@Column(name = "created_date")
	private Timestamp createdDate;
}
