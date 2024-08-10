package com.abswer.fazilbooks;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.downloader.Error;
import com.downloader.OnCancelListener;
import com.downloader.OnDownloadListener;
import com.downloader.OnPauseListener;
import com.downloader.OnProgressListener;
import com.downloader.OnStartOrResumeListener;
import com.downloader.PRDownloader;
import com.downloader.Progress;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.material.navigation.NavigationView;

import java.io.File;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    ImageView imageMenu;
    CardView book1, book2, book3, book4, book5, book6, book7, book8, book9, book10, book11, book12, book13, book14, book15, book16, fazil1st, fazil2nd, fazil3rd;
    int downloadId;
    private Intent intent;
    InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        new Thread(
                () -> {
                    // Initialize the Google Mobile Ads SDK on a background thread.
                    MobileAds.initialize(this, initializationStatus -> {});
                })
                .start();

        LinearLayout adViewContainer = findViewById(R.id.adViewContainer);
        loadBanner(adViewContainer);

        drawerLayout = findViewById(R.id.drawerId);
        navigationView = findViewById(R.id.navmenuId);


        toggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.homeId) {
                    intent = new Intent(MainActivity.this, MainActivity.class);
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















        PRDownloader.initialize(getApplicationContext());

        book1 = findViewById(R.id.book1);
        book2 = findViewById(R.id.book2);
        book3 = findViewById(R.id.book3);
        book4 = findViewById(R.id.book4);
        book5 = findViewById(R.id.book5);
        book6 = findViewById(R.id.book6);
        book7 = findViewById(R.id.book7);
        book8 = findViewById(R.id.book8);
        book9 = findViewById(R.id.book9);
        book10 = findViewById(R.id.book10);
        book11 = findViewById(R.id.book11);
        book12 = findViewById(R.id.book12);
        book13 = findViewById(R.id.book13);
        book14 = findViewById(R.id.book14);
        book15 = findViewById(R.id.book15);
        book16 = findViewById(R.id.book16);
        fazil1st = findViewById(R.id.fazil1st);
        fazil2nd = findViewById(R.id.fazil2nd);
        fazil3rd = findViewById(R.id.fazil3rd);


        loadFullscreenAd();


        book1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String pdfUrl = "https://firebasestorage.googleapis.com/v0/b/fazil-books.appspot.com/o/01.%20Tafseerul%20Quran%20-%20Fazil%201st%20Year%20Book.pdf?alt=media&token=2aa005a7-aca7-4c94-815e-4df648716336";
                File file = new File(getCacheDir(), URLUtil.guessFileName(pdfUrl,null,null));
                if (mInterstitialAd != null) {
                    mInterstitialAd.show(MainActivity.this);
                }
                if (file.exists()){
                    Intent intent = new Intent(MainActivity.this, ViewActivity.class);
                    intent.putExtra("isOnline",false);
                    intent.putExtra("pdfUrl", pdfUrl);
                    startActivity(intent);

                } else {
                    showDialog(pdfUrl);
                }

            }
        });

        book2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pdfUrl = "https://firebasestorage.googleapis.com/v0/b/fazil-books.appspot.com/o/02.%20Hadith%20and%20Usulul%20Hadith%20-%20Fazil%201st%20Year%20Book.pdf?alt=media&token=1dbf1902-5805-4bd5-a59f-ac576a9bb1e6";
                File file = new File(getCacheDir(), URLUtil.guessFileName(pdfUrl,null,null));
                if (mInterstitialAd != null) {
                    mInterstitialAd.show(MainActivity.this);
                }
                if (file.exists()){
                    Intent intent = new Intent(MainActivity.this, ViewActivity.class);
                    intent.putExtra("isOnline",false);
                    intent.putExtra("pdfUrl", pdfUrl);
                    startActivity(intent);

                } else {
                    showDialog(pdfUrl);
                }
            }
        });

        book3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pdfUrl = "https://firebasestorage.googleapis.com/v0/b/fazil-books.appspot.com/o/03.%20Al-Aqaid%20al-Islamiyyah%20-%20Fazil%201st%20Year%20Book.pdf?alt=media&token=c442a437-e405-43e4-b246-6df63044f62d";
                File file = new File(getCacheDir(), URLUtil.guessFileName(pdfUrl,null,null));
                if (mInterstitialAd != null) {
                    mInterstitialAd.show(MainActivity.this);
                }
                if (file.exists()){
                    Intent intent = new Intent(MainActivity.this, ViewActivity.class);
                    intent.putExtra("isOnline",false);
                    intent.putExtra("pdfUrl", pdfUrl);
                    startActivity(intent);

                } else {
                    showDialog(pdfUrl);
                }
            }
        });

        book4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pdfUrl = "https://firebasestorage.googleapis.com/v0/b/fazil-books.appspot.com/o/04.%20Bangla%20-%20Fazil%201st%20Year%20Book.pdf?alt=media&token=d3beef25-1a71-4869-9981-4f9885f727df";
                File file = new File(getCacheDir(), URLUtil.guessFileName(pdfUrl,null,null));
                if (mInterstitialAd != null) {
                    mInterstitialAd.show(MainActivity.this);
                }
                if (file.exists()){
                    Intent intent = new Intent(MainActivity.this, ViewActivity.class);
                    intent.putExtra("isOnline",false);
                    intent.putExtra("pdfUrl", pdfUrl);
                    startActivity(intent);

                } else {
                    showDialog(pdfUrl);
                }
            }
        });

        book5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pdfUrl = "https://firebasestorage.googleapis.com/v0/b/fazil-books.appspot.com/o/01.%20Al%20Arabiyyatut%20Tatbikiyyah%20(Arabic)%20-%20Fazil%202nd%20Year%20Book%20(HQ).pdf?alt=media&token=a4be6ba0-2c3f-4a94-b37b-bdb544968353";
                File file = new File(getCacheDir(), URLUtil.guessFileName(pdfUrl,null,null));
                if (mInterstitialAd != null) {
                    mInterstitialAd.show(MainActivity.this);
                }
                if (file.exists()){
                    Intent intent = new Intent(MainActivity.this, ViewActivity.class);
                    intent.putExtra("isOnline",false);
                    intent.putExtra("pdfUrl", pdfUrl);
                    startActivity(intent);

                } else {
                    showDialog(pdfUrl);
                }
            }
        });

        book6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pdfUrl = "https://firebasestorage.googleapis.com/v0/b/fazil-books.appspot.com/o/02.%20Al%20Fiqah%20-%20Fazil%202nd%20Year%20Book%20(HQ).pdf?alt=media&token=dd9335f1-a3db-494e-a05b-9d1f20021e49";
                File file = new File(getCacheDir(), URLUtil.guessFileName(pdfUrl,null,null));
                if (mInterstitialAd != null) {
                    mInterstitialAd.show(MainActivity.this);
                }
                if (file.exists()){
                    Intent intent = new Intent(MainActivity.this, ViewActivity.class);
                    intent.putExtra("isOnline",false);
                    intent.putExtra("pdfUrl", pdfUrl);
                    startActivity(intent);

                } else {
                    showDialog(pdfUrl);
                }
            }
        });

        book7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pdfUrl = "https://firebasestorage.googleapis.com/v0/b/fazil-books.appspot.com/o/03.%20Usulul%20Fiqh%20and%20Dawah%20-%20Fazil%202nd%20Year%20Book%20(HQ).pdf?alt=media&token=c4bc901b-7ce5-4ecc-a412-330f2d3b8f21";
                File file = new File(getCacheDir(), URLUtil.guessFileName(pdfUrl,null,null));
                if (mInterstitialAd != null) {
                    mInterstitialAd.show(MainActivity.this);
                }
                if (file.exists()){
                    Intent intent = new Intent(MainActivity.this, ViewActivity.class);
                    intent.putExtra("isOnline",false);
                    intent.putExtra("pdfUrl", pdfUrl);
                    startActivity(intent);

                } else {
                    showDialog(pdfUrl);
                }
            }
        });

        book8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pdfUrl = "https://firebasestorage.googleapis.com/v0/b/fazil-books.appspot.com/o/04.%20English%20(Model%20Test)%20-%20Fazil%202nd%20Year%20Book%20(HQ).pdf?alt=media&token=69b7fc4f-3110-4f22-ac36-c0805accc16e";
                File file = new File(getCacheDir(), URLUtil.guessFileName(pdfUrl,null,null));
                if (mInterstitialAd != null) {
                    mInterstitialAd.show(MainActivity.this);
                }
                if (file.exists()){
                    Intent intent = new Intent(MainActivity.this, ViewActivity.class);
                    intent.putExtra("isOnline",false);
                    intent.putExtra("pdfUrl", pdfUrl);
                    startActivity(intent);

                } else {
                    showDialog(pdfUrl);
                }
            }
        });

        book9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pdfUrl = "https://firebasestorage.googleapis.com/v0/b/fazil-books.appspot.com/o/04.%20English%20(Solution)%20-%20Fazil%202nd%20Year%20Book%20(HQ).pdf?alt=media&token=30b21492-3cf2-4449-99c0-fdc49f82f001";
                File file = new File(getCacheDir(), URLUtil.guessFileName(pdfUrl,null,null));
                if (mInterstitialAd != null) {
                    mInterstitialAd.show(MainActivity.this);
                }
                if (file.exists()){
                    Intent intent = new Intent(MainActivity.this, ViewActivity.class);
                    intent.putExtra("isOnline",false);
                    intent.putExtra("pdfUrl", pdfUrl);
                    startActivity(intent);

                } else {
                    showDialog(pdfUrl);
                }
            }
        });

        book10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pdfUrl = "https://firebasestorage.googleapis.com/v0/b/fazil-books.appspot.com/o/01.%20Islamic%20Studies%201st%20Paper%20-%20Fazil%203rd%20Year%20Guide%20Book.pdf?alt=media&token=a6302d16-739a-4052-9957-b246fa5696e8";
                File file = new File(getCacheDir(), URLUtil.guessFileName(pdfUrl,null,null));
                if (mInterstitialAd != null) {
                    mInterstitialAd.show(MainActivity.this);
                }
                if (file.exists()){
                    Intent intent = new Intent(MainActivity.this, ViewActivity.class);
                    intent.putExtra("isOnline",false);
                    intent.putExtra("pdfUrl", pdfUrl);
                    startActivity(intent);

                } else {
                    showDialog(pdfUrl);
                }
            }
        });

        book11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pdfUrl = "https://firebasestorage.googleapis.com/v0/b/fazil-books.appspot.com/o/02.%20Islamic%20Studies%202nd%20Paper%20-%20Fazil%203rd%20Year%20Guide%20Book.pdf?alt=media&token=c10eedc2-7d49-4698-8fc2-8d1b10e0637d";
                File file = new File(getCacheDir(), URLUtil.guessFileName(pdfUrl,null,null));
                if (mInterstitialAd != null) {
                    mInterstitialAd.show(MainActivity.this);
                }
                if (file.exists()){
                    Intent intent = new Intent(MainActivity.this, ViewActivity.class);
                    intent.putExtra("isOnline",false);
                    intent.putExtra("pdfUrl", pdfUrl);
                    startActivity(intent);

                } else {
                    showDialog(pdfUrl);
                }
            }
        });

        book12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pdfUrl = "https://firebasestorage.googleapis.com/v0/b/fazil-books.appspot.com/o/03.%20Islamic%20Studies%203rd%20Paper%20-%20Fazil%203rd%20Year%20Guide%20Book.pdf?alt=media&token=bc1e0b07-6c6a-4f06-8064-478dc59986c2";
                File file = new File(getCacheDir(), URLUtil.guessFileName(pdfUrl,null,null));
                if (mInterstitialAd != null) {
                    mInterstitialAd.show(MainActivity.this);
                }
                if (file.exists()){
                    Intent intent = new Intent(MainActivity.this, ViewActivity.class);
                    intent.putExtra("isOnline",false);
                    intent.putExtra("pdfUrl", pdfUrl);
                    startActivity(intent);

                } else {
                    showDialog(pdfUrl);
                }
            }
        });

        book13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pdfUrl = "https://firebasestorage.googleapis.com/v0/b/fazil-books.appspot.com/o/04.%20Islamic%20History%201st%20Paper%20-%20Fazil%203rd%20Year%20Guide%20Book.pdf?alt=media&token=20c78855-a31c-4cb9-b5af-a1d7e6bac885";
                File file = new File(getCacheDir(), URLUtil.guessFileName(pdfUrl,null,null));
                if (mInterstitialAd != null) {
                    mInterstitialAd.show(MainActivity.this);
                }
                if (file.exists()){
                    Intent intent = new Intent(MainActivity.this, ViewActivity.class);
                    intent.putExtra("isOnline",false);
                    intent.putExtra("pdfUrl", pdfUrl);
                    startActivity(intent);

                } else {
                    showDialog(pdfUrl);
                }
            }
        });

        book14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pdfUrl = "https://firebasestorage.googleapis.com/v0/b/fazil-books.appspot.com/o/05.%20Islamic%20History%202nd%20Paper%20-%20Fazil%203rd%20Year%20Guide%20Book.pdf?alt=media&token=6f87fb8d-d371-49c6-b6d7-8080b7a43015";
                File file = new File(getCacheDir(), URLUtil.guessFileName(pdfUrl,null,null));
                if (mInterstitialAd != null) {
                    mInterstitialAd.show(MainActivity.this);
                }
                if (file.exists()){
                    Intent intent = new Intent(MainActivity.this, ViewActivity.class);
                    intent.putExtra("isOnline",false);
                    intent.putExtra("pdfUrl", pdfUrl);
                    startActivity(intent);

                } else {
                    showDialog(pdfUrl);
                }
            }
        });

        book15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pdfUrl = "https://firebasestorage.googleapis.com/v0/b/fazil-books.appspot.com/o/06.%20Islamic%20History%203rd%20Paper%20-%20Fazil%203rd%20Year%20Guide%20Book.pdf?alt=media&token=e50cd1cb-cde4-456d-8a7d-f04dcfc483f8";
                File file = new File(getCacheDir(), URLUtil.guessFileName(pdfUrl,null,null));
                if (mInterstitialAd != null) {
                    mInterstitialAd.show(MainActivity.this);
                }
                if (file.exists()){
                    Intent intent = new Intent(MainActivity.this, ViewActivity.class);
                    intent.putExtra("isOnline",false);
                    intent.putExtra("pdfUrl", pdfUrl);
                    startActivity(intent);

                } else {
                    showDialog(pdfUrl);
                }
            }
        });

        book16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pdfUrl = "https://firebasestorage.googleapis.com/v0/b/fazil-books.appspot.com/o/07.%20Islamic%20Economics%201st%20Paper%20-%20Fazil%203rd%20Year%20Guide.pdf?alt=media&token=15685f8f-aad8-4bb4-bf4f-a58de5b85d7f";
                File file = new File(getCacheDir(), URLUtil.guessFileName(pdfUrl,null,null));
                if (mInterstitialAd != null) {
                    mInterstitialAd.show(MainActivity.this);
                }
                if (file.exists()){
                    Intent intent = new Intent(MainActivity.this, ViewActivity.class);
                    intent.putExtra("isOnline",false);
                    intent.putExtra("pdfUrl", pdfUrl);
                    startActivity(intent);

                } else {
                    showDialog(pdfUrl);
                }
            }
        });

        fazil1st.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pdfUrl = "https://firebasestorage.googleapis.com/v0/b/fazil-books.appspot.com/o/Fazil%201st%20Year%20Suggestions%20for%20Exam%202023%20(Held%202024-25).pdf?alt=media&token=206f6f34-6b70-4a1c-9da7-f6995a57ba4b";
                File file = new File(getCacheDir(), URLUtil.guessFileName(pdfUrl,null,null));
                if (mInterstitialAd != null) {
                    mInterstitialAd.show(MainActivity.this);
                }
                if (file.exists()){
                    Intent intent = new Intent(MainActivity.this, ViewActivity.class);
                    intent.putExtra("isOnline",false);
                    intent.putExtra("pdfUrl", pdfUrl);
                    startActivity(intent);

                } else {
                    showDialog(pdfUrl);
                }
            }
        });

        fazil2nd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pdfUrl = "https://firebasestorage.googleapis.com/v0/b/fazil-books.appspot.com/o/Fazil%202nd%20Year%20Suggestions%20for%20Exam%202023%20(Held%202024-25).pdf?alt=media&token=34e6a7ab-0c20-4c07-a21a-8a853d925c73";
                File file = new File(getCacheDir(), URLUtil.guessFileName(pdfUrl,null,null));
                if (mInterstitialAd != null) {
                    mInterstitialAd.show(MainActivity.this);
                }
                if (file.exists()){
                    Intent intent = new Intent(MainActivity.this, ViewActivity.class);
                    intent.putExtra("isOnline",false);
                    intent.putExtra("pdfUrl", pdfUrl);
                    startActivity(intent);

                } else {
                    showDialog(pdfUrl);
                }
            }
        });

        fazil3rd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pdfUrl = "https://firebasestorage.googleapis.com/v0/b/fazil-books.appspot.com/o/Fazil%203rd%20Year%20Suggestions%20for%20Exam%202023%20(Held%202024-25).pdf?alt=media&token=e2ccfe55-ebf1-42fe-801b-8ac4c9b3a4c4";
                File file = new File(getCacheDir(), URLUtil.guessFileName(pdfUrl,null,null));
                if (mInterstitialAd != null) {
                    mInterstitialAd.show(MainActivity.this);
                }
                if (file.exists()){
                    Intent intent = new Intent(MainActivity.this, ViewActivity.class);
                    intent.putExtra("isOnline",false);
                    intent.putExtra("pdfUrl", pdfUrl);
                    startActivity(intent);

                } else {
                    showDialog(pdfUrl);
                }
            }
        });














    }

    private void showDialog(String pdfUrl){
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View myView = inflater.inflate(R.layout.choose_item, null);

        Button viewOnlineId = myView.findViewById(R.id.viewOnlineId);
        Button downloadId = myView.findViewById(R.id.downloadId);
        Button cancelId = myView.findViewById(R.id.cancelId);

        alert.setView(myView);

        AlertDialog alertDialog = alert.create();
        alertDialog.setCancelable(false);

        viewOnlineId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ViewActivity.class);
                intent.putExtra("isOnline",true);
                intent.putExtra("pdfUrl", pdfUrl);
                startActivity(intent);
                alertDialog.dismiss();


            }
        });

        downloadId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Download Start", Toast.LENGTH_SHORT).show();
                downloadPdf(pdfUrl);
                alertDialog.dismiss();

            }
        });

        cancelId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                alertDialog.dismiss();
            }
        });

        alertDialog.show();


    }

    private void downloadPdf(String pdfUrl){
        ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setIcon(R.drawable.ic_download);
        progressDialog.setTitle("Download");
        progressDialog.setMessage("Downloading PDF File, Please wait a moment...");
        progressDialog.setCancelable(false);
        progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                PRDownloader.cancel(downloadId);
                progressDialog.dismiss();
            }
        });

        progressDialog.show();

        downloadId = PRDownloader.download(pdfUrl, String.valueOf(getCacheDir()), URLUtil.guessFileName(pdfUrl,null,null))
                .build()
                .setOnStartOrResumeListener(new OnStartOrResumeListener() {
                    @Override
                    public void onStartOrResume() {

                    }
                })
                .setOnPauseListener(new OnPauseListener() {
                    @Override
                    public void onPause() {

                    }
                })
                .setOnCancelListener(new OnCancelListener() {
                    @Override
                    public void onCancel() {

                    }
                })
                .setOnProgressListener(new OnProgressListener() {
                    @Override
                    public void onProgress(Progress progress) {
                        int progressPercentage = (int) (progress.currentBytes*100/progress.totalBytes);
                        progressDialog.setMessage("Download : "+progressPercentage+" %");

                    }
                })
                .start(new OnDownloadListener() {
                    @Override
                    public void onDownloadComplete() {
                        Intent intent = new Intent(MainActivity.this, ViewActivity.class);
                        intent.putExtra("isOnline",false);
                        intent.putExtra("pdfUrl", pdfUrl);
                        startActivity(intent);

                        Toast.makeText(MainActivity.this, "Download Completed", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onError(Error error) {
                        Toast.makeText(MainActivity.this, "Download Failed", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                });
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder alertDialogBuilder;
        alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setIcon(R.drawable.ic_exit);
        alertDialogBuilder.setTitle(R.string.exit_title);
        alertDialogBuilder.setMessage(R.string.exit_message);
        alertDialogBuilder.setCancelable(false);

        alertDialogBuilder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });

        alertDialogBuilder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }








    // Step 1 : Implementation Admob banner ads method in your Activity
    private void loadBanner(LinearLayout adViewContainer) {

        // Create a new ad view.
        AdView adView = new AdView(this);
        adView.setAdSize(getAdSize(adViewContainer));
        adView.setAdUnitId("ca-app-pub-3940256099942544/9214589741");

        // Replace ad container with new ad view.
        adViewContainer.removeAllViews();
        adViewContainer.addView(adView);

        // Start loading the ad in the background.
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    private AdSize getAdSize(LinearLayout adViewContainer) {
        // Determine the screen width (less decorations) to use for the ad width.
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float density = outMetrics.density;

        float adWidthPixels = adViewContainer.getWidth();

        // If the ad hasn't been laid out, default to the full screen width.
        if (adWidthPixels == 0) {
            adWidthPixels = outMetrics.widthPixels;
        }

        int adWidth = (int) (adWidthPixels / density);
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this, adWidth);
    }







    // FullScreen Ads
// Step 1 : loadFullscreenAd
    private void loadFullscreenAd() {

        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(this, "ca-app-pub-3940256099942544/1033173712", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        mInterstitialAd = interstitialAd;

                        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                            @Override
                            public void onAdClicked() {

                            }

                            @Override
                            public void onAdDismissedFullScreenContent() {
                                mInterstitialAd = null;
                                loadFullscreenAd();
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(AdError adError) {
                                mInterstitialAd = null;
                            }

                            @Override
                            public void onAdImpression() {
                            }

                            @Override
                            public void onAdShowedFullScreenContent() {

                            }
                        });

                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        super.onAdFailedToLoad(loadAdError);
                    }

                });

    }
// loadFullscreenAd




}