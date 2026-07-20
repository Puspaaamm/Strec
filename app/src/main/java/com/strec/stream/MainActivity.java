package com.strec.stream;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.projection.MediaProjectionManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final int SCREEN_CAPTURE_REQUEST = 2000;
    
    private LinearLayout mainLayout;
    private TextView tvLogo, tvSub, tvFilters, tvThemesLabel;
    private EditText etStreamUrl;
    private Button btnStreamToggle, btnGithub;
    
    private boolean isLive = false;
    private String currentAccentColor = "#FF0055"; // Default Injected Cyber Pink Accent

    // 5 Unique Dynamic Gaming Neon Preset System Blocks
    private final String[][] themePresets = {
            {"CYBER PINK", "#FF0055"},
            {"NEON LIME", "#39FF14"},
            {"TOXIC TEAL", "#00F5FF"},
            {"VOLT YELLOW", "#FFF000"},
            {"SONIC BLUE", "#1F51FF"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Absolute Premium Dark Setup Core
        mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.setPadding(60, 80, 60, 60);
        mainLayout.setGravity(Gravity.CENTER_HORIZONTAL);
        mainLayout.setBackgroundColor(Color.parseColor("#0A0A0C")); 

        // Logo Header Design Identity
        tvLogo = new TextView(this);
        tvLogo.setText("STREC");
        tvLogo.setTextSize(64); 
        tvLogo.setTypeface(Typeface.create("sans-serif-condensed", Typeface.BOLD));
        tvLogo.setGravity(Gravity.CENTER);
        mainLayout.addView(tvLogo);

        tvSub = new TextView(this);
        tvSub.setText("ULTRA 1080P 60FPS BROADCASTER");
        tvSub.setTextSize(10);
        tvSub.setPadding(0, 0, 0, 70);
        mainLayout.addView(tvSub);

        // Target Ingestion Real-time Endpoint Input Box
        etStreamUrl = new EditText(this);
        etStreamUrl.setHint("rtmp://a.rtmp.youtube.com/live2/YOUR_STREAM_KEY");
        etStreamUrl.setHintTextColor(Color.parseColor("#444446"));
        etStreamUrl.setTextColor(Color.WHITE);
        etStreamUrl.setTextSize(13);
        etStreamUrl.setPadding(40, 45, 40, 45);
        etStreamUrl.setBackgroundColor(Color.parseColor("#141416"));
        mainLayout.addView(etStreamUrl);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 40, 0, 60);

        // Streaming Toggle Command System
        btnStreamToggle = new Button(this);
        btnStreamToggle.setText("START BROADCASTING");
        btnStreamToggle.setTextColor(Color.WHITE);
        btnStreamToggle.setTypeface(Typeface.DEFAULT_BOLD);
        btnStreamToggle.setLayoutParams(layoutParams);
        mainLayout.addView(btnStreamToggle);

        // Themes Deck Engine Instantiation
        tvThemesLabel = new TextView(this);
        tvThemesLabel.setText("DYNAMIC AMBIENT THEME MATRIX");
        tvThemesLabel.setTextSize(11);
        tvThemesLabel.setPadding(0, 20, 0, 20);
        mainLayout.addView(tvThemesLabel);

        HorizontalScrollView themesScroller = new HorizontalScrollView(this);
        themesScroller.setHorizontalScrollBarEnabled(false);
        LinearLayout themesContainer = new LinearLayout(this);
        themesContainer.setOrientation(LinearLayout.HORIZONTAL);

        for (String[] theme : themePresets) {
            Button btnTheme = new Button(this);
            btnTheme.setText(theme[0]);
            btnTheme.setTextSize(10);
            btnTheme.setTextColor(Color.WHITE);
            btnTheme.setBackgroundColor(Color.parseColor("#1C1C1E"));
            
            LinearLayout.LayoutParams itemParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            itemParams.setMargins(10, 0, 10, 0);
            btnTheme.setLayoutParams(itemParams);

            btnTheme.setOnClickListener(v -> applyDynamicColorMatrix(theme[1]));
            themesContainer.addView(btnTheme);
        }
        themesScroller.addView(themesContainer);
        mainLayout.addView(themesScroller);

        // Live GPU Shaders Filter Core Module List
        tvFilters = new TextView(this);
        tvFilters.setText("LIVE GPU GRAPHICS FILTERS");
        tvFilters.setTextSize(11);
        tvFilters.setPadding(0, 60, 0, 20);
        mainLayout.addView(tvFilters);

        HorizontalScrollView filtersScroller = new HorizontalScrollView(this);
        filtersScroller.setHorizontalScrollBarEnabled(false);
        LinearLayout filtersContainer = new LinearLayout(this);
        filtersContainer.setOrientation(LinearLayout.HORIZONTAL);

        String[] filterNames = {"Normal", "Vivid", "Classic", "Ocean", "4K Sharp"};
        for (String name : filterNames) {
            Button btnFilter = new Button(this);
            btnFilter.setText(name.toUpperCase());
            btnFilter.setTextSize(10);
            btnFilter.setTextColor(Color.WHITE);
            btnFilter.setBackgroundColor(Color.parseColor("#1C1C1E"));
            
            LinearLayout.LayoutParams itemParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            itemParams.setMargins(10, 0, 10, 0);
            btnFilter.setLayoutParams(itemParams);

            btnFilter.setOnClickListener(v -> {
                if (isLive) {
                    Intent filterIntent = new Intent(this, StreamService.class)
                            .setAction("CHANGE_FILTER")
                            .putExtra("FILTER_TYPE", name);
                    startService(filterIntent);
                    Toast.makeText(this, name + " Filter Injected", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Broadcast pipeline inactive!", Toast.LENGTH_SHORT).show();
                }
            });
            filtersContainer.addView(btnFilter);
        }
        filtersScroller.addView(filtersContainer);
        mainLayout.addView(filtersScroller);

        // Injected Dedicated Hardcoded Official Repository Anchor Gateway
        LinearLayout.LayoutParams gitParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        gitParams.setMargins(0, 100, 0, 0);

        btnGithub = new Button(this);
        btnGithub.setText("VIEW SOURCE ON GITHUB");
        btnGithub.setTextSize(11);
        btnGithub.setBackgroundColor(Color.TRANSPARENT);
        btnGithub.setLayoutParams(gitParams);
        
        btnGithub.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Puspaaamm/Strec"));
            startActivity(browserIntent);
        });
        mainLayout.addView(btnGithub);

        applyDynamicColorMatrix(currentAccentColor);
        setContentView(mainLayout);

        btnStreamToggle.setOnClickListener(v -> {
            if (!isLive) {
                String url = etStreamUrl.getText().toString().trim();
                if (url.isEmpty()) {
                    Toast.makeText(this, "Target ingestion URL required!", Toast.LENGTH_SHORT).show();
                    return;
                }
                MediaProjectionManager manager = (MediaProjectionManager) getSystemService(MEDIA_PROJECTION_SERVICE);
                if (manager != null) {
                    startActivityForResult(manager.createScreenCaptureIntent(), SCREEN_CAPTURE_REQUEST);
                }
            } else {
                stopStreamingProcess();
            }
        });
    }

    private void applyDynamicColorMatrix(String hexColor) {
        currentAccentColor = hexColor;
        int parsedColor = Color.parseColor(hexColor);
        
        tvLogo.setTextColor(parsedColor);
        tvSub.setTextColor(Color.parseColor("#666668"));
        tvThemesLabel.setTextColor(parsedColor);
        tvFilters.setTextColor(parsedColor);
        
        if (!isLive) {
            btnStreamToggle.setBackgroundColor(parsedColor);
        }
        btnGithub.setTextColor(parsedColor);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SCREEN_CAPTURE_REQUEST && resultCode == RESULT_OK && data != null) {
            
            Intent serviceIntent = new Intent(this, StreamService.class)
                    .putExtra("STREAM_URL", etStreamUrl.getText().toString().trim())
                    .putExtra("RESULT_CODE", resultCode)
                    .putExtra("DATA", data);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(serviceIntent);
            } else {
                startService(serviceIntent);
            }

            btnStreamToggle.setText("STOP LIVE STREAM");
            btnStreamToggle.setBackgroundColor(Color.parseColor("#2C2C2E"));
            isLive = true;

            Intent homeIntent = new Intent(Intent.ACTION_MAIN)
                    .addCategory(Intent.CATEGORY_HOME)
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(homeIntent);
        }
    }

    private void stopStreamingProcess() {
        StreamService.stopStreaming();
        stopService(new Intent(this, StreamService.class));
        btnStreamToggle.setText("START BROADCASTING");
        btnStreamToggle.setBackgroundColor(Color.parseColor(currentAccentColor));
        isLive = false;
        Toast.makeText(this, "Strec Pipeline Disengaged", Toast.LENGTH_SHORT).show();
    }
}

