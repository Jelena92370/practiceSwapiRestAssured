package tests;

import dto.Film;
import dto.Person;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.*;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tests.BaseTest.getRequest;

public class SearchTest {

    @Test
    public void getViderInfo() {

        List<Person> people = getRequest("/people/?search=Vader", 200)
                .body().jsonPath().getList("results", Person.class);

        people.forEach(person -> assertTrue(person.getName().contains("Vader")));

        List<String> filmUrls = new ArrayList<>();
        people.forEach(person -> filmUrls.addAll(person.getFilms()));

        String filmWithLeastPlanets = null;
        int leastPlanetsCount = Integer.MAX_VALUE;

        for (String filmUrl : filmUrls) {
            Response filmResponse = getRequest(filmUrl, 200);
            Film film = filmResponse.getBody().as(Film.class);
            int planetCount = film.getPlanets().size();

            if (planetCount < leastPlanetsCount) {
                leastPlanetsCount = planetCount;
                filmWithLeastPlanets = film.getTitle();


        }


        System.out.println("Фильм с наименьшим количеством планет: " + filmWithLeastPlanets);
        System.out.println("Количество планет в этом фильме: " + leastPlanetsCount);
    }
        }

    @Test
    public void verifyVaderStarshipInFilmWithId1() {

        List<Person> people = getRequest("/people/?search=Vader", 200)
                .body().jsonPath().getList("results", Person.class);


        Response filmResponse = getRequest("/films/1", 200);
        Film film = filmResponse.getBody().as(Film.class);
        List<String> starshipsInFilm = film.getStarships();


        List<String> vaderStarships = new ArrayList<>();
        people.forEach(person -> vaderStarships.addAll(person.getStarships()));

        for (String starship : vaderStarships) {
            if (starshipsInFilm.contains(starship)) {
                System.out.println("Корабль Вайдера присутствует в фильме 1? Да");

        } else {System.out.println("Корабль Вайдера присутствует в фильме 1? Нет");
        }


        }

    }
    @Test
    public void verifyVaderStarshipInFilmWithLeastPlanets() {
        List<Person> people = getRequest("/people/?search=Vader", 200)
                .body().jsonPath().getList("results", Person.class);

        //people.forEach(person -> assertTrue(person.getName().contains("Vader")));

        List<String> filmUrls = new ArrayList<>();
        people.forEach(person -> filmUrls.addAll(person.getFilms()));

        String filmWithLeastPlanets = null;
        int leastPlanetFilmId = 0;
        int leastPlanetsCount = Integer.MAX_VALUE;
        String filmWithLeastPlanetsUrl = "";

        for (String filmUrl : filmUrls) {
            Response filmResponse = getRequest(filmUrl, 200);
            Film film = filmResponse.getBody().as(Film.class);
            int planetCount = film.getPlanets().size();

            if (planetCount < leastPlanetsCount) {
                leastPlanetsCount = planetCount;
                filmWithLeastPlanetsUrl = filmUrl;
                leastPlanetFilmId = film.getEpisode_id();


            }

            System.out.println("911");
            Response filmInfoLessPlanets = getRequest(filmWithLeastPlanetsUrl, 200);
            Film filmLessPlanets = filmInfoLessPlanets.getBody().as(Film.class);
            List<String> starshipsInFilm = filmLessPlanets.getStarships();


            List<String> vaderStarships = new ArrayList<>();
            people.forEach(person -> vaderStarships.addAll(person.getStarships()));

            for (String starship : vaderStarships) {
                if (starshipsInFilm.contains(starship)) {
                    System.out.println("Корабль Вайдера присутствует в фильме? Да");

                } else {System.out.println("Корабль Вайдера присутствует в фильме? Нет");
                }

                System.out.println("Ид фильма = " + leastPlanetFilmId);
            }
        }
    }
}



/*
for (Person person: people) {
            assertTrue(person.getName().contains("Vader"));
        }
 */

/*
Map film title, planet Quantity;
 List<String> films = people.get(0).getFilms();
                films.forEach(x-> System.out.println(x));

                Map<String, Integer> filmsPlanetsQuantity = new HashMap<>();
                for (String onefilm: films) {
                    Film filmInfo = getRequest(onefilm, 200)
                            .body().jsonPath().getObject("", Film.class);
                    filmsPlanetsQuantity.put(filmInfo.getTitle(), filmInfo.getPlanets().size());
            }
                String minFilmName
                        = (Collections.min(filmsPlanetsQuantity.entrySet(), Map.Entry.comparingByValue()).getKey());
                System.out.println(minFilmName);
                System.out.println(filmsPlanetsQuantity.get(minFilmName));
        }

        @Test
    public void verifyVaderStarshipInFilmWithId1() {
        Response vaderResponse = RestAssured.get("https://swapi.dev/api/people/?search=Vader");
        List<String> vaderStarshipUrl = vaderResponse.jsonPath().getList("results[0].starships");

        Response filmResponse = RestAssured.get("https://swapi.dev/api/films/1");
        List<String> filmStarships = filmResponse.jsonPath().getList("starships");
        boolean isVaderShipInFilmWithId1 = false;

        for (String vaderStarship: vaderStarshipUrl
             ) {
            if (filmStarships.contains(vaderStarship)) {
                isVaderShipInFilmWithId1 = true;
                break;
            }

        }

        String resultMessage = isVaderShipInFilmWithId1 ? "да" : "нет";
        System.out.println("Корабль Вейдера присутствует в фильме с ID 1: " + resultMessage);
    }@Test
    public void verifyVaderStarshipInFilmWithId1() {
        Response vaderResponse = RestAssured.get("https://swapi.dev/api/people/?search=Vader");
        List<String> vaderStarshipUrl = vaderResponse.jsonPath().getList("results[0].starships");

        Response filmResponse = RestAssured.get("https://swapi.dev/api/films/1");
        List<String> filmStarships = filmResponse.jsonPath().getList("starships");
        boolean isVaderShipInFilmWithId1 = false;

        for (String vaderStarship: vaderStarshipUrl
             ) {
            if (filmStarships.contains(vaderStarship)) {
                isVaderShipInFilmWithId1 = true;
                break;
            }

        }

        String resultMessage = isVaderShipInFilmWithId1 ? "да" : "нет";
        System.out.println("Корабль Вейдера присутствует в фильме с ID 1: " + resultMessage);
    }
 */



