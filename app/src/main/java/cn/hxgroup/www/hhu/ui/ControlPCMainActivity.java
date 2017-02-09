//package cn.hxgroup.www.hhu.ui;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import android.app.Activity;
//import android.net.wifi.ScanResult;
//import android.net.wifi.WifiManager;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.Menu;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.AdapterView;
//import android.widget.AdapterView.OnItemClickListener;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.ListView;
//import android.widget.Toast;
//
//import com.wifi.utils.WifiPswDialog;
//import com.wifi.utils.WifiPswDialog.OnCustomDialogListener;
//import com.wifi.utils.WifiUtils;
//
//public class ControlPCMainActivity extends Activity {
//	private String wifiPassword = null;
//	private Button wifiSearchButton;
//	private WifiUtils localWifiUtils;
//	private List<ScanResult> wifiResultList;
//	private List<String> wifiListString = new ArrayList<String>();
//	private ListView wifiSearchListView;
//	private ArrayAdapter<String> arrayWifiAdapter;
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_control_pcmain);
//
//		wifiSearchButton = (Button)findViewById(R.id.wifiSearchButton);
//		WIFIButtonListener wifiButtonListener = new WIFIButtonListener();
//		wifiSearchButton.setOnClickListener(wifiButtonListener);
//		wifiSearchListView = (ListView)findViewById(R.id.wifiListView);
//		arrayWifiAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,wifiListString);
//		wifiSearchListView.setAdapter(arrayWifiAdapter);
//		ListOnItemClickListener wifiListListener = new ListOnItemClickListener();
//		wifiSearchListView.setOnItemClickListener(wifiListListener);
//
//		localWifiUtils = new WifiUtils(ControlPCMainActivity.this);
//	}
//
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.control_pcmain, menu);
//		return true;
//	}
//
//
//	class WIFIButtonListener implements OnClickListener{
//		@Override
//		public void onClick(View view) {
//			wifiListString.clear();
//			localWifiUtils.WifiOpen();
//			localWifiUtils.WifiStartScan();
//			//0���ڹر�,1WIFi������,2���ڴ�,3����,4״̬����zhi
//			while(localWifiUtils.WifiCheckState() != WifiManager.WIFI_STATE_ENABLED){//�ȴ�Wifi����
//				Log.i("WifiState",String.valueOf(localWifiUtils.WifiCheckState()));
//			}
//			try {
//					Thread.sleep(3000);//����3s������������ڳ����״ο���WIFIʱ�򣬴���getScanResults���wifiResultList.size()�����쳣
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			wifiResultList = localWifiUtils.getScanResults();
//			localWifiUtils.getConfiguration();
//			if(wifiListString != null){
//				Log.i("WIFIButtonListener","dataChange");
//				scanResultToString(wifiResultList,wifiListString);
//			}
//		}
//	}
//
//	class ListOnItemClickListener implements OnItemClickListener{
//		String wifiItemSSID = null;
//		private View selectedItem;
//		@Override
//		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
//				long arg3) {
//			Log.i("ListOnItemClickListener","start");
//			selectedItem = arg1;
//			arg1.setBackgroundResource(R.color.gray);//�����Item�������
//			String wifiItem = arrayWifiAdapter.getItem(arg2);//���ѡ�е��豸
//			String []ItemValue = wifiItem.split("--");
//			wifiItemSSID = ItemValue[0];
//			Log.i("ListOnItemClickListener",wifiItemSSID);
//			int wifiItemId = localWifiUtils.IsConfiguration("\""+wifiItemSSID+"\"");
//			Log.i("ListOnItemClickListener",String.valueOf(wifiItemId));
//			if(wifiItemId != -1){
//				if(localWifiUtils.ConnectWifi(wifiItemId)){//����ָ��WIFI
//					arg1.setBackgroundResource(R.color.green);
//				}
//			}
//			else{//û�����ú���Ϣ������
//				WifiPswDialog pswDialog = new WifiPswDialog(ControlPCMainActivity.this,new OnCustomDialogListener() {
//					@Override
//					public void back(String str) {
//						wifiPassword = str;
//						if(wifiPassword != null){
//							int netId = localWifiUtils.AddWifiConfig(wifiResultList,wifiItemSSID, wifiPassword);
//							Log.i("WifiPswDialog",String.valueOf(netId));
//							if(netId != -1){
//								localWifiUtils.getConfiguration();//�����������Ϣ��Ҫ���µõ�������Ϣ
//								if(localWifiUtils.ConnectWifi(netId)){
//									selectedItem.setBackgroundResource(R.color.green);
//								}
//							}
//							else{
//								Toast.makeText(ControlPCMainActivity.this, "�������Ӵ���", Toast.LENGTH_SHORT).show();
//								selectedItem.setBackgroundResource(R.color.burlywood);
//							}
//						}
//						else{
//							selectedItem.setBackgroundResource(R.color.burlywood);
//						}
//					}
//				});
//				pswDialog.show();
//			}
//		}
//	}
//	//ScanResult����תΪString
//	public void scanResultToString(List<ScanResult> listScan,List<String> listStr){
//		for(int i = 0; i <listScan.size(); i++){
//			ScanResult strScan = listScan.get(i);
//			String str = strScan.SSID+"--"+strScan.BSSID;
//			boolean bool = listStr.add(str);
//			if(bool){
//				arrayWifiAdapter.notifyDataSetChanged();//��ݸ���,ֻ�ܵ���Item���£����ܹ�����List����
//			}
//			else{
//				Log.i("scanResultToSting","fail");
//			}
//			Log.i("scanResultToString",listStr.get(i));
//		}
//	}
//	/*private BroadcastReceiver WifiBroadcastRec = new BroadcastReceiver(){
//	@Override
//	public void onReceive(Context context, Intent intent) {
//		String wifiAction = intent.getAction();
//		if(WifiManager.WIFI_STATE_CHANGED_ACTION.equals(wifiAction)){
//			int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE,WifiManager.WIFI_STATE_ENABLED);
//			if(wifiState == WifiManager.WIFI_STATE_ENABLED){
//				try {
//					Thread.sleep(3000);//����3s������������ڳ����״ο���WIFIʱ�򣬴���getScanResults���wifiResultList.size()�����쳣
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//				wifiResultList = localWifiUtils.getScanResults();
//				localWifiUtils.getConfiguration();
//				if(wifiListString != null){
//					scanResultToString(wifiResultList,wifiListString);
//				}
//			}
//		}
//	  }
//	};*/
//}
