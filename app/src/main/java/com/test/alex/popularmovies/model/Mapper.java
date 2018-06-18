package com.test.alex.popularmovies.model;



public interface Mapper<From, To> {

    To map(From from);

}
