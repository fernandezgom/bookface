package com.bookface.vo;

import java.util.List;

public class BookDetailsVO {
	
	private String book;
	private String syn;
	private String author;
	private List<UserDetailsVO> likes;
	
	public String getBook() {
		return book;
	}
	public void setBook(String book) {
		this.book = book;
	}
	public String getSyn() {
		return syn;
	}
	public void setSyn(String syn) {
		this.syn = syn;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String idAuthor) {
		this.author = idAuthor;
	}
	public List<UserDetailsVO> getLikes() {
		return likes;
	}
	public void setLikes(List<UserDetailsVO> likes) {
		this.likes = likes;
	}
	
}
