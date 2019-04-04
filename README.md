//------News Scrapper Service----//

Problem statement:
1. Scrap newspaper articles data from https://www.thehindu.com/archive/
2. Create a REST service which answers following queries from scrap data:
	a. Search available authors
	b. Search articles based on author name
	c. Search articles based on article title and description

As per the above requirement, SpringBoot, "newscrapper" project is designed & developed.
Design:
5 API End points are created to access the data,  default IP & port http://localhost:8080/api, 
following are the APIs which return response in JSON format

	  i) Load Meta Data : /metaData
		 Will load the Authors & Articles list to avoid the overhead/waiting time for the user
		 Note that metaData must be the initial API call to access the data, failing which may not give desired resultSet
	 
	 ii) Retrive Authors List: /authors
		 Authors List with Author Name & User Name of the author, can be accessed using this API 
		 
	iii) Author respective Articles List: /authors/{userName}/articles
		 Based on the Author's - UserName, 
		 Authors List with Author Name & User Name of the author, can be accessed using this API 
		 
	 iv) All Articles List: /articles
		 List of Articles with Title, Description & URL link the article, can be accessed using this API 
	 
	  v) Article Search Based on Article Title: /articles/{article}
		 Based on the List of Articles with Title, Description & URL to access the article

Architecture/Development:
1) Spring Boot Application with Web Dependency along with Srapper API 
	--> JSOUP is integrated in the current project, 

	//JSOUP Dependency
	<dependency>
		<groupId>org.jsoup</groupId>
		<artifactId>jsoup</artifactId>
		<version>1.11.3</version>
	</dependency>
2) Project consists of Controller, DAO - Data Access Object, DTO - Data Transfer Object	
	NewsscraperApplication
	NewsScrapperController
	NewsScrapperDao
	ArticleDTO, AuthorDTO
		
3) NewsscraperApplication - is the Initializer class, contains main method	
4) ArticleDTO, AuthorDTO has Author, Article JSON reponse format
5) NewsScrapperController has the API mapping, which uses NewsScrapperDao to fetch data, 
   NewsScrapperDao inturn using the DTOs sends the data as per the API call
		 

Steps to setup the project.
1) Clone/Downlaod the project using the gitHub URL: 
2) Clean & Build the project as it'll setup the required depencies for the project
3) Run the application as Spro 
Build & run as Soring Boot

Example:
1) MetaData --> http://localhost:8080/api/metaData loads the MetaData returns "OK" as reponse for Success. 
2) AuthorsList --> http://localhost:8080/api/authors
{
        "name": "Arun S",
        "userName": "Arun-S-872"
}

3) Article for "Arun S", http://localhost:8080/api/authors/Arun-S-872/articles

[
    { 
        "title": "Firms from four nations keen on DMIC",
        "description": "Defence among sectors on Delhi-Mumbai corridor attracting Canada, U.S., Singapore and Taiwan",
        "url": null
    },
    {
        "title": "Easier norms may help Indian firms go global",
        "description": "The proposed Outward Direct Investment policy could, however, tighten provisions to prevent round-tripping",
        "url": null
    },
    {
        "title": "‘U.S. tax cuts forcing others to respond’",
        "description": "Concerned that the lower U.S. tax rate may lead to a slowdown in investment into India: USISPF’s Mukesh Aghi.",
        "url": null
    }
]

4)ArticlesList: http://localhost:8080/api/articles/

    {
        "title": "Overeating super foods can harm health: Study",
        "description": null,
        "url": "https://www.thehindu.com/sci-tech/health/diet-and-nutrition/Overeating-super-foods-can-harm-health-Study/article16811977.ece"
    }
	
5)Article with Title like "Experiment",
    {
        "title": "Experiment takes aim at genetic learning disorder",
        "description": null,
        "url": "https://www.thehindu.com/sci-tech/health/rx/Experiment-takes-aim-at-genetic-learning-disorder/article16811971.ece"
    }	