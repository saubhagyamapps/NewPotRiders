package pro.sau.potriders.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import pro.sau.potriders.R;

public class SignUp extends AppCompatActivity {
    Toolbar toolbar;
    TextView txtLoginLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initialization();
        overridePendingTransition(R.anim.windmill_enter, R.anim.windmill_exit);
    }

    private void initialization() {
        toolbar = (Toolbar) findViewById(R.id.toolbarSignUp);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        txtLoginLink=(TextView)findViewById(R.id.txtLoginLink);
        clickToLogin();
    }

    private void clickToLogin() {
        txtLoginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this, LogIn.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.windmill_exit, R.anim.windmill_enter);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(SignUp.this, LogIn.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.windmill_exit, R.anim.windmill_enter);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SignUp.this, LogIn.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.windmill_exit, R.anim.windmill_enter);
        super.onBackPressed();
    }
}
