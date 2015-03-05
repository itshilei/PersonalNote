package bluecup.com.personalnote;

import android.app.Application;

import com.parse.Parse;


public class ParseApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize Crash Reporting.
        //ParseCrashReporting.enable(this);

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "YFeIibITvb9xarAQNB5gDuEVJpROfrnzVAqA4y2M", "AAF1VKH9U4LWaqUNhFasQARDnhUrboOMslJuKK1V");



    }



}
