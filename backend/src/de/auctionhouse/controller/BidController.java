package de.auctionhouse.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.auctionhouse.model.Bid;

public class BidController {
	private static BidController sharedBidController;
	
	private BidController() {
	}
	
	// Singleton
	public static BidController sharedInstance() {
		if (sharedBidController == null) {
			sharedBidController = new BidController();
		}
		return sharedBidController;
	}
	
	public Bid findLastByArticleId(int _articleId) throws SQLException {
		
		Statement stmt = ConnectionController.sharedInstance().newStatement();
		
		ResultSet rs = stmt.executeQuery("SELECT b.id, b.bidder_id AS user_id, b.article_id AS article_id, b.bid, b.bid_date, u.first_name, u.last_name " +
											"FROM Bid AS b " +
											"LEFT JOIN Users AS u ON b.bidder_id = u.id " +
											"LEFT JOIN Article AS a ON b.article_id = a.id " +
											"WHERE b.article_id = " + _articleId + " " +
											"ORDER by b.bid_date DESC " + 
											"LIMIT 1");

		if (rs.next()) {
			return new Bid(rs);	
		}
		
		return null;
	}

	public void placeBid(int _bidderId, int _articleId, int _bidValue) throws Exception {
		Statement stmt = ConnectionController.sharedInstance().newStatement();
		
		stmt.executeUpdate("INSERT INTO Bid(id, bidder_id, article_id, bid, bid_date) " +
							"VALUES (DEFAULT, " + _bidderId + "," + _articleId + "," + _bidValue + "," + "DEFAULT)");
	}
	
}
