package pro.sau.potriders.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import pro.sau.potriders.R;

public class ForgotPassword extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        overridePendingTransition(R.anim.windmill_enter, R.anim.windmill_exit);
        initialization();
    }

    private void initialization() {
        toolbar = (Toolbar) findViewById(R.id.toolbarForgotPwd);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(ForgotPassword.this, LogIn.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.windmill_exit, R.anim.windmill_enter);
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ForgotPassword.this, LogIn.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.windmill_exit, R.anim.windmill_enter);
        super.onBackPressed();
    }
}
