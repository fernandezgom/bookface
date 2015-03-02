package com.bookface.repository;

// Generated 02-Mar-2015 16:57:19 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Author generated by hbm2java
 */
@Entity
@Table(name = "author", catalog = "bookface")
public class Author implements java.io.Serializable {

	private Integer idAuthor;
	private String name;
	private String bio;
	private Set<Book> books = new HashSet<Book>(0);

	public Author() {
	}

	public Author(String name, String bio, Set<Book> books) {
		this.name = name;
		this.bio = bio;
		this.books = books;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_author", unique = true, nullable = false)
	public Integer getIdAuthor() {
		return this.idAuthor;
	}

	public void setIdAuthor(Integer idAuthor) {
		this.idAuthor = idAuthor;
	}

	@Column(name = "name", length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "bio", length = 2000)
	public String getBio() {
		return this.bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "author")
	public Set<Book> getBooks() {
		return this.books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}

}
