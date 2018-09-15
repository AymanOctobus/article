package com.octobus.article.model;

import java.time.LocalDateTime;

public class Point {

	private String pointsBy;
	private int point;
	private LocalDateTime pointGivenOn;
	public String getPointsBy() {
		return pointsBy;
	}
	public void setPointsBy(String pointsBy) {
		this.pointsBy = pointsBy;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public LocalDateTime getPointGivenOn() {
		return pointGivenOn;
	}
	public void setPointGivenOn(LocalDateTime pointGivenOn) {
		this.pointGivenOn = pointGivenOn;
	}
	
}
