package pro.sau.potriders.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import pro.sau.potriders.R;

public class ViewCartActivity extends AppCompatActivity {
    Spinner spinner;
    String[] country = {"1G", "2G", "1/8", "1/4", "1/2", "OZ"};
    String mBackStack;
    ImageView imgBackArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cart);
        overridePendingTransition(R.anim.card_enter, R.anim.card_exit);
        inittilaztion();
    }

    private void inittilaztion() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            mBackStack = bundle.getString("Addtocart");
        }
        spinner = (Spinner) findViewById(R.id.spinnerItemList);
        imgBackArrow=(ImageView)findViewById(R.id.imgBackArrow);
        setDataSpinner();
        backArrowClick();
    }

    private void backArrowClick() {
        imgBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mBackStack.equals("weed")) {
                    Intent intent = new Intent(ViewCartActivity.this, ScrollingActivityAddToCartWeed.class);
                    startActivity(intent);
                    finish();
                    overridePendingTransition(R.anim.card_enter, R.anim.card_exit);
                } else {
                    Intent intent = new Intent(ViewCartActivity.this, ScrollingActivityAddToCart.class);
                    startActivity(intent);
                    finish();
                    overridePendingTransition(R.anim.card_enter, R.anim.card_exit);
                }
            }
        });
    }

    private void setDataSpinner() {
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, country);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa);
    }

    @Override
    public void onBackPressed() {
        if (mBackStack.equals("weed")) {
            Intent intent = new Intent(ViewCartActivity.this, ScrollingActivityAddToCartWeed.class);
            startActivity(intent);
            finish();
            overridePendingTransition(R.anim.card_enter, R.anim.card_exit);
        } else {
            Intent intent = new Intent(ViewCartActivity.this, ScrollingActivityAddToCart.class);
            startActivity(intent);
            finish();
            overridePendingTransition(R.anim.card_enter, R.anim.card_exit);
        }

        super.onBackPressed();
    }
}
