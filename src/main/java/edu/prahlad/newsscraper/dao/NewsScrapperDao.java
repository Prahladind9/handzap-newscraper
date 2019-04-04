package edu.prahlad.newsscraper.dao;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import edu.prahlad.newsscraper.dto.ArticleDTO;
import edu.prahlad.newsscraper.dto.AuthorDTO;

public class NewsScrapperDao {

	public static final Logger LOGGER = LoggerFactory.getLogger(NewsScrapperDao.class);
	private static List<AuthorDTO> authors = new ArrayList<>();
	private static List<ArticleDTO> articles = new ArrayList<>();

	public static HttpStatus getMetaData() {

		// Users MetaData

		for (char startsWith = 'A'; startsWith <= 'Z'; ++startsWith) {
			try {
				// fetch the document over HTTP
				Document doc = Jsoup.connect(
						"https://www.thehindu.com/profile/wf.fragment/9e3112ea-289c-42da-a0ff-0c85637ee95c?fldStartsWith="
								+ startsWith + "&elementwidth=760")
						.get();

				// User List
				Elements auth = doc.select("div.auther-section > span");

				for (int i = 0; i < auth.size(); i++) {

					AuthorDTO authorDto = new AuthorDTO();

					authorDto.setName(auth.stream().filter(t -> t.children().attr("href").contains("/author/"))
							.map(t -> t.text()).distinct().collect(Collectors.toList()).get(i));

					authorDto.setUserName(auth.stream().filter(t -> t.children().attr("href").contains("/author/"))
							.map(t -> t.children().attr("href")
									.substring(t.children().attr("href").indexOf("/author/") + 8).replace("/", ""))
							.distinct().collect(Collectors.toList()).get(i));

					// Adding the Authors List
					authors.add(authorDto);

				}

			} catch (IOException e) {
				LOGGER.error(e.getMessage());
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
			}

		}

		// Articles MetaData

		for (int year = 2009; year <= LocalDate.now().getYear(); year++) {
			for (int month = 1; month <= 12; month++) {
				for (int day = 1; day <= LocalDate.of(year, month, day).getMonthValue(); day++) {
					try {

						Document doc = Jsoup.connect("https://www.thehindu.com/archive/web/" + year + "/02/02/").get();

						Elements auth = doc.getElementsByAttributeValue("class", "archive-list");
						// section-list-heading

						for (Element element : auth) {

							ArticleDTO articleDto = new ArticleDTO();
							articleDto.setTitle(element.text());
							articleDto.setUrl(element.getAllElements().attr("href"));

							articles.add(articleDto);
						}

					} catch (IOException e) {
						LOGGER.error(e.getMessage());
					} catch (Exception e) {
						LOGGER.error(e.getMessage());
					}
				} // Day Loop Ends

			} // Month Loop Ends

		} // Year Loop Ends

		return HttpStatus.OK;
	}

	public static List<AuthorDTO> getAuthors() {

		return authors;

	}

	public static List<ArticleDTO> getArticleBasedOnAuthor(String userName) {

		List<ArticleDTO> articles = new ArrayList<>();

		try {
			// fetch the document over HTTP
			Document doc = Jsoup.connect("https://www.thehindu.com/profile/author/" + userName + "/").get();

			Elements auth = doc.getElementsByAttributeValue("class", "story-card-33-heading");
			Elements auth2 = doc.select("div.story-card-33-news > span");
			// section-list-heading

			for (int i = 0; i < auth.size(); i++) {

				ArticleDTO articleDto = new ArticleDTO();

				// Article - teaser-text
				articleDto.setTitle(auth.stream().map(t -> t.text()).distinct().collect(Collectors.toList()).get(i));

				// Article - description
				articleDto.setDescription(
						auth2.stream().map(t -> t.text()).distinct().collect(Collectors.toList()).get(i));

				articles.add(articleDto);
			}

		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}

		return articles.stream().collect(Collectors.toList());

	}

	public static List<ArticleDTO> getArticles() {

		return articles;

	}

	public static List<ArticleDTO> articlesBasedSearch(String articleTitle) {

		return articles.stream().filter(t -> t.getTitle().contains(articleTitle)).distinct()
				.collect(Collectors.toList())
				;

	}

	
}