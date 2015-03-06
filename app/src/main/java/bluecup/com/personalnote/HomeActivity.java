package bluecup.com.personalnote;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;


public class HomeActivity extends ListActivity {

    protected List<ParseObject> mStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            // do some query then display them
            ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Status");
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> statusObjArray, ParseException e) {

                    if(null == e){
                        //return result
                        mStatus = statusObjArray;

                        StatusAdapter adapter = new StatusAdapter(getListView().getContext(), mStatus);
                        setListAdapter(adapter);

                    }else {
                        // problem
                    }
                }
            });


        } else {
            // show the signup or login screen
            Intent takeUserToLogin = new Intent(this, LoginActivity.class);
            startActivity(takeUserToLogin);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // Handle different menu option
        switch (id){
            case R.id.updateStatus:
                //take user to update status activity
                Intent intent = new Intent(this, UpdateStatusActivity.class);
                startActivity(intent);
                break;
            case R.id.logoutUser:
                //logout the user
                ParseUser.logOut();

                //take user back to the login activity
                Intent takeUserToLoginIntent = new Intent(this,LoginActivity.class);
                startActivity(takeUserToLoginIntent);
                break;

        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_home, container, false);
            return rootView;
        }
    }
}
