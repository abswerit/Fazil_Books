package com.abswer.fazilbooks;

import static java.net.Proxy.Type.HTTP;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.google.android.material.navigation.NavigationView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class ViewActivity extends AppCompatActivity {

    PDFView pdfView;
    LottieAnimationView animationView;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    Intent intent;
    ImageView imageMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view);





        drawerLayout = findViewById(R.id.drawerId);
        navigationView = findViewById(R.id.navmenuId);


        toggle = new ActionBarDrawerToggle(ViewActivity.this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.homeId) {
                    intent = new Intent(ViewActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                if (item.getItemId() == R.id.shareId) {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    String subject = "Share this app";
                    String body = "আস-সালামু আলাইকুম ওয়া-রাহমাতুল্লাহি ওয়া-বারাকাতু, হে প্রিয় মুসলিম ভাইবোন! Fazil Books অ্যাপ্সটি প্লেস্টোর থেকে বিনামূল্যে ডাউনলোড করতে পারবেন। নিচের লিংকে ক্লিক করে তারপর ইন্সটল করুন https://play.google.com/store/apps/details?id=com.abswer.fazilbooks";
                    intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                    intent.putExtra(Intent.EXTRA_TEXT, body);
                    startActivity(Intent.createChooser(intent, "Share with"));
                }
                if (item.getItemId() == R.id.rateId) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.abswer.fazilbooks"));
                    startActivity(intent);
                }
                if (item.getItemId() == R.id.privacyId) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.abswer.com/p/privacy-policy-for-fazil-books-android.html"));
                    startActivity(intent);
                }
                if (item.getItemId() == R.id.moreappsId) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/dev?id=6366150173205547249"));
                    startActivity(intent);
                }


                return false;
            }
        });

        imageMenu = findViewById(R.id.imageMenu);
        imageMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
















        pdfView = findViewById(R.id.pdfView);
        animationView = findViewById(R.id.animationView);


        animationView.setVisibility(View.VISIBLE);
        pdfView.setVisibility(View.INVISIBLE);

        boolean isOnline = getIntent().getBooleanExtra("isOnline",true);
        String pdfUrl = getIntent().getStringExtra("pdfUrl");
        if (isOnline == true){
            new loadPdfFromUrl().execute(pdfUrl);
        } else {
            File file = new File(getCacheDir(), URLUtil.guessFileName(pdfUrl,null,null));
            loadPDFOffline(file);

        }

    }



    private class loadPdfFromUrl extends AsyncTask<String, Void, InputStream>{

        @Override
        protected InputStream doInBackground(String... strings) {

            try {
                URL url = new URL(strings[0]);
                try {
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                    if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                        return new BufferedInputStream(httpURLConnection.getInputStream());
                    }


                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
            super.onPostExecute(inputStream);

            if (inputStream != null){
                loadPDFOnline(inputStream);

            } else {
                Toast.makeText(ViewActivity.this, "Failed to load PDF ", Toast.LENGTH_SHORT).show();
            }



        }
    }






    private void loadPDFOnline(InputStream inputStream){

        pdfView.fromStream(inputStream)
                .onLoad(new OnLoadCompleteListener() {
                    @Override
                    public void loadComplete(int nbPages) {
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                pdfView.setVisibility(View.VISIBLE);
                                animationView.setVisibility(View.GONE);
                            }
                        }, 2000);

                    }
                })
                .scrollHandle(new DefaultScrollHandle(ViewActivity.this))
                .spacing(3)
                .enableDoubletap(false)
                .enableAntialiasing(true)
                .load();
    }


    private void loadPDFOffline(File file){

        pdfView.fromFile(file)
                .onLoad(new OnLoadCompleteListener() {
                    @Override
                    public void loadComplete(int nbPages) {
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                pdfView.setVisibility(View.VISIBLE);
                                animationView.setVisibility(View.GONE);
                            }
                        }, 2000);

                    }
                })
                .scrollHandle(new DefaultScrollHandle(ViewActivity.this))
                .spacing(3)
                .enableDoubletap(false)
                .enableAntialiasing(true)
                .load();
    }


}