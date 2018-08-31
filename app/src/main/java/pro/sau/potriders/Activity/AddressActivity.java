package pro.sau.potriders.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import pro.sau.potriders.R;

public class AddressActivity extends AppCompatActivity {
    String mBackStack;
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        initialization();

    }

    private void initialization() {
        toolbar = (Toolbar) findViewById(R.id.toolbarAddress);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {

            try {
                mBackStack = bundle.getString("Addtocart");
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AddressActivity.this, CheckoutActivity.class);
        intent.putExtra("Address", "1");
        intent.putExtra("Addtocart", mBackStack);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.zoom_exit, R.anim.zoom_enter);
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(AddressActivity.this, CheckoutActivity.class);
                intent.putExtra("Address", "1");
                intent.putExtra("Addtocart", mBackStack);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.zoom_exit, R.anim.zoom_enter);
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
