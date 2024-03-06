package dto;

import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Film {
    private String title;
    private int episode_id;
    private String opening_crawl;
    private String director;
    private String producer;
    private String release_date;
    ArrayList< Object > characters = new ArrayList < Object > ();
    ArrayList < Object > planets = new ArrayList < Object > ();
    ArrayList < String > starships = new ArrayList <> ();
    ArrayList < Object > vehicles = new ArrayList < Object > ();
    ArrayList < Object > species = new ArrayList < Object > ();
    private String created;
    private String edited;
    private String url;
}
