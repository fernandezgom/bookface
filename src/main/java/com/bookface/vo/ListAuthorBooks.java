package com.bookface.vo;

import java.util.List;

public class ListAuthorBooks {
	
	private String author;
	private String bio;
	private List<BookDetailsVO> books;
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public List<BookDetailsVO> getBooks() {
		return books;
	}
	public void setBooks(List<BookDetailsVO> books) {
		this.books = books;
	}
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}

}
