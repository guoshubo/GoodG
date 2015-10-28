package com.GoodG.app.activity;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.GoodG.app.R;
import com.GoodG.app.model.GetMusicDB;
import com.GoodG.app.model.Mp3Info;

public class MainActivity extends Activity {
	private GetMusicDB getMusicDB;
	private List<Mp3Info> mp3Infol = new ArrayList<Mp3Info>();
	private List<HashMap<String, String>> mp3list = new ArrayList<HashMap<String, String>>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState) ;
		setContentView(R.layout.activity_main);
		mp3Infol = getMusicDB.getMp3Infos(this);
		mp3list = getMusicDB.setListAdpter(mp3Infol);
		SimpleAdapter mAdapter = new SimpleAdapter(this,mp3list,R.layout.music_list_item_layout,new String[] { "title", "Artist", "duration" },new int[] { R.id.music_title, R.id.music_Artist, R.id.music_duration });
		ListView mMusiclist = (ListView) findViewById(R.id.music_list);
		mMusiclist.setAdapter(mAdapter);
	}
}
