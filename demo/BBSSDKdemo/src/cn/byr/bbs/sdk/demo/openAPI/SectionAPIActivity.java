package cn.byr.bbs.sdk.demo.openAPI;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import cn.byr.bbs.sdk.demo.AccessTokenKeeper;
import cn.byr.bbs.sdk.demo.R;
import cn.byrbbs.sdk.api.SectionApi;
import cn.byrbbs.sdk.api.model.Section;
import cn.byrbbs.sdk.auth.Oauth2AccessToken;
import cn.byrbbs.sdk.exception.BBSException;
import cn.byrbbs.sdk.net.RequestListener;
import cn.byrbbs.sdk.utils.LogUtil;

public class SectionAPIActivity extends Activity implements OnItemClickListener{
    private static final String TAG = SectionAPIActivity.class.getName();
    
	private ListView mFuncListView;
    /** func list */
    private String[] mFuncList;

    private SectionApi mSectionApi;
    
    private Oauth2AccessToken mAccessToken;
    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.openapi_item);
        
        mAccessToken = AccessTokenKeeper.readAccessToken(this);
        mSectionApi = new SectionApi(mAccessToken);
        
        // ��ȡ�����б�
        mFuncList = getResources().getStringArray(R.array.section_func_list);
        // ��ʼ�������б� ListView
        mFuncListView = (ListView)findViewById(R.id.api_func_list);
        mFuncListView.setAdapter(new ArrayAdapter<String>(
        		this, android.R.layout.simple_list_item_1, mFuncList));
        mFuncListView.setOnItemClickListener(this);       
	}

	private RequestListener mListener = new RequestListener(){
			@Override
			public void onComplete(String response) {
		            if (!TextUtils.isEmpty(response)) {
		                LogUtil.i(TAG, response);
		                
		                new Section();
						Section section = Section.parse(response);
		                if (section != null) {
		                    Toast.makeText(SectionAPIActivity.this, 
		                            "Success! section des: " + section.description, 
		                            Toast.LENGTH_LONG).show();
		                } else {
		                    Toast.makeText(SectionAPIActivity.this, response, Toast.LENGTH_LONG).show();
		                }
		            }
		        }

				@Override
				public void onException(BBSException e) {
					// TODO Auto-generated method stub
		            LogUtil.e(TAG, e.getMessage());
//		            Toast.makeText(SectionActivity.this, info.toString(), Toast.LENGTH_LONG).show();
				}	
	};
	
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		if (view instanceof TextView) {
            if (mAccessToken != null && mAccessToken.isSessionValid()) {
                switch (position) {
                case 0:
                	mSectionApi.getRootSection(mListener);
                    break;
                    
                case 1:
                	mSectionApi.getSection("3", mListener);
                    break;
                    
                default:
                    break;
                }
            } else {
                Toast.makeText(SectionAPIActivity.this, 
                        R.string.bbsSDK_token_empty, 
                        Toast.LENGTH_LONG).show();
            }// else
        }// if( view)
	}// func
}
