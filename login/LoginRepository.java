package com.gips.ourapp.login;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * LoginEntity に対するデータベース操作を行うリポジトリインターフェース。
 * Spring Data JPA を使用して、基本的なCRUD操作に加えて
 * ユーザーIDによる検索が可能。
 */
public interface LoginRepository extends JpaRepository<LoginEntity, Integer> {

	/**
	 * 指定されたユーザーIDに一致するユーザー情報を取得する。
	 * 
	 * @param userId ログインID
	 * @return 一致するユーザー情報（存在しない場合は空）
	 */
	Optional<LoginEntity> findByUserId(String userId);
}
