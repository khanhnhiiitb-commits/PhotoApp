package thanhdnh.ueh.edu.article_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
  public GridView gridview;

  private AdapterView.OnItemClickListener onitemclick = new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
      Intent intent = new Intent(getBaseContext(), ViewUserActivity.class);
      intent.putExtra("id", gridview.getAdapter().getItemId(position));
      startActivity(intent);
    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    getSupportActionBar().hide();

    gridview = findViewById(R.id.gridview);
    new UserData(getBaseContext(), gridview).loadData("https://gist.githubusercontent.com/khanhnhiiitb-commits/0160928f8649342ea82fce19845b93a7/raw/efb466f7b1c41bab8c53fe868f7a49ee3795f975/gistfile1.txt", this);
    gridview.setOnItemClickListener(onitemclick);
  }

}
