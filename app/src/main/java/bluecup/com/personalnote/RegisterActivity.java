package bluecup.com.personalnote;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class RegisterActivity extends ActionBarActivity {


    protected EditText mUsername;
    protected EditText mUserPassword;
    protected EditText mUserEmail;
    protected Button mRegisterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "YFeIibITvb9xarAQNB5gDuEVJpROfrnzVAqA4y2M", "AAF1VKH9U4LWaqUNhFasQARDnhUrboOMslJuKK1V");

        //initialize
        mUsername = (EditText) findViewById(R.id.usernameRegisterEditText);
        mUserEmail = (EditText) findViewById(R.id.emailRegisterEditText);
        mUserPassword = (EditText) findViewById(R.id.passwordRegisterEditText);
        mRegisterButton = (Button) findViewById(R.id.registerButton);

        // listen to the register button click
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //get the user name, email and password then convert it to string

                //.trim() delete the extra space
                String username = mUsername.getText().toString().trim();
                String email = mUserEmail.getText().toString().trim();
                String password = mUserPassword.getText().toString().trim();

                //Bug! can not resolve the empty input. Cause force quit
                /*
                if(null == username){
                    Toast.makeText(RegisterActivity.this, "You must enter the Username!", Toast.LENGTH_LONG).show();

                }else {

                }
                */
                    //store user to Parse
                    ParseUser user = new ParseUser();
                    user.setUsername(username);
                    user.setPassword(password);
                    user.setEmail(email);


                    user.signUpInBackground(new SignUpCallback() {

                        public void done(ParseException e) {
                            if (e == null) {
                                // Hooray! Let them use the app now.
                                // toast
                                Toast.makeText(RegisterActivity.this, "Sign up successful!", Toast.LENGTH_LONG).show();

                                //take the user to homepage
                                Intent takeUserHome = new Intent(RegisterActivity.this, HomeActivity.class);
                                startActivity(takeUserHome);

                            } else {
                                // Sign up didn't succeed. Look at the ParseException
                                // to figure out what went wrong
                                Toast.makeText(RegisterActivity.this, "Check the format or something else", Toast.LENGTH_LONG).show();

                            }
                        }
                    });




            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
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
