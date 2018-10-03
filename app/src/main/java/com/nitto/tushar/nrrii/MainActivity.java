package com.nitto.tushar.nrrii;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.nitto.tushar.nrrii.Services.CartService;
import com.nitto.tushar.nrrii.Services.OrderService;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private List<Fragment> fragmentCategoriesList;
    private CategoriesViewPagerAdapter categoriesViewPagerAdapter;

    private String[] productCategory = {"ALL ITEMS", "VEG AND FRUITS",
            "FISH AND MEAT", "GROCERIES",
            "MEAL", "MEDICINE", "TRAVEL",
            "UTILITIES", "EDUCATION", "FASHION", "MISC" };
    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP) {
            requestToGrantDataReadWritePermission();
        } else {
            initializeEverything();
        }
    }

    private void requestToGrantDataReadWritePermission() {
        if(
                ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                        || ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            Dexter
                    .withActivity(this)
                    .withPermissions(
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE)
                    .withListener(new MultiplePermissionsListener() {
                        @Override
                        public void onPermissionsChecked(MultiplePermissionsReport report) {
                            if(report.isAnyPermissionPermanentlyDenied()) {
                                new AlertDialog.Builder(MainActivity.this)
                                        .setTitle("Permission denied")
                                        .setMessage("All the permissions are necessary to read/write data in this app." +
                                                "\nYou will not be able to read/write any data without these permission." +
                                                "\nBefore you can move any further, you must accept these permission." +
                                                "\nYou have permanently denied/disabled at least one of them and can only allow/enable them again from" +
                                                "\nSettings->Apps->Product Catalog->Permissions" +
                                                "\nApp will exit now.")
                                        .setIcon(android.R.drawable.ic_dialog_alert)
                                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                                finish();
                                            }
                                        }).show();
                            } else {
                                if(!report.areAllPermissionsGranted()) {
                                    new AlertDialog.Builder(MainActivity.this)
                                            .setTitle("Permission denied")
                                            .setMessage("All the permissions are necessary to read/write data in this app." +
                                                    "\nYou will not be able to read/write any data without these permission." +
                                                    "\nBefore you can move any further, you must accept these permission.")
                                            .setIcon(android.R.drawable.ic_dialog_alert)
                                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.dismiss();
                                                }
                                            }).show();
                                } else {
                                    initializeEverything();
                                }
                            }
                        }

                        @Override
                        public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                            token.continuePermissionRequest();
                        }
                    }).check();

        } else {
            initializeEverything();
        }
    }

    private void initializeEverything() {
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        populateCategoriesList();
        categoriesViewPagerAdapter = new CategoriesViewPagerAdapter(this.fragmentCategoriesList, getSupportFragmentManager());
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(categoriesViewPagerAdapter);
        viewPager.setOffscreenPageLimit(0);

        SmartTabLayout viewPagerTab = findViewById(R.id.viewpagertab);
        viewPagerTab.setViewPager(viewPager);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);


        //OrderService.getInstance().clearOrderList();
        //OrderService.getInstance().getAllOrderFromDB();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
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

        if (id == R.id.shopping_cart) {
            if(!CartService.getInstance().isEmpty()) {
                startActivity(new Intent(this, CartActivity.class) );
            } else {
                Toast.makeText(this, "Cart is empty", Toast.LENGTH_SHORT).show();
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

//        if (id == R.id.nav_camera) {
//            Toast.makeText(this, "You are clicked to Import.", Toast.LENGTH_SHORT).show();
//        } else if (id == R.id.nav_gallery) {
//            Toast.makeText(this, "You are clicked to Gallery.", Toast.LENGTH_SHORT).show();
//        } else if (id == R.id.nav_slideshow) {
//            Toast.makeText(this, "You are clicked to SlideShow.", Toast.LENGTH_SHORT).show();
//        } else if (id == R.id.nav_manage) {
//            Toast.makeText(this, "You are clicked to Tools.", Toast.LENGTH_SHORT).show();
//        } else if (id == R.id.nav_share) {
//            Toast.makeText(this, "You are clicked to Share.", Toast.LENGTH_SHORT).show();
//        } else if (id == R.id.nav_send) {
//            Toast.makeText(this, "You are clicked to Send.", Toast.LENGTH_SHORT).show();
//        }

        if (id == R.id.menu_my_orders) {
            Toast.makeText(this, "My Orders", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MyOrdersActivity.class) );
        } else if (id == R.id.menu_shopping_cart) {
            Toast.makeText(this, "Shopping Cart", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.menu_my_favorites) {
            Toast.makeText(this, "My Favourites", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.menu_browse) {
            Toast.makeText(this, "Browse", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.menu_notifications) {
            Toast.makeText(this, "Notifications", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.menu_order_history) {
            Toast.makeText(this, "Order History", Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void populateCategoriesList() {
        this.fragmentCategoriesList = new ArrayList<>();
        for(int i=0;i<11;i++) {
            DemoFragment demoFragment = new DemoFragment();
            demoFragment.setPageTitle(productCategory[i]);
            this.fragmentCategoriesList.add(demoFragment);
        }
    }

    public static class CategoriesViewPagerAdapter extends SmartFragmentStatePagerAdapter {

        private List<Fragment> categoriesFragmentList;

        public CategoriesViewPagerAdapter(List<Fragment> categoriesFragmentList, FragmentManager fragmentManager) {
            super(fragmentManager);
            this.categoriesFragmentList = categoriesFragmentList;
        }

        @Override
        public Fragment getItem(int position) {
            return this.categoriesFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return this.categoriesFragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return ((DemoFragment)this.categoriesFragmentList.get(position)).GetPageTitle();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
