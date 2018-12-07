package com.articles.entity;
/**
 * @author sgudapati
 *
 */
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.articles.enums.VoteType;

@Entity
@Table(name="Articles")
@EntityListeners(AuditingEntityListener.class)
public class Article {
	
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private String title;
	

	private String description;

	private String tag;
	
	@Column
    @ElementCollection	
	Map<VoteType, Integer> voteCount = new HashMap<>();


	public Map<VoteType, Integer> getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(Map<VoteType, Integer> voteCount) {
		this.voteCount = voteCount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	

	public void vote(VoteType voteType) {
		if (voteType != null) {
			
			synchronized (this) {
				Integer count = voteCount.get(voteType);
				if (count == null) {
					voteCount.put(voteType, 1);
				} else {
					voteCount.put(voteType, count + 1);
				}
			}
		}
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", description=" + description + ", tag=" + tag
				+ ", voteCount=" + voteCount + "]";
	}

	}


