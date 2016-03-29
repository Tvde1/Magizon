package nl.broekhin.magizon;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.Calendar;

import nl.broekhin.magizon.layout.LoginFragment;
import nl.broekhin.magizon.layout.VandaagFragment;
import nl.broekhin.magizon.layout.dummy.DummyContent;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, refreshTijd(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setFragment(new VandaagFragment());


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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public String persoon;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        if (id == R.id.TimOptie) {
            DummyContent.setPersoon("Tim");
            DummyContent.refresh();
            setFragment(new VandaagFragment());
        } else if (id == R.id.TomOptie) {
            DummyContent.setPersoon("Tom");
            DummyContent.refresh();
            setFragment(new VandaagFragment());
        }

        return super.onOptionsItemSelected(item);
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        //Fragment fragment;


        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_vandaag) {
            setFragment(new VandaagFragment());
        } else if (id == R.id.nav_login) {
            setFragment(new LoginFragment());
        } else if (id == R.id.nav_instellingen) {
            Intent settings = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(settings);

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

    public void setFragment(Fragment fragment) {
        android.support.v4.app.FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.fragment_container, fragment);
        t.commit();
    }

    public String refreshTijd() {

        int berekenUren = 99;
        int berekenMinuten = 0;
        String bericht;
        String beep = "";

        Calendar c = Calendar.getInstance();
        int nuUren = c.get(Calendar.HOUR_OF_DAY);
        int nuMinuten = c.get(Calendar.MINUTE);

        int u = 60;
        //int i = 1;

        int nuTotaalMinuten = nuUren * u + nuMinuten;

        int lesMinuten[] = {8*u + 30, 9 * u + 20, 10 * u + 10, 11 * u, 11 * u + 20, 12 * u + 10, 13 * u, 13 * u + 30, 14 * u + 20, 15 * u + 10, 16 * u};


        for (int i=0; i < lesMinuten.length; i++) {

            Log.d("i", "i = " + i);

            if (lesMinuten[i] - nuTotaalMinuten >= 0) {

                if ((lesMinuten[i] - nuTotaalMinuten) >= 60) {

                    berekenUren = ((lesMinuten[i] - nuTotaalMinuten) / 60);
                    berekenMinuten = (lesMinuten[i] - nuTotaalMinuten) - berekenUren * 60;

                    Log.i("Uur", "Het is MEER dan 60 minuten.");


                } else {

                    berekenUren = 0;
                    berekenMinuten = lesMinuten[i] - nuTotaalMinuten;

                    Log.i("Uur", "Het is MINDER dan 60 minuten.");

                }

                Log.i("Getallen", "nuTotaalMinuten = " + nuTotaalMinuten + ". lesMinuten[i] = " + lesMinuten[i]);
                break;


            }
        }

        if (berekenUren == 99) {

            bericht = "Je hebt geen les meer vandaag.";

        } else if (berekenUren == 0 && berekenMinuten != 0) {

            if (berekenMinuten == 1) {

                bericht = "Nog " + berekenMinuten + " minuut tot de bel gaat.";

            } else {

                bericht = "Nog " + berekenMinuten + " minuten tot de bel gaat.";

            }
        } else if (berekenMinuten == 0 && berekenUren != 0) {

            bericht = "Nog " + berekenUren + " uur tot de bel gaat.";

        } else if (berekenMinuten == 0 && berekenUren == 0) {

            bericht = "De bel gaat nu!";

        } else {

            bericht = "Nog " + berekenUren + " uur en " + berekenMinuten + " minuten tot de bel gaat.";

        }

        return bericht;

    }
}
