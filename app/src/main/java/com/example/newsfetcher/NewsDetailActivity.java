package com.example.newsfetcher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class NewsDetailActivity extends AppCompatActivity {
    private TextView titleTV , subTittleTV , contentTV ;
    private ImageView titleIV ;
    private Button fullNewsButton ;
    String title,desc,content,imageURL,url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        title = getIntent().getStringExtra("title");
        desc = getIntent().getStringExtra("desc");
        content = getIntent().getStringExtra("content");
        imageURL = getIntent().getStringExtra("image");
        url = getIntent().getStringExtra("url");
        titleTV = findViewById(R.id.idTVTitle);
        subTittleTV = findViewById(R.id.idTVSubDesc);
        fullNewsButton = findViewById(R.id.idBtnReadNews);
        contentTV = findViewById(R.id.idTVContent);
        titleIV = findViewById(R.id.idIVNews);
        titleTV.setText(title);
        subTittleTV.setText(desc);
        contentTV.setText(content);
        Picasso.get().load(imageURL).into(titleIV);
        fullNewsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
    }
}