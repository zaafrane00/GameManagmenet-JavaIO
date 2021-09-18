/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamesmanagement;

/**
 *
 * @author zaafr
 */
public class Game {
    
    private String id;
    private String title;
    private String genre;

    public Game(String id, String title, String genre) {
        this.id = id;
        this.title = title;
        this.genre = genre;
    }

    public Game() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Game{" + "id=" + id + ", title=" + title + ", genre=" + genre + '}';
    }

     
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Game other = (Game) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
}
