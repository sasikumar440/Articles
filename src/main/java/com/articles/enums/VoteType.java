package com.articles.enums;
/**
 * @author sgudapati
 *
 */
import java.util.Comparator;
import java.util.PriorityQueue;

import com.articles.entity.Article;

public enum VoteType {
UPVOTE, DOWNVOTE;
	private PriorityQueue<Article> populorArticles;
	private Comparator<Article> comparator = null;

	VoteType() {
	
		class ArticleComparator implements Comparator<Article> {
			VoteType currentVoteType = null;

			ArticleComparator(VoteType voteType) {
				this.currentVoteType = voteType;
			}

			@Override
			public int compare(Article o1, Article o2) {
				if ((o1 == null || o1.getVoteCount() == null || o1.getVoteCount().get(this.currentVoteType) == null
						|| o1.getVoteCount().get(this.currentVoteType) == 0)
						&& (o2 == null || o2.getVoteCount() == null
								|| o2.getVoteCount().get(this.currentVoteType) == null
								|| o2.getVoteCount().get(this.currentVoteType) == 0)) {
					return 0;
				} else if (o1 == null || o1.getVoteCount() == null
						|| o1.getVoteCount().get(this.currentVoteType) == null
						|| o1.getVoteCount().get(this.currentVoteType) == 0) {
					return -1;
				} else if (o2 == null || o2.getVoteCount() == null
						|| o2.getVoteCount().get(this.currentVoteType) == null
						|| o2.getVoteCount().get(this.currentVoteType) == 0) {
					return 1;
				} else {
					return o1.getVoteCount().get(this.currentVoteType) - o2.getVoteCount().get(this.currentVoteType);
				}
			}

		}
		this.comparator = new ArticleComparator(this);
		this.populorArticles = new PriorityQueue<>(comparator);

	}
	public PriorityQueue<Article> getPopulorArticles() {
		return populorArticles;
	}

	public Comparator<Article> getComparator() {
		return comparator;
	}
}
