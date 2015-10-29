package com.GoodG.app.activity;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.IBinder;
import android.util.Log;

import com.GoodG.app.domain.AppContant;

public class PlayService extends Service{
	//记得 这个服务要到AndroidMainfest.xml红注册
	
	private MediaPlayer mediaPlayer = new MediaPlayer(); //媒体播放器对象
	private String path;		//音乐文件路径
	private boolean isPause;  //暂停状态

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	//onStartCommand会在每次启动服务的时候调用
	public int onStartCommand(Intent intent, int flags, int startId) {
		if( mediaPlayer.isPlaying() ){
			stop();
		}
		path = intent.getStringExtra("url");
		Log.d("onStartCommand","path is " + path);
		int msg = intent.getIntExtra("MSG", 0);
		if(msg == AppContant.MusicPlayStatus.PLAY_MSG) {
			play(0);
		}
		return flags;
	}
	
	//播放音乐
	public void play( int position) {
		try {
			mediaPlayer.reset();
			mediaPlayer.setDataSource(path);
			mediaPlayer.prepare();
			//注册一个监听器
			mediaPlayer.setOnPreparedListener(new PreparedListener(position));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private final class PreparedListener implements OnPreparedListener   {
		private int position;
		
		public PreparedListener(int position) {
			this.position = position;
		}
		@Override
		public void onPrepared(MediaPlayer mp) {
			mediaPlayer.start();
			//如果position不为0，则调用seekTo从固定位置开始播放
			if(position > 0) {
				mediaPlayer.seekTo(position);
			}
			
		}
		
	}
	
	
	@Override
	public void onDestroy() {
		if(mediaPlayer != null) {
			mediaPlayer.stop();
			mediaPlayer.release();
		}
	}
	public void stop() {
		if(mediaPlayer != null) {
			mediaPlayer.stop();
			try {
				mediaPlayer.prepare();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	

}
