package com.absolute.whatsappstickers.activities;

import android.content.ComponentName;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sangcomz.fishbun.define.Define;
import com.absolute.whatsappstickers.R;
import com.absolute.whatsappstickers.backgroundRemover.CutOut;
import com.absolute.whatsappstickers.identities.StickerPacksContainer;
import com.absolute.whatsappstickers.utils.StickerPacksManager;
import com.absolute.whatsappstickers.whatsapp_api.AddStickerPackActivity;

import java.util.List;
import java.util.Objects;


public class MainActivity extends AddStickerPackActivity implements CheckRefreshClickListener {
    private MyStickersFragment myStickersFragment;
    private ExploreFragment exploreFragment;
    private CreateFragment createFragment;
    private FloatingActionButton fab;
    private BottomAppBar bottomAppBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomAppBar=findViewById(R.id.bottom_app_bar);
        setSupportActionBar(bottomAppBar);

        Fresco.initialize(this);
        this.setupFragments();

        setFragmento(myStickersFragment);
        StickerPacksManager.stickerPacksContainer = new StickerPacksContainer("", "", StickerPacksManager.getStickerPacks(this));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        fab=findViewById(R.id.fab);
        fab.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.cusotmcreate, null));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, NewStickerPackActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bottom_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.share:
                    setFragmento(myStickersFragment);
                    return true;
                /*case R.id.menu_explore:
                    setFragmento(exploreFragment);
                    return true;*/
            }
            return false;
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CutOut.CUTOUT_ACTIVITY_REQUEST_CODE || requestCode == Define.ALBUM_REQUEST_CODE) {
            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frame_principal);
            Objects.requireNonNull(fragment).onActivityResult(requestCode, resultCode, data);
        }
    }



    private void setupFragments() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        myStickersFragment = new MyStickersFragment();
        exploreFragment = new ExploreFragment();
        createFragment = new CreateFragment();
        fragmentTransaction.add(R.id.frame_principal, myStickersFragment);
        fragmentTransaction.add(R.id.frame_principal, exploreFragment);
        fragmentTransaction.add(R.id.frame_principal, createFragment);
        fragmentTransaction.hide(exploreFragment);
        fragmentTransaction.hide(createFragment);
        fragmentTransaction.commit();
    }


    private void setFragmento(Fragment fragmento) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        if (fragmento == myStickersFragment) {
            fragmentTransaction.hide(exploreFragment);
            fragmentTransaction.hide(createFragment);
        } else if (fragmento == exploreFragment) {
            fragmentTransaction.hide(myStickersFragment);
            fragmentTransaction.hide(createFragment);
        } else if (fragmento == createFragment) {
            fragmentTransaction.hide(myStickersFragment);
            fragmentTransaction.hide(exploreFragment);
        }
        fragmentTransaction.show(fragmento);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
//        ShowRoundDialogFragment showRoundDialogFragment =new ShowRoundDialogFragment();
//        showRoundDialogFragment.show(getSupportFragmentManager(),
//                "add");
//        super.onBackPressed();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            finish();
            System.exit(0);
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuItem = item.getItemId();
        if(menuItem==android.R.id.home){
            ShowRoundDialogFragment showRoundDialogFragment =new ShowRoundDialogFragment();
            showRoundDialogFragment.show(getSupportFragmentManager(),
                    "add");
        }
        if(menuItem==R.id.share){
            try {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT, "Sticker Central");
                String sAux = "\nDownload Sticker Maker for creating 100% ad free awesome WhatsApp stickers.\n\n";
                sAux = sAux + "https://play.google.com/store/apps/details?id="+getApplicationContext().getPackageName()+"\n\n";
                i.putExtra(Intent.EXTRA_TEXT, sAux);
                startActivity(Intent.createChooser(i, "choose one"));
            } catch(Exception e) {
                //e.toString();
            }
        }
        if(menuItem==R.id.about){
            AboutDialogFragment ab=new AboutDialogFragment();
            ab.show(getSupportFragmentManager(),"SHOWN");
        }
        if(menuItem==R.id.support){
                String appId = this.getPackageName();
                Intent rateIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=" + appId));
                boolean marketFound = false;

                // find all applications able to handle our rateIntent
                final List<ResolveInfo> otherApps = this.getPackageManager()
                        .queryIntentActivities(rateIntent, 0);
                for (ResolveInfo otherApp: otherApps) {
                    // look for Google Play application
                    if (otherApp.activityInfo.applicationInfo.packageName
                            .equals("com.android.vending")) {

                        ActivityInfo otherAppActivity = otherApp.activityInfo;
                        ComponentName componentName = new ComponentName(
                                otherAppActivity.applicationInfo.packageName,
                                otherAppActivity.name
                        );
                        // make sure it does NOT open in the stack of your activity
                        rateIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        // task reparenting if needed
                        rateIntent.addFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                        // if the Google Play was already open in a search result
                        //  this make sure it still go to the app page you requested
                        rateIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        // this make sure only the Google Play app is allowed to
                        // intercept the intent
                        rateIntent.setComponent(componentName);
                        this.startActivity(rateIntent);
                        marketFound = true;
                        break;

                    }
                }

                // if GP not present on device, open web browser
                if (!marketFound) {
                    Intent webIntent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://play.google.com/store/apps/details?id="+appId));
                    this.startActivity(webIntent);
                }
            }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onLicenseClick() {
        openPlay("com.innovate.stickercentral");
    }
    public void openPlay(String mark){
        Intent i;
        PackageManager manager = getPackageManager();
        try {
            i = manager.getLaunchIntentForPackage(mark);
            if (i == null)
                throw new PackageManager.NameNotFoundException();
            i.addCategory(Intent.CATEGORY_LAUNCHER);
            startActivity(i);
        } catch (PackageManager.NameNotFoundException e) {

//if not found in device then will come here
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("market://details?id="+mark));
            startActivity(intent);
        }
    }
    @Override
    public void OnShareClick() {
        setFragmento(createFragment);
    }

    @Override
    public void onFeedbackClick() {
        openPlay("com.absolute.tooncentral");

    }

    @Override
    public void onAboutClick() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("market://details?id="+"com.absolute.floral"));
        startActivity(intent);
    }

    @Override
    public void onCreateClick() {
        setFragmento(myStickersFragment);
    }
}
