package com.GoodG.app.activity;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.IBinder;
import android.util.Log;

import com.GoodG.app.domain.AppContant;

public class PlayService extends Service{
	//�ǵ� �������Ҫ��AndroidMainfest.xml��ע��
	
	private MediaPlayer mediaPlayer = new MediaPlayer(); //ý�岥��������
	private String path;		//�����ļ�·��
	private boolean isPause;  //��ͣ״̬

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	//onStartCommand����ÿ�����������ʱ�����
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
	
	//��������
	public void play( int position) {
		try {
			mediaPlayer.reset();
			mediaPlayer.setDataSource(path);
			mediaPlayer.prepare();
			//ע��һ��������
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
			//���position��Ϊ0�������seekTo�ӹ̶�λ�ÿ�ʼ����
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
