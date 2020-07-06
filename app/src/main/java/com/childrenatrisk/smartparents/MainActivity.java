
package com.childrenatrisk.smartparents;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.ActionBarDrawerToggle;
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
import androidx.preference.PreferenceManager;

import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    AppBarConfiguration appBarConfiguration;

    DrawerLayout drawerLayout;
    NavController navController;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;

//    TODO resize icons
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

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
//                //Disable Texas specific resources if set to anywhere but Texas
//                SharedPreferences prefs=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//                if(prefs.getString("state","us_national").equals("tx")) {
//                    Button testbutton=(Button) findViewById(R.id.health_button1);
//                    if(testbutton!=null)
//                        testbutton.setClickable(false);
//                }
                int id = item.getItemId();
                Fragment fragment=null;
                switch (id) {   //Fragments should be activities, change if possible after feature complete
                    case R.id.home_menu:
                        fragment=new HomeFragment();
                        break;
                    case R.id.health_menu:
                        fragment=new HealthFragment();
                        break;
                    case R.id.nutrition_menu:
                        fragment=new NutritionFragment();
                        break;
                    case R.id.education_menu:
                        fragment=new EducationFragment();
                        break;
                    case R.id.child_safety_menu:
                        fragment=new ChildSafetyFragment();
                        break;
                    case R.id.parenting_menu:
                        fragment=new ParentingFragment();
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
            case R.id.action_settings:
                fragment=new SettingsFragment();
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
        //TODO confirm exit with toast
        boolean readyToExit=false;
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
            return;
        }
        if(getTitle()!="Home") {   //transition to home fragment
            FrameLayout f1= findViewById(R.id.nav_host_fragment);
            f1.removeAllViews();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.nav_host_fragment,new HomeFragment());
            ft.commit();
            getSupportActionBar().setTitle("Home");    //Change if home fragment title changes
            return;
        }
        if(!readyToExit) {
            Toast.makeText(getApplication(), "Press back again to exit", Toast.LENGTH_SHORT).show();
            readyToExit=true;

        }
        if(readyToExit) {

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

    //click functions for buttons in app TODO try to put functions in respective fragments? Passing view necessary
    //Home
    public void onClickCaRLogo(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.childrenatrisk.org"));
        startActivity(browserIntent);
    }

    public void onClickTest(View view) {
        SharedPreferences prefs=PreferenceManager.getDefaultSharedPreferences(this);
        Toast.makeText(getApplicationContext(),prefs.getString("state",""),Toast.LENGTH_SHORT).show();
        Snackbar.make(drawerLayout,prefs.getString("state",""),Snackbar.LENGTH_SHORT).show();
    }

    //Health
    public void healthButton1Click(View view) {
        Intent openWebViewIntent=new Intent(MainActivity.this, WebViewActivity.class);  //"https://docs.google.com/gview?embedded=true&url=" opens pdf using google drive pdf viewer
        openWebViewIntent.putExtra("passedURL","https://docs.google.com/gview?embedded=true&url=https://protectingimmigrantfamilies.org/wp-content/uploads/2020/02/You-Have-Rights-Protect-Your-Health-Updated-February-2020-ENGLISH.pdf");
        startActivity(openWebViewIntent);
    }

    //Nutrition
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

    //Education

    //Child Safety
    public void childSafetyButton1Click(View view) {
        Intent openWebViewIntent=new Intent(MainActivity.this, WebViewActivity.class);
        openWebViewIntent.putExtra("passedURL","https://love146.org/action/online-safety/");
        startActivity(openWebViewIntent);
    }

    //Parenting
    public void parentingButton1Click(View view) {
        Intent openWebViewIntent=new Intent(MainActivity.this, WebViewActivity.class);
        openWebViewIntent.putExtra("passedURL","https://findchildcare.collabforchildren.org/");
        startActivity(openWebViewIntent);
    }
    public void parentingButton2Click(View view) {
        Intent openWebViewIntent=new Intent(MainActivity.this, WebViewActivity.class);
        openWebViewIntent.putExtra("passedURL", "https://www.childcareaware.org/state/texas/");
        startActivity(openWebViewIntent);
    }
    public void parentingButton3Click(View view) {
        Intent openWebViewIntent=new Intent(MainActivity.this, WebViewActivity.class);
        openWebViewIntent.putExtra("passedURL", "https://find.frontlinechildcare.texas.gov/parent/dashboard");
        startActivity(openWebViewIntent);
    }
}