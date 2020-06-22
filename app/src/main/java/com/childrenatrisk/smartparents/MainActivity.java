//

package com.childrenatrisk.smartparents;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.navigation.NavigationView;

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

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity{

    AppBarConfiguration appBarConfiguration;

    DrawerLayout drawer;
    NavController navController;
    NavigationView navigationView;
    CollapsingToolbarLayout collapsingToolbar;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;

//    TODO resize icons
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawer = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar_layout);
        collapsingToolbar = findViewById(R.id.collapsing_toolbar_layout);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).setDrawerLayout(drawer).build();
        NavigationUI.setupWithNavController(collapsingToolbar,toolbar,navController,appBarConfiguration);
        setSupportActionBar(toolbar);

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
                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        switch (item.getItemId()) {
            default:
                return NavigationUI.onNavDestinationSelected(item, navController)
                        || super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp(); //Crashing when navigating back
    }
    @Override
    public void onBackPressed() {
        //TODO confirm exit
        super.onBackPressed();
    }
    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    //onClick goes by activity, not fragments. Still needs to be in fragment though
    public void onClickCaRLogo(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.childrenatrisk.org"));
        startActivity(browserIntent);
    }

    public void healthButton1Click(View view) {
        Intent openWebViewIntent=new Intent(MainActivity.this, WebViewActivity.class);
        openWebViewIntent.putExtra("passedURL","https://docs.google.com/gview?embedded=true&url=https://protectingimmigrantfamilies.org/wp-content/uploads/2020/02/You-Have-Rights-Protect-Your-Health-Updated-February-2020-ENGLISH.pdf");
        startActivity(openWebViewIntent);
    }

    public void parentingButton1Click(View view) {
        Intent openWebViewIntent=new Intent(MainActivity.this, WebViewActivity.class);
        openWebViewIntent.putExtra("passedURL","https://findchildcare.collabforchildren.org/");
        startActivity(openWebViewIntent);
    }
}