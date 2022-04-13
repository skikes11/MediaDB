package com.example.mediaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Database database;
    ListView lvSong;
    ArrayList<Song> arrayListSong;
    SongAdapter songAdapter;
    ArrayList<Song> arrayListTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Mapping();
        arrayListSong = new ArrayList<>();
      //  databaseTestLayout();
        songAdapter = new SongAdapter(this, R.layout.song_line, arrayListSong);
        lvSong.setAdapter(songAdapter);
        CreateDataBase();
        lvSong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, MediaPlayerActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("position",i);
                bundle.putSerializable("arrayListSong", (Serializable) arrayListSong);
                intent.putExtra("bundle",bundle);
                startActivity(intent);
            }
        });

    }

    public void databaseTestLayout(){
        arrayListTest = new ArrayList<>();
        arrayListTest.add(new Song(1,"Bước qua mùa hạ", "Vũ.", "Sad Song", R.raw.buoc_qua_nhau,1));
        arrayListTest.add(new Song(1,"Bước qua mùa hạ", "Vũ.", "Sad Song", R.raw.buoc_qua_nhau,1));
        arrayListTest.add(new Song(1,"Bước qua mùa hạ", "Vũ.", "Sad Song", R.raw.buoc_qua_nhau,1));
        arrayListTest.add(new Song(1,"Bước qua mùa hạ", "Vũ.", "Sad Song", R.raw.buoc_qua_nhau,1));
        arrayListTest.add(new Song(1,"Bước qua mùa hạ", "Vũ.", "Sad Song", R.raw.buoc_qua_nhau,1));
        arrayListTest.add(new Song(1,"Bước qua mùa hạ", "Vũ.", "Sad Song", R.raw.buoc_qua_nhau,1));
        arrayListTest.add(new Song(1,"Bước qua mùa hạ", "Vũ.", "Sad Song", R.raw.buoc_qua_nhau,1));
        arrayListTest.add(new Song(1,"Bước qua mùa hạ", "Vũ.", "Sad Song", R.raw.buoc_qua_nhau,1));
        arrayListTest.add(new Song(1,"Bước qua mùa hạ", "Vũ.", "Sad Song", R.raw.buoc_qua_nhau,1));
        arrayListTest.add(new Song(1,"Bước qua mùa hạ", "Vũ.", "Sad Song", R.raw.buoc_qua_nhau,1));
        arrayListTest.add(new Song(1,"Bước qua mùa hạ", "Vũ.", "Sad Song", R.raw.buoc_qua_nhau,1));
        arrayListTest.add(new Song(1,"Bước qua mùa hạ", "Vũ.", "Sad Song", R.raw.buoc_qua_nhau,1));
    }


    public void  CreateDataBase(){
        database = new Database(this, "song.sqlite", null, 1);
        database.QueryData("CREATE TABLE IF NOT EXISTS Song(Id INTEGER PRIMARY KEY AUTOINCREMENT" +
               ", Name VARCHAR(55), Singer VARCHAR(55), Type VARCHAR(25) , FileMp3 VARCHAR(55) , IdPlayList INTEGER )");

        //     database.QueryData("CREATE TABLE IF NOT EXISTS PlayList(Id INTEGER PRIMARY KEY AUTOINCREMENT, Name VARCHAR(55) )");

      //  database.QueryData("INSERT INTO Song VALUES(null, 'Bad Liar', 'Imagine Dragons', 'Sad Song',' bad_liar', null)");

      //  database.QueryData("INSERT INTO Song VALUES(null, 'Bước qua mùa cô đơn', 'Vũ.' , 'Sad Song', 'buoc_qua_mua_co_don', null)");
     //   database.QueryData("INSERT INTO Song VALUES(null, 'Bước qua nhau', 'Vũ.' , 'Sad Song', 'buoc_qua_nhau', null)");
        /*
          database.QueryData("INSERT INTO Song VALUES(null, 'Chấm đen', 'DSK', 'Rap Song',' cham_den', null)");
          database.QueryData("INSERT INTO Song VALUES(null, 'Cho tôi lang thang', 'Ngọt && Đen Vâu' , 'Rap Song', 'cho_toi_lang_thang', null)");
          database.QueryData("INSERT INTO Song VALUES(null, 'Coconut', 'NULL' , 'Funny Song', 'coconut_song', null)");
          database.QueryData("INSERT INTO Song VALUES(null, 'Dancing with the ghost', 'Shasa Sloan', 'Sad Song',' dacing_with_the_ghost', null)");
          database.QueryData("INSERT INTO Song VALUES(null, 'Lạ lùng', 'Vũ.' , 'Sad Song', 'la_lung', null)");
          database.QueryData("INSERT INTO Song VALUES(null, 'Let her go', 'Passenger' , 'Sad Song', 'let_her_go', null)");
        */
      // database.QueryData("drop table Song");


        Cursor dataListSongs = database.GetData("SELECT * FROM Song");
        Context context = getApplicationContext();
        while(dataListSongs.moveToNext()){
            int id = dataListSongs.getInt(0);
            String name = dataListSongs.getString(1);
            String singer = dataListSongs.getString(2);
            String type = dataListSongs.getString(3);
            String fileMP3= dataListSongs.getString(4);
            int idPlaylist = dataListSongs.getInt(5);
            arrayListSong.add(new Song(id, name, singer, type, context.getResources().getIdentifier(fileMP3, "raw", context.getPackageName()) , idPlaylist));

        }
        songAdapter.notifyDataSetChanged();
    }


    public void Mapping(){
        lvSong  = (ListView) findViewById(R.id.listViewSong);

    }

}