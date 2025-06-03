package com.gips.ourapp.register;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * users テーブルにアクセスするリポジトリインタフェース。
 * Spring Data JPA により実装不要で CRUD 操作が提供される。
 */
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	/**
	 * ユーザーIDに一致するユーザーを検索する。
	 * 主にログイン・登録処理時の重複チェックに使用。
	 * 
	 * @param userId 入力されたユーザーID
	 * @return 該当するユーザーがいればそのエンティティ、なければ null
	 */
	UserEntity findByUserId(String userId);

	/**
	 * ユーザー名に一致するユーザーを検索する。
	 * 必須ではないが、ユーザー名の重複チェックなどに使用可能。
	 * 
	 * @param userName 入力されたユーザー名
	 * @return 該当するユーザーがいればそのエンティティ、なければ null
	 */
	UserEntity findByUserName(String userName);
}
