package com.strec.stream;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.projection.MediaProjectionManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.webkit.WebView;
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
    
    // Core Subsystem Visual Framework Layout Anchors
    private LinearLayout mainLayout;
    private WebView webLogoLayer;
    private TextView tvSub, tvFilters, tvThemesLabel;
    private EditText etStreamUrl;
    private Button btnStreamToggle, btnGithub;
    
    // Internal Engine Variable Targets
    private boolean isLive = false;
    private String currentAccentColor = "#FF0055"; // Baseline Initialization Accent

    // 5 Matrix Arrays representing individual theme configs
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

        // Primary Ambient Background Node Container Setup
        mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.setPadding(60, 60, 60, 60);
        mainLayout.setGravity(Gravity.CENTER_HORIZONTAL);
        mainLayout.setBackgroundColor(Color.parseColor("#0A0A0C")); 

        // 1. HARDCODED DECODER CORE TO PLAY NATIVE VECTOR SVG LOGO FROM REMOTE SERVER
        webLogoLayer = new WebView(this);
        webLogoLayer.setBackgroundColor(Color.TRANSPARENT);
        webLogoLayer.getSettings().setJavaScriptEnabled(true);
        
        // Formulates an isolated viewport loop that centers the dynamic content fluidly
        String svgTargetUrl = "https://imagetourl.cloud/6nhspwvz.svg";
        String pipelineHtml = "<html><body style='margin:0;padding:0;display:flex;justify-content:center;align-items:center;background:transparent;'>"
                + "<img src='" + svgTargetUrl + "' style='width:auto;height:95px;max-width:100%;object-fit:contain;'/>"
                + "</body></html>";
        webLogoLayer.loadDataWithBaseURL(null, pipelineHtml, "text/html", "UTF-8", null);

        LinearLayout.LayoutParams webViewParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 260);
        webViewParams.setMargins(0, 40, 0, 10);
        webLogoLayer.setLayoutParams(webViewParams);
        mainLayout.addView(webLogoLayer);

        // Broadcaster Descriptor Label
        tvSub = new TextView(this);
        tvSub.setText("ULTRA 1080P 60FPS BROADCASTER");
        tvSub.setTextSize(10);
        tvSub.setPadding(0, 0, 0, 70);
        mainLayout.addView(tvSub);

        // 2. INPUT TARGET INGESTION FIELD
        etStreamUrl = new EditText(this);
        etStreamUrl.setHint("rtmp://a.rtmp.youtube.com/live2/YOUR_STREAM_KEY");
        etStreamUrl.setHintTextColor(Color.parseColor("#444446"));
        etStreamUrl.setTextColor(Color.WHITE);
        etStreamUrl.setTextSize(13);
        etStreamUrl.setPadding(40, 45, 40, 45);
        etStreamUrl.setBackgroundColor(Color.parseColor("#141416"));
        mainLayout.addView(etStreamUrl);

        LinearLayout.LayoutParams inputBtnParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        inputBtnParams.setMargins(0, 40, 0, 60);

        // 3. MASTER TRANSMISSION SYSTEM ENGINE CONTROL TOGGLE
        btnStreamToggle = new Button(this);
        btnStreamToggle.setText("START BROADCASTING");
        btnStreamToggle.setTextColor(Color.WHITE);
        btnStreamToggle.setTypeface(Typeface.DEFAULT_BOLD);
        btnStreamToggle.setLayoutParams(inputBtnParams);
        mainLayout.addView(btnStreamToggle);

        // 4. THEME CONTROL SELECTION INTERFACE CONTROLLERS
        tvThemesLabel = new TextView(this);
        tvThemesLabel.setText("DYNAMIC AMBIENT THEME MATRIX");
        tvThemesLabel.setTextSize(11);
        tvThemesLabel.setPadding(0, 20, 0, 20);
        mainLayout.addView(tvThemesLabel);

        HorizontalScrollView scrollThemesNode = new HorizontalScrollView(this);
        scrollThemesNode.setHorizontalScrollBarEnabled(false);
        LinearLayout innerThemesLayout = new LinearLayout(this);
        innerThemesLayout.setOrientation(LinearLayout.HORIZONTAL);

        for (String[] targetTheme : themePresets) {
            Button singleThemeTab = new Button(this);
            singleThemeTab.setText(targetTheme[0]);
            singleThemeTab.setTextSize(10);
            singleThemeTab.setTextColor(Color.WHITE);
            singleThemeTab.setBackgroundColor(Color.parseColor("#1C1C1E"));
            
            LinearLayout.LayoutParams horizontalParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            horizontalParams.setMargins(10, 0, 10, 0);
            singleThemeTab.setLayoutParams(horizontalParams);

            singleThemeTab.setOnClickListener(v -> triggerAccentTransformation(targetTheme[1]));
            innerThemesLayout.addView(singleThemeTab);
        }
        scrollThemesNode.addView(innerThemesLayout);
        mainLayout.addView(scrollThemesNode);

        // 5. OPENGL GRAPHICS LIVE BITRATE FILTERS CONTROLS
        tvFilters = new TextView(this);
        tvFilters.setText("LIVE GPU GRAPHICS FILTERS");
        tvFilters.setTextSize(11);
        tvFilters.setPadding(0, 60, 0, 20);
        mainLayout.addView(tvFilters);

        HorizontalScrollView scrollFiltersNode = new HorizontalScrollView(this);
        scrollFiltersNode.setHorizontalScrollBarEnabled(false);
        LinearLayout innerFiltersLayout = new LinearLayout(this);
        innerFiltersLayout.setOrientation(LinearLayout.HORIZONTAL);

        String[] processingFilterNames = {"Normal", "Vivid", "Classic", "Ocean", "4K Sharp"};
        for (String filterInstance : processingFilterNames) {
            Button individualFilterBtn = new Button(this);
            individualFilterBtn.setText(filterInstance.toUpperCase());
            individualFilterBtn.setTextSize(10);
            individualFilterBtn.setTextColor(Color.WHITE);
            individualFilterBtn.setBackgroundColor(Color.parseColor("#1C1C1E"));
            
            LinearLayout.LayoutParams horizontalParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            horizontalParams.setMargins(10, 0, 10, 0);
            individualFilterBtn.setLayoutParams(horizontalParams);

            individualFilterBtn.setOnClickListener(v -> {
                if (isLive) {
                    Intent intentPayload = new Intent(this, StreamService.class)
                            .setAction("CHANGE_FILTER")
                            .putExtra("FILTER_TYPE", filterInstance);
                    startService(intentPayload);
                    Toast.makeText(this, filterInstance + " Filter Injected", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Broadcast pipeline inactive!", Toast.LENGTH_SHORT).show();
                }
            });
            innerFiltersLayout.addView(individualFilterBtn);
        }
        scrollFiltersNode.addView(innerFiltersLayout);
        mainLayout.addView(scrollFiltersNode);

        // 6. OFFICIAL SOURCE TARGET ENDPOINT LINK HUB FOR COMPLIANCE 
        LinearLayout.LayoutParams anchorParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        anchorParams.setMargins(0, 100, 0, 0);

        btnGithub = new Button(this);
        btnGithub.setText("VIEW SOURCE ON GITHUB");
        btnGithub.setTextSize(11);
        btnGithub.setBackgroundColor(Color.TRANSPARENT);
        btnGithub.setLayoutParams(anchorParams);
        
        btnGithub.setOnClickListener(v -> {
            Intent routingWebIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Puspaaamm/Strec"));
            startActivity(routingWebIntent);
        });
        mainLayout.addView(btnGithub);

        // Initialize Dynamic Colors baseline system properties
        triggerAccentTransformation(currentAccentColor);
        setContentView(mainLayout);

        // Setup Stream Engagement Listener Configuration Core
        btnStreamToggle.setOnClickListener(v -> {
            if (!isLive) {
                String inputUri = etStreamUrl.getText().toString().trim();
                if (inputUri.isEmpty()) {
                    Toast.makeText(this, "Target ingestion URL required!", Toast.LENGTH_SHORT).show();
                    return;
                }
                MediaProjectionManager projectionManagerInstance = (MediaProjectionManager) getSystemService(MEDIA_PROJECTION_SERVICE);
                if (projectionManagerInstance != null) {
                    startActivityForResult(projectionManagerInstance.createScreenCaptureIntent(), SCREEN_CAPTURE_REQUEST);
                }
            } else {
                executePipelineTeardown();
            }
        });
    }

    /**
     * Runtime layout matrix color shifter engine.
     * @param requestedAccentColor target hex value applied to control parameters.
     */
    private void triggerAccentTransformation(String requestedAccentColor) {
        currentAccentColor = requestedAccentColor;
        int resolvedHexColor = Color.parseColor(requestedAccentColor);
        
        tvSub.setTextColor(Color.parseColor("#666668"));
        tvThemesLabel.setTextColor(resolvedHexColor);
        tvFilters.setTextColor(resolvedHexColor);
        
        if (!isLive) {
            btnStreamToggle.setBackgroundColor(resolvedHexColor);
        }
        btnGithub.setTextColor(resolvedHexColor);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SCREEN_CAPTURE_REQUEST && resultCode == RESULT_OK && data != null) {
            
            Intent broadcasterServiceIntent = new Intent(this, StreamService.class)
                    .putExtra("STREAM_URL", etStreamUrl.getText().toString().trim())
                    .putExtra("RESULT_CODE", resultCode)
                    .putExtra("DATA", data);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(broadcasterServiceIntent);
            } else {
                startService(broadcasterServiceIntent);
            }

            btnStreamToggle.setText("STOP LIVE STREAM");
            btnStreamToggle.setBackgroundColor(Color.parseColor("#2C2C2E"));
            isLive = true;

            // Sends App to Background to prioritize focus redirection on the gameplay layer
            Intent backgroundLauncherIntent = new Intent(Intent.ACTION_MAIN)
                    .addCategory(Intent.CATEGORY_HOME)
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(backgroundLauncherIntent);
        }
    }

    private void executePipelineTeardown() {
        StreamService.stopStreaming();
        stopService(new Intent(this, StreamService.class));
        btnStreamToggle.setText("START BROADCASTING");
        btnStreamToggle.setBackgroundColor(Color.parseColor(currentAccentColor));
        isLive = false;
        Toast.makeText(this, "Strec Pipeline Disengaged", Toast.LENGTH_SHORT).show();
    }
}
