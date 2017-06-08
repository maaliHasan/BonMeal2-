package com.example.mhasan.bonmeal;


import android.app.SearchManager;

import android.content.res.Configuration;

import android.graphics.PorterDuff;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import android.widget.TextView;
import android.widget.Toast;

import static android.R.color.black;
import static android.view.View.X;


public class MainActivity extends AppCompatActivity {
    public static final String KITCHEN_CHALLENGE="تحدي الطبخ";
    public static final String COOK_BOOK="كتاب الطبخ";
    public static final String AT_FRIDGE="في الثلاجة";
    public static final String MY_RECIPES="وصفاتي";
    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerList = (ListView) findViewById(R.id.navList);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        String mActivityTitle = getTitle().toString();
        getSupportActionBar().setTitle(mActivityTitle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        addDrawerItems();
        setupDrawer();
        setViewPager();


    }

    public void addDrawerItems() {

        String[] osArray = { KITCHEN_CHALLENGE ,COOK_BOOK,AT_FRIDGE,MY_RECIPES};
        ArrayAdapter<String> mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Time for an upgrade!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {
            /** Call when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //  getSupportActionBar().setTitle("Navigation!");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);

                invalidateOptionsMenu();
            }
        };
        int tabIconColor = ContextCompat.getColor(this, black);
        mDrawerToggle.getDrawerArrowDrawable().setColor(tabIconColor);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

    private void setViewPager() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        final CategoryAdapter categoryAdapter = new CategoryAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(categoryAdapter);

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_cookchallenge);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_cookbook);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_fridge);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_recipes);
        tabLayout.addOnTabSelectedListener(
                new TabLayout.ViewPagerOnTabSelectedListener(viewPager) {

                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        super.onTabSelected(tab);
                        int position = tab.getPosition();
                        switch (position) {
                            case 0:
                                TextView tabOne = (TextView) LayoutInflater.from(getBaseContext()).inflate(R.layout.custom_tab, null);
                                tabOne.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.kitchen_challenge));
                                tabOne.setText(R.string.kitchen_challenge);
                                // tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_cookchallenge, 0, 0);
                                tabLayout.getTabAt(0).setCustomView(tabOne);
                                break;
                            case 1:
                                tab.setText(R.string.cook_book);
                                break;
                            case 2:
                                tab.setText(R.string.fridge);
                                break;
                            case 3:
                                tab.setText(R.string.my_recipes);
                                break;
                        }
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                        super.onTabUnselected(tab);
                        int position = tab.getPosition();

                        switch (position) {
                            case 0:
                                tab.setText("");
                                break;
                            case 1:
                                tab.setText("");
                                break;
                            case 2:
                                tab.setText("");
                                break;
                            case 3:
                                tab.setText("");
                                break;
                        }
                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                        super.onTabReselected(tab);
//                        int tabIconColor = ContextCompat.getColor(getBaseContext(), black);
//                        tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
                    }
                }
        );
    }

}
