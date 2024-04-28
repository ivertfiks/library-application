package com.todoapp.entity.enums;

public enum BookEnum {
    FANTASY("Fantasy"),
    SCIENCE_FICTION("Science Fiction"),
    MYSTERY("Mystery"),
    ROMANCE("Romance"),
    HORROR("Horror"),
    THRILLER("Thriller"),
    HISTORICAL_FICTION("Historical Fiction"),
    BIOGRAPHY("Biography"),
    AUTOBIOGRAPHY("Autobiography"),
    NON_FICTION("Non-Fiction"),
    YOUNG_ADULT("Young Adult"),
    CHILDRENS("Children's"),
    POETRY("Poetry"),
    DRAMA("Drama"),
    COMEDY("Comedy"),
    CRIME("Crime"),
    ADVENTURE("Adventure");

    private final String genreName;

    BookEnum(String genreName) {
        this.genreName = genreName;
    }

    public String getGenreName() {
        return genreName;
    }
}
