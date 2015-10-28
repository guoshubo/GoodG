package com.GoodG.app.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;



public class GetMusicDB {
	
	
	Context context;
	public static List<Mp3Info> getMp3Infos(Context context) {
		Cursor cursor = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,null,null,null,MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
		 List<Mp3Info> mp3Infos = new ArrayList<Mp3Info>();
	

		if(cursor.moveToFirst()) {

			do {
			Mp3Info mp3Info = new Mp3Info();
			if( cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.IS_MUSIC)) == 0) {
				continue;
			}
			mp3Info.setId(cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media._ID)) );  //����ID
			Log.d("Music","music id id " + mp3Info.getId());
			mp3Info.setTitle(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)) ); //����
			Log.d("Music","music title is " + mp3Info.getTitle());
			
			if("<unknown>".equals(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST))) ){
				mp3Info.setArtist("���޴���");
			} else  mp3Info.setArtist(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)) ); //����
			
			
			mp3Info.setDuration(cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION)) );//ʱ��  
			
		    mp3Info.setSize(cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.SIZE)) ); //�ļ���С  
		    
		    mp3Info.setUrl(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA)) );  //�ļ�·��  
		    
		    mp3Infos.add(mp3Info);

			} while(cursor.moveToNext());
		}
		cursor.close();
		return mp3Infos;
	}
	
	/**
	 * ����б�
	 * @param mp3Infos
	 */
	public static List<HashMap<String, String>> setListAdpter(List<Mp3Info> mp3Infos) {
		List<HashMap<String, String>> mp3list = new ArrayList<HashMap<String, String>>();
		for (Iterator iterator = mp3Infos.iterator(); iterator.hasNext();) {
			Mp3Info mp3Info = (Mp3Info) iterator.next();
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("title", mp3Info.getTitle());
			map.put("Artist", mp3Info.getArtist());
			map.put("duration", String.valueOf(mp3Info.getDuration()));
			map.put("size", String.valueOf(mp3Info.getSize()));
			map.put("url", mp3Info.getUrl());
			mp3list.add(map);
			Log.d("hashmap","music title aaa is "+mp3Info.getTitle());
			
		}
		return mp3list;
	
	}


}
