package dotstar.picksforkicks.testActivities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import dotstar.picksforkicks.*;

public class testLogin extends GoogleSignIn {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_login);
    }

    @Override
    protected void setOnAuthStateListener() {
        // [START auth_state_listener]
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    //Run Activity
                    testActivity();
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    //Send back to signin

                }
            }
        };
        // [END auth_state_listener]
    }

    @Override
    public void onClick(View v) {

    }

    private void testActivity(){
        //Create textview of username
        TextView tv = new TextView(this);
        tv.setText(account.getDisplayName());

        //Add the new textview to layout
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.activity_test_login);
        rl.addView(tv);
    }
}
