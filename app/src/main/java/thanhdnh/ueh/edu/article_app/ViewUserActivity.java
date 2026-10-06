package thanhdnh.ueh.edu.article_app;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class ViewUserActivity extends AppCompatActivity {
  ImageView iv_detail;
  TextView tv_detail_title, tv_detail_description;
  ProgressBar progressBar;
  Button btn_download;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_view_article);
    if (getSupportActionBar() != null) {
      getSupportActionBar().hide();
    }

    // Ánh xạ các View
    iv_detail = findViewById(R.id.iv_detail);
    tv_detail_title = findViewById(R.id.tv_detail_title);
    tv_detail_description = findViewById(R.id.tv_detail_description);
    progressBar = findViewById(R.id.progressBar);
    btn_download = findViewById(R.id.btn_download);

    int position = (int) getIntent().getLongExtra("id", 0);

    if (UserData.data != null && UserData.data.getUsers() != null) {
      User user = UserData.data.getUsers().get(position);

      if (user != null) {
        if (user.getUrl_profile() != null && !user.getUrl_profile().isEmpty()) {
          Picasso.get().load(user.getUrl_profile()).resize(400, 500).centerCrop().into(iv_detail);
        }

        tv_detail_title.setText(user.getUname());
        tv_detail_description.setText(user.getShort_bio());

        btn_download.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            if (user.getUrl_profile() != null && !user.getUrl_profile().isEmpty()) {
              progressBar.setVisibility(View.VISIBLE);
              progressBar.setProgress(0);

              Handler mainHandler = new Handler(Looper.getMainLooper());

              Toast.makeText(ViewUserActivity.this, "Đang tải ảnh...", Toast.LENGTH_SHORT).show();


              Downloader.downloadWithProgress(
                      user.getUrl_profile(),
                      mainHandler,
                      ViewUserActivity.this,
                      getCacheDir(),
                      progressBar,
                      iv_detail
              );
            }
          }
        });
      }
    }
  }
}