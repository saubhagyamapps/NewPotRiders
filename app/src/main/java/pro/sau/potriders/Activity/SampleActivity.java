package pro.sau.potriders.Activity;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import java.util.Arrays;

import pro.sau.potriders.Fragment.HomeFragment;
import pro.sau.potriders.Fragment.MyOrdersFragment;
import pro.sau.potriders.Fragment.SettingsFragment;
import pro.sau.potriders.R;
import pro.sau.potriders.menu.DrawerAdapter;
import pro.sau.potriders.menu.DrawerItem;
import pro.sau.potriders.menu.SimpleItem;


public class SampleActivity extends AppCompatActivity implements DrawerAdapter.OnItemSelectedListener {

    private static final int HOME = 0;
    private static final int MY_ORDER = 1;
    private static final int SETTING = 2;
    private static final int LOGOUT = 3;
    private static final int POS_LOGOUT = 5;
    private static final String TAG = "SampleActivity";
    LinearLayout linearLayout, linearLayout1;
    Toolbar toolbar;
    private String[] screenTitles;
    private Drawable[] screenIcons;
    private SlidingRootNav slidingRootNav;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        overridePendingTransition(R.anim.windmill_enter, R.anim.windmill_exit);
        initilization(savedInstanceState);


    }

    private void initilization(Bundle savedInstanceState) {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.call_icon);


        slidingRootNav = new SlidingRootNavBuilder(this)
                .withToolbarMenuToggle(toolbar)
                .withMenuOpened(false)
                .withContentClickableWhenMenuOpened(true)
                .withSavedState(savedInstanceState)
                .withMenuLayout(R.layout.menu_left_drawer)
                .inject();

        screenIcons = loadScreenIcons();
        screenTitles = loadScreenTitles();

        DrawerAdapter adapter = new DrawerAdapter(Arrays.asList(
                createItemFor(HOME).setChecked(true),
                createItemFor(MY_ORDER),
                createItemFor(SETTING),
                createItemFor(LOGOUT)));
        adapter.setListener(this);

        RecyclerView list = findViewById(R.id.list);
        list.setNestedScrollingEnabled(false);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);

        adapter.setSelected(HOME);

        ViewProfileClick();
    }


    private void ViewProfileClick() {

        this.findViewById(R.id.txtViewProfile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SampleActivity.this, ViewProfile.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.split_exit, R.anim.split_enter);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                slidingRootNav.closeMenu();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onItemSelected(int position) {

        android.support.v4.app.Fragment fragment = new HomeFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
        if (position == 0) {
            android.support.v4.app.Fragment fragment1 = new HomeFragment();
            FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
            fragmentTransaction1.replace(R.id.container, fragment1);
            fragmentTransaction1.commit();
            slidingRootNav.closeMenu();


        } else if (position == 1) {
            android.support.v4.app.Fragment fragment1 = new MyOrdersFragment();
            FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
            fragmentTransaction1.replace(R.id.container, fragment1);
            fragmentTransaction1.commit();
            slidingRootNav.closeMenu();

        } else if (position == 2) {
            android.support.v4.app.Fragment fragment1 = new SettingsFragment();
            FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
            fragmentTransaction1.replace(R.id.container, fragment1);
            fragmentTransaction1.commit();
            slidingRootNav.closeMenu();

        } else if (position == 3) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(R.drawable.logout_icon);

            builder.setTitle("Logout");

            //Setting message manually and performing action on button click
            builder.setMessage("Are you sure you want to logout?");
            //This will not allow to close dialogbox until user selects an option
            builder.setCancelable(false);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                    finish();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });


            final AlertDialog alert = builder.create();
            alert.setOnShowListener(new DialogInterface.OnShowListener() {
                @SuppressLint("ResourceAsColor")
                @Override
                public void onShow(DialogInterface arg0) {
                    alert.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(R.color.colorPrimaryDark);
                    alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(R.color.colorPrimaryDark);
                }
            });

            alert.show();
            slidingRootNav.closeMenu();
        }


       /* Fragment selectedScreen = CenteredTextFragment.createFor(screenTitles[position]);
        showFragment(selectedScreen);*/
    }

    private void showFragment(Fragment fragment) {

        getFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    private DrawerItem createItemFor(int position) {
        return new SimpleItem(screenIcons[position], screenTitles[position])
                .withIconTint(color(R.color.iconmenucolor))
                .withTextTint(color(R.color.textmenucolor))
                .withSelectedIconTint(color(R.color.iconmenucolor))
                .withSelectedTextTint(color(R.color.textmenucolor));
    }

    private String[] loadScreenTitles() {
        return getResources().getStringArray(R.array.ld_activityScreenTitles);
    }

    private Drawable[] loadScreenIcons() {
        TypedArray ta = getResources().obtainTypedArray(R.array.ld_activityScreenIcons);
        Drawable[] icons = new Drawable[ta.length()];
        for (int i = 0; i < ta.length(); i++) {
            int id = ta.getResourceId(i, 0);
            if (id != 0) {
                icons[i] = ContextCompat.getDrawable(this, id);
            }
        }
        ta.recycle();
        return icons;
    }

    @ColorInt
    private int color(@ColorRes int res) {

        return ContextCompat.getColor(this, res);
    }
}
