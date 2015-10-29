package com.GoodG.app.activity;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.GoodG.app.R;
import com.GoodG.app.domain.AppContant;
import com.GoodG.app.model.GetMusicDB;
import com.GoodG.app.model.Mp3Info;




public class MainActivity extends Activity {

	private List<Mp3Info> mp3Infol = new ArrayList<Mp3Info>();
	private List<HashMap<String, String>> mp3list = new ArrayList<HashMap<String, String>>();

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState) ;
		setContentView(R.layout.activity_main);
		mp3Infol = GetMusicDB.getMp3Infos(MainActivity.this);
		mp3list = GetMusicDB.setListAdpter(mp3Infol);
		SimpleAdapter mAdapter = new SimpleAdapter(this,mp3list,R.layout.music_list_item_layout,new String[] { "title", "Artist", "duration" },new int[] { R.id.music_title, R.id.music_Artist, R.id.music_duration });
		ListView mMusicList = (ListView) findViewById(R.id.music_list);
		mMusicList.setAdapter(mAdapter);
		
		mMusicList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				Log.d("OnItemClick","clicked" );
				if (mp3Infol != null) {
					Mp3Info mp3Click = mp3Infol.get(position);
					Intent intent = new Intent();
					intent.putExtra("url",mp3Click.getUrl());
					Log.d("OnItemClick","title is " + mp3Click.getTitle());
					intent.putExtra("MSG", AppContant.MusicPlayStatus.PLAY_MSG);
					Log.d("OnItemClick","Play_msg is " + AppContant.MusicPlayStatus.PLAY_MSG);
					intent.setClass(MainActivity.this,PlayService.class);  //调用服务，后台播放音乐
					startService(intent);
				}	
			}
		});
	}
	

}
