package com.example.mediaapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class SongAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Song> songList;

    public SongAdapter(Context context, int layout, List<Song> songList) {
        this.context = context;
        this.layout = layout;
        this.songList = songList;
    }

    @Override
    public int getCount() {
        return songList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    private class ViewHolder{
        ImageView imageViewSong;
        TextView textViewSongAndSinger;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if(view==null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            holder.imageViewSong = (ImageView) view.findViewById(R.id.imageViewSongIcon);
            holder.textViewSongAndSinger = (TextView) view.findViewById((R.id.textViewSongAndSinger));
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        Song song = songList.get(i);
        holder.textViewSongAndSinger.setText(song.getName()+ " - " + song.getSinger() );
        return view;
    }
}
