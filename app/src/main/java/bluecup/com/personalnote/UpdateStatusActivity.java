package bluecup.com.personalnote;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;


public class UpdateStatusActivity extends ActionBarActivity {

    protected EditText mStatusUpdate;
    protected Button mStatusUpdateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_status);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

        //Initialize
        mStatusUpdate = (EditText) findViewById(R.id.updateStatusEditText);
        mStatusUpdateButton = (Button) findViewById(R.id.updateStatusButton);

        //listen to the status update button click
        mStatusUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get the current user
                ParseUser currentUser = ParseUser.getCurrentUser();
                String currentUserStr = currentUser.getUsername();

                //Get the user status enteered data, then convert it to a string
                String newStatus = mStatusUpdate.getText().toString();

                //save to update to Parse
                ParseObject statusObj = new ParseObject("Status");

                statusObj.put("user",currentUserStr);
                statusObj.put("postedStatus",newStatus);

                statusObj.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (null == e){
                            //successfully stored new status in parse
                            Toast.makeText(UpdateStatusActivity.this,"Success!", Toast.LENGTH_LONG).show();

                            //Take user to home
                            Intent takeUserToHomeIntent = new Intent(UpdateStatusActivity.this,HomeActivity.class);
                            startActivity(takeUserToHomeIntent);

                        }else {
                            //there was a problem to save the status
                            AlertDialog.Builder builder = new AlertDialog.Builder(UpdateStatusActivity.this);
                            builder.setMessage(e.getMessage());
                            builder.setTitle("Sorry");
                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // close the dialog
                                    dialog.dismiss();
                                }
                            });
                            // show dialog
                            AlertDialog dialog = builder.create();
                            dialog.show();

                        }
                    }
                });
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_update_status, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



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
            View rootView = inflater.inflate(R.layout.fragment_update_status, container, false);
            return rootView;
        }
    }
}
