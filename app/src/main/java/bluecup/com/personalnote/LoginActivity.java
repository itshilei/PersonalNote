package bluecup.com.personalnote;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends ActionBarActivity {

    protected EditText mUsername;
    protected EditText mPassword;
    protected Button mLoginButton;
    protected Button mCreateAccountButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //initialize variables
        mUsername = (EditText) findViewById(R.id.usernameLoginEditText);
        mPassword = (EditText) findViewById(R.id.passwordLoginEditText);
        mLoginButton = (Button) findViewById(R.id.loginButton);
        mCreateAccountButton = (Button) findViewById(R.id.createAccountLoginButton);


        // listen to when the mLoginButton is clicked
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get user input form the EditText and convert it to string
                String username = mUsername.getText().toString().trim();
                String password = mPassword.getText().toString().trim();




                // login with Parse
                ParseUser.logInInBackground(username, password, new LogInCallback() {
                    public void done(ParseUser user, ParseException e) {
                        if (user != null) {
                            // Hooray! The user is logged in.
                            Toast.makeText(LoginActivity.this, "Login successful!", Toast.LENGTH_LONG).show();

                            //take user to the homepage

                        } else {
                            // Signup failed. Look at the ParseException to see what happened.
                            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
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
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
}
