package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Paper implements Serializable {

	public String name;
	
	public Researcher author;
	public Reviewer reviewer;
	
	public List<Reviewer> reviewers;


	public Object researcher;

	public ArrayList<Review> reviews;
	
	public PaperStatus status;
	
	public Paper(String name) {
		this.name = name;
		this.reviewers = new ArrayList<Reviewer>();
		this.reviews = new ArrayList<Review>();
		
		this.status = PaperStatus.SUBMITTED;
	}

	public void add(Reviewer re1) {
		// TODO Auto-generated method stub
		
	}
	
	public void addReview(Review review) {
		this.reviews.add(review);
		review.paper = this;
	}
	
}
