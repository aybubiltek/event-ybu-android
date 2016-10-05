package tr.edu.ybu.eventandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DuyuruActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ArrayList<Object> arrayOfUsers;
        final RecyclerView lv;
        MyComplexAdapter adapter;
        ExpandableListAdapter listAdapter;
        ExpandableListView expListView;


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duyuru);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        arrayOfUsers = getSampleArrayList();
        lv = (RecyclerView) findViewById(R.id.recyclerView);
        adapter = new MyComplexAdapter(arrayOfUsers, getApplicationContext());
        lv.setAdapter(adapter);
        lv.setLayoutManager(new LinearLayoutManager(this));
        lv.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                int id = view.getId();
                if (id == (-1)) {
                    //Toast.makeText(getApplicationContext(),"etkinlik",Toast.LENGTH_LONG).show();
                    Intent i = new Intent(DuyuruActivity.this, EventDetailActivity.class);
                    startActivity(i);
                } else if (id == (2131558549)) {
                    //  Toast.makeText(getApplicationContext(),"duyuru",Toast.LENGTH_LONG).show();

                } else
                    Toast.makeText(getApplicationContext(), id + "  " + lv.getChildAdapterPosition(view), Toast.LENGTH_LONG).show();
            }
        }));

        ////??????????????????////////
        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);


        // Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(
                        getApplicationContext(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });

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
        getMenuInflater().inflate(R.menu.duyuru, menu);
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
        int id = item.getItemId();

        if (id == R.id.duyurular) {

        } else if (id == R.id.etkinlikler) {

        } else if (id == R.id.kulupler) {
            Intent intent = new Intent(DuyuruActivity.this, KulupListActivity.class);
            startActivity(intent);
        } else if (id == R.id.ayarlar) {

        } else if (id == R.id.cıkıs) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private ArrayList<Object> getSampleArrayList() {
        ArrayList<Object> items = new ArrayList<>();
        items.add(new Duyuru("Duyuru", "Ayrıntı"));
        items.add(new Etkinlik("Etkinlik", "Ayrıntı"));
        items.add(new Duyuru("Duyuru", "Ayrıntı"));
        items.add(new Etkinlik("Etkinlik", "Ayrıntı"));
        items.add(new Duyuru("Duyuru", "Ayrıntı"));
        items.add(new Etkinlik("Etkinlik", "Ayrıntı"));


        return items;
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("YEMEK LİSTESİ");


        // Adding child data
        List<String> yemeklistesi = new ArrayList<String>();
        yemeklistesi.add("Mercimek Çorbası");
        yemeklistesi.add("Bezelye/Pilav");
        yemeklistesi.add("Yoğurt");


        listDataChild.put(listDataHeader.get(0), yemeklistesi); // Header, Child data

    }
}
