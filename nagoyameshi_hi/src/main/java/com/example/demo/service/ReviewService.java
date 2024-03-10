package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.House;
import com.example.demo.entity.Review;
import com.example.demo.entity.User;
import com.example.demo.form.ReviewEditForm;
import com.example.demo.form.ReviewRegisterForm;
import com.example.demo.repository.ReviewRepository;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;

	/**
	 * レビュー登録
	 * 
	 * @param house
	 * @param user
	 * @param reviewRegisterForm
	 */
	@Transactional
	public void create(House house, User user, ReviewRegisterForm reviewRegisterForm) {

		Review review = new Review();

		review.setHouse(house);
		review.setUser(user);
		review.setScore(reviewRegisterForm.getScore());
		review.setContent(reviewRegisterForm.getContent());

		reviewRepository.save(review);
	}

	/**
	 * レビュー修正
	 * @param reviewEditForm
	 */
	@Transactional
	public void update(ReviewEditForm reviewEditForm) {

		Review review = reviewRepository.getReferenceById(reviewEditForm.getId());

		review.setScore(reviewEditForm.getScore());
		review.setContent(reviewEditForm.getContent());

		reviewRepository.save(review);
	}

	/**
	 * レビュー投稿済みチェック
	 * @param house
	 * @param user
	 * @return true(存在する), false(存在しない)
	 */
	public boolean hasUserAlreadyReviewed(House house, User user) {
		return reviewRepository.findByHouseAndUser(house, user) != null;
	}

}