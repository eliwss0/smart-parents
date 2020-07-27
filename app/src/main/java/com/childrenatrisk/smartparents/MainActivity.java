package com.childrenatrisk.smartparents;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity{

    AppBarConfiguration appBarConfiguration;
    DrawerLayout drawerLayout;
    NavController navController;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;

    String lang=Locale.getDefault().getLanguage();

//        TODO descriptions for resources?
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar_layout);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).setDrawerLayout(drawerLayout).build();
        NavigationUI.setupWithNavController(toolbar,navController,appBarConfiguration);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int id = item.getItemId();
                Fragment fragment=null;
                switch (id) {
                    case R.id.home_menu:
                        fragment=new HomeFragment();
                        break;
                    case R.id.health_nutrition_menu:
                        fragment=new HealthNutritionFragment();
                        break;
                    case R.id.k12_education_menu:
                        fragment=new K12EducationFragment();
                        break;
                    case R.id.child_safety_menu:
                        fragment=new ChildSafetyFragment();
                        break;
                    case R.id.ec_education_menu:
                        fragment=new ECEducationFragment();
                        break;
                    case R.id.hotline_menu:
                        fragment=new HotlineFragment();
                        break;
                    case R.id.notes_menu:
                        fragment=new NotesFragment();
                        break;
                    default:
                        break;
                }
                if (fragment != null) {
                    FrameLayout f1= findViewById(R.id.nav_host_fragment);
                    f1.removeAllViews();
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.add(R.id.nav_host_fragment,fragment);
                    ft.commit();
                }
                DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        Fragment fragment=null;
        switch (id) {
            case R.id.action_about:
                fragment=new AboutFragment();
                break;
            default:
                break;
        }
        if (fragment != null) {
            FrameLayout frameLayout= findViewById(R.id.nav_host_fragment);
            frameLayout.removeAllViews();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.nav_host_fragment,fragment);
            ft.commit();
        }
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        }
        else if (getSupportActionBar().getTitle()!="Home") {   //transition to home fragment
            FrameLayout f1= findViewById(R.id.nav_host_fragment);
            f1.removeAllViews();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.add(R.id.nav_host_fragment,new HomeFragment());
            ft.commit();
            getSupportActionBar().setTitle("Home");
        }
        else if (getSupportActionBar().getTitle()=="Home") {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setMessage(R.string.exit_dialog);
            builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        }
        else
            super.onBackPressed();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.dispatchTouchEvent(ev);
    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    //click functions for buttons in app
    //Home
    public void onClickCaRLogo(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.childrenatrisk.org"));
        startActivity(browserIntent);
    }
    public void onClickSmartParents(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://smartparents.org/"));
        startActivity(browserIntent);
    }

    //Health and Nutrition
    public void healthButton1Click(View view) {
        Intent openWebViewIntent=new Intent(MainActivity.this, WebViewActivity.class);  //"https://docs.google.com/gview?embedded=true&url=" opens pdf using google drive pdf viewer
        openWebViewIntent.putExtra("passedURL","https://protectingimmigrantfamilies.org/wp-content/uploads/2020/02/You-Have-Rights-Protect-Your-Health-Updated-February-2020-ENGLISH.pdf");
        startActivity(openWebViewIntent);
    }
    public void healthButton2Click(View view) {
        Intent openWebViewIntent=new Intent(MainActivity.this, WebViewActivity.class);
        openWebViewIntent.putExtra("passedURL","https://www.cdc.gov/parents/");
        startActivity(openWebViewIntent);
    }
    public void nutritionButton1Click(View view) {
        Intent openWebViewIntent=new Intent(MainActivity.this, WebViewActivity.class);
        openWebViewIntent.putExtra("passedURL", "https://www.feedingamerica.org/find-your-local-foodbank");
        startActivity(openWebViewIntent);
    }
    public void nutritionButton2Click(View view) {
        Intent openWebViewIntent=new Intent(MainActivity.this, WebViewActivity.class);
        openWebViewIntent.putExtra("passedURL", "https://schoolmealfinder.hoonuit.com/");
        startActivity(openWebViewIntent);
    }

    //Early Childhood Education
    public void ecEducationButton1Click(View view) {
        Intent openWebViewIntent=new Intent(MainActivity.this, WebViewActivity.class);
        openWebViewIntent.putExtra("passedURL","https://findchildcare.collabforchildren.org/");
        startActivity(openWebViewIntent);
    }
    public void ecEducationButton2Click(View view) {
        Intent openWebViewIntent=new Intent(MainActivity.this, WebViewActivity.class);
        openWebViewIntent.putExtra("passedURL", "https://www.childcareaware.org/");
        startActivity(openWebViewIntent);
    }
    public void ecEducationButton3Click(View view) {
        Intent openWebViewIntent=new Intent(MainActivity.this, WebViewActivity.class);
        openWebViewIntent.putExtra("passedURL", "https://find.frontlinechildcare.texas.gov/parent/dashboard");
        startActivity(openWebViewIntent);
    }
    public void ecEducationButton4Click(View view) {
        Intent openWebViewIntent=new Intent(MainActivity.this, WebViewActivity.class);
        openWebViewIntent.putExtra("passedURL", "https://childrenatrisk.org/childcaredesertmap/");
        startActivity(openWebViewIntent);
    }
    public void ecEducationButton5Click(View view) {    //spanish version?
        Intent openWebViewIntent=new Intent(MainActivity.this, WebViewActivity.class);
        openWebViewIntent.putExtra("passedURL", "https://texasschoolguide.org/content/uploads/2018/01/TSG-ChildCareChecklist.pdf");
        startActivity(openWebViewIntent);
    }

    //K-12 Education
    public void k12EducationButton1Click(View view) {
        Intent openWebViewIntent=new Intent(MainActivity.this, WebViewActivity.class);
        if (lang.substring(0,2).equals("es"))
            openWebViewIntent.putExtra("passedURL","https://guiadelasescuelas.org/");
        else
            openWebViewIntent.putExtra("passedURL","https://texasschoolguide.org");
        startActivity(openWebViewIntent);
    }
    public void k12EducationButton2Click(View view) {
        Intent openWebViewIntent=new Intent(MainActivity.this, WebViewActivity.class);
        openWebViewIntent.putExtra("passedURL","http://live.tsg.gfolkdev.net/content/uploads/2017/09/Questions-to-Ask-When-Visiting-a-School.pdf");
        startActivity(openWebViewIntent);
    }
    public void k12EducationButton3Click(View view) {
        Intent openWebViewIntent=new Intent(MainActivity.this, WebViewActivity.class);
        if (lang.substring(0,2).equals("es"))
            openWebViewIntent.putExtra("passedURL","https://catriskprod.wpengine.com/wp-content/uploads/2019/04/Gold-Ribbon-Blueprint_Spanish_Final.pdf");
        else
            openWebViewIntent.putExtra("passedURL","https://catriskprod.wpengine.com/wp-content/uploads/2019/04/Gold-Ribbon-Blueprint_English_Final.pdf");
        startActivity(openWebViewIntent);
    }
    public void k12EducationButton4Click(View view) {
        Intent openWebViewIntent=new Intent(MainActivity.this, WebViewActivity.class);
        openWebViewIntent.putExtra("passedURL","http://live.tsg.gfolkdev.net/content/uploads/2017/08/College-Ready-Checklist.pdf");
        startActivity(openWebViewIntent);
    }
    public void k12EducationButton5Click(View view) {
        Intent openWebViewIntent=new Intent(MainActivity.this, WebViewActivity.class);
        openWebViewIntent.putExtra("passedURL","https://childrenatrisk.org/pag18-19/");
        startActivity(openWebViewIntent);
    }

    //Child Safety
    public void childSafetyButton1Click(View view) {
        Intent openWebViewIntent=new Intent(MainActivity.this, WebViewActivity.class);
        openWebViewIntent.putExtra("passedURL","https://love146.org/action/online-safety/");
        startActivity(openWebViewIntent);
    }
    public void childSafetyButton2Click(View view) {
        Intent openWebViewIntent=new Intent(MainActivity.this, WebViewActivity.class);
        openWebViewIntent.putExtra("passedURL","https://www.usfa.fema.gov/prevention/outreach/children.html");
        startActivity(openWebViewIntent);
    }
    public void childSafetyButton3Click(View view) {
        Intent openWebViewIntent=new Intent(MainActivity.this, WebViewActivity.class);
        openWebViewIntent.putExtra("passedURL","https://www.redcross.org/get-help/how-to-prepare-for-emergencies/types-of-emergencies/water-safety.html");
        startActivity(openWebViewIntent);
    }
}