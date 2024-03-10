package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.House;
import com.example.demo.entity.Review;
import com.example.demo.entity.User;

public interface ReviewRepository extends JpaRepository<Review, Integer>{
	
	// レビューの作成日時順(降順6件)取得
	public List<Review> findTop6ByHouseOrderByCreatedAtDesc(House house);
	
	// 店舗とユーザーに紐づくレビュー(1件)取得
	public Review findByHouseAndUser(House house, User user);
	
	// 店舗に紐づくレビュー件数取得
	public long countByHouse(House house);
	
	// 店舗に紐づくレビューの作成日時順（降順)全件取得
	public Page<Review> findByHouseOrderByCreatedAtDesc(House house, Pageable pageable);

}
