package com.test.alex.popularmovies.ui.main;


import com.test.alex.popularmovies.di.AppComponent;
import com.test.alex.popularmovies.di.CustomScope;

import dagger.Component;


@CustomScope
@Component(dependencies = AppComponent.class, modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
