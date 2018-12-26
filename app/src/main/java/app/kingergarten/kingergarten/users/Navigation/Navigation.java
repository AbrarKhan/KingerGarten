package app.kingergarten.kingergarten.users.Navigation;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Locale;

import app.kingergarten.kingergarten.R;

import app.kingergarten.kingergarten.users.Students_info.StudentFragment;
import app.kingergarten.kingergarten.admin.navigation_admin.Navigation_admin;
import app.kingergarten.kingergarten.admin.add_best_student.AddStudentBestFragment;
import app.kingergarten.kingergarten.admin.add_best_student.ArabicFragment;
import app.kingergarten.kingergarten.admin.add_best_student.EnglishFragment;
import app.kingergarten.kingergarten.admin.add_best_student.MathFragment;
import app.kingergarten.kingergarten.admin.add_best_student.QuranFragment;
import app.kingergarten.kingergarten.users.display_best_student.DisplayStudentBestFragment;
import app.kingergarten.kingergarten.users.homePages.Home;
import app.kingergarten.kingergarten.users.time_table.TimeFragment;
import app.kingergarten.kingergarten.utils.KindergartenBaseActivity;
import app.kingergarten.kingergarten.utils.KindergartenConstent;



public class Navigation extends KindergartenBaseActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        Home.OnFragmentInteractionListener,
        TimeFragment.OnFragmentInteractionListener,
        StudentFragment.OnFragmentInteractionListener,
        AddStudentBestFragment.OnFragmentInteractionListener,
        DisplayStudentBestFragment.OnFragmentInteractionListener,
        QuranFragment.OnFragmentInteractionListener,
        EnglishFragment.OnFragmentInteractionListener,
        ArabicFragment.OnFragmentInteractionListener,
        MathFragment.OnFragmentInteractionListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        Home homeFragment= Home.newInstance(null,null);
        fragmentTransaction.replace(R.id.main_container,homeFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        Fragment fragment=null;
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            fragment= Home.newInstance("HOME",null);

        } else if (id == R.id.nav_table) {
            fragment= TimeFragment.newInstance("TIME TABLE",null);

        } else if (id == R.id.nav_pill) {

        } else if (id == R.id.nav_sugg) {

        } else if (id == R.id.nav_lang) {
            Locale current = getResources().getConfiguration().locale;
            if (current.getLanguage().equalsIgnoreCase(KindergartenConstent.AR)) {
                changeLang(KindergartenConstent.EN);

            } else {
                changeLang(KindergartenConstent.AR);
            }
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        } else if (id == R.id.nav_contact) {

        } else if (id == R.id.nav_about) {
            Intent intent=new Intent(Navigation.this,Navigation_admin.class);
            startActivity(intent);
        }
        if(fragment !=null) {
            fragmentTransaction.replace(R.id.main_container, fragment);
            fragmentTransaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void back_click(View view) {
                        onBackPressed();
                    }

            @Override
            public void onFragmentInteraction(Uri uri) {

            }
        }
