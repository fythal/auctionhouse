package de.auctionhouse.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import de.auctionhouse.model.Article;

public class ArticleController {

	private static ArticleController sharedArticleController;
	
	private ArticleController() {
	}
	
	// Singleton
	public static ArticleController sharedInstance() throws SQLException {
		if (sharedArticleController == null) {
			sharedArticleController = new ArticleController();
		}
		return sharedArticleController;
	}
		
	public Article findById(int _id) throws SQLException {
		
		Statement stmt = ConnectionController.sharedInstance().newStatement();
		
		ResultSet rs = stmt.executeQuery("SELECT * FROM Article LEFT JOIN Users ON Article.seller_id = Users.id WHERE Article.id = " + _id);
		rs.next();
		
		
		/* ORGINAL
		ResultSet rs = stmt.executeQuery("SELECT * FROM article where id =" + _id);
		rs.next();
		*/
		
		return new Article(rs);	
	}
	
	public List<Article> findAll() throws SQLException {
		
		Statement stmt = ConnectionController.sharedInstance().newStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM article");
		
		List<Article> result =  new ArrayList<Article>();
		while(rs.next()) {
			result.add(new Article(rs));
		}
		
		return result;
	}
	
}
