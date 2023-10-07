# Best matched restaurants

This application allows users to search for restaurants based on various criteria such as restaurant name, customer rating, distance, price, and cuisine. The search criteria are used to filter and sort the results to provide users with a list of matching restaurants.

## Data Initialization

All the data used by this application is loaded during the initialization process from two CSV files located in the `resources/input` folder:

- **cuisines.csv:** This file contains a list of possible cuisines that restaurants can offer.

- **restaurants.csv:** This file contains restaurant information, including name, customer rating, distance, price, and cuisine details.

During the application's startup, it reads and processes these CSV files to populate the internal data structures used for restaurant searches. This ensures that the search results are based on the most up-to-date restaurant data.


## Search Criteria

The following search criteria are used to find matching restaurants:

1. **Restaurant Name Match**: A restaurant name match is defined as an exact or partial string match with what users provide. For example, searching for "Mcd" would match "Mcdonald's."
1. **Customer Rating Match:** A customer rating match is defined as a customer rating equal to or more than what users have asked for. For example, searching for "3" would match all the 3-star restaurants, as well as 4-star and 5-star restaurants.
1. **Distance Match:** A distance match is defined as a distance equal to or less than what users have asked for. For example, searching for "2" would match any restaurant within 2 miles from the user's location.
1. **Price Match:** A price match is defined as a price equal to or less than what users have asked for. For example, searching for "15" would match any restaurant with a price equal to or less than $15 per person.
1. **Cuisine Match:** A cuisine match is defined as an exact or partial string match with what users provide. For example, searching for "Chi" would match "Chinese" cuisine. Each restaurant is assumed to offer only one cuisine. The list of possible cuisines can be found in the cuisines.csv file.


## Search Logic

The search criteria parameters have an "AND" relationship. For example, if users provide both the restaurant name "Mcdonald's" and a distance of "2 miles," the application will find all "Mcdonald's" restaurants within 2 miles.

Multiple matches are sorted as follows:
1. Sort the restaurants by distance first.
1. If two matches are still equal, then the restaurant with a higher customer rating wins.
1. If two matches are still equal, then the restaurant with a lower price wins.
1. If two matches are still equal, the order is decided randomly.

## Getting Started

To use this application, follow these steps:

1. **Clone the Repository:** Clone this repository to your local machine.
1. **Build and Run the Application:** Use Gradle to build the application and run it locally.
1. **Access the API:** The application exposes an API endpoint for restaurant searches (http://localhost:8080/api/search).
   You can access it using a tool like curl or through a web browser. Open API documentation is also available at http://localhost:8080/swagger-ui/index.html.
1. **Provide Search Criteria:** When making a search request, provide the desired search criteria as query parameters. You can specify criteria such as "name," "rating," "distance," "price," and "cuisine."
   Receive Search Results: The application will return a list of restaurants that match your criteria, sorted according to the specified rules.
1. **Enjoy Your Dining Experience:** Explore the search results and find the perfect restaurant for your dining experience.

## Technologies Used

Spring Boot: The application is built using the Spring Boot framework, making it easy to develop and deploy RESTful web services.
Java: The core logic of the application is written in Java (version 17).
Gradle: Dependency management and build tools used for the project.
Swagger UI: Swagger UI is integrated to provide interactive API documentation.

## Contributing
Contributions to this project are welcome. If you find any issues or have suggestions for improvements, please feel free to create a GitHub issue or submit a pull request.
