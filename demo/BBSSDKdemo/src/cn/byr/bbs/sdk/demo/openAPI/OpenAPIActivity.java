package cn.byr.bbs.sdk.demo.openAPI;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Set;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import cn.byr.bbs.sdk.demo.R;

public class OpenAPIActivity extends Activity implements OnItemClickListener {
	
    private static final String ACTIVITY_PACKAGE_NAME = "cn.byr.bbs.sdk.demo.openAPI";
    
    /** �� MAP ���ڴ�� OpenAPI �����Լ���Ӧ�� DEMO Activity �� */
    private static final LinkedHashMap<String, String> sAPIList = 
            new LinkedHashMap<String, String>();
    
    /**
     * ��ʼ�����ڴ�� OpenAPI �����Լ���Ӧ�� DEMO Activity ���� MAP��
     */
    static {
        sAPIList.put("�û��ӿ� - UserQueryApi",    "UserAPIActivity");
        sAPIList.put("�����ӿ�- SectionApi",       "SectionAPIActivity");
        sAPIList.put("����ӿ� - BoardApi",     	"BoardAPIActivity");
        sAPIList.put("���½ӿ� - UserQueryApi",    "UserQuertAPIActivity");
        sAPIList.put("���½ӿ� - UserQueryApi",    "UserQuertAPIActivity");
        sAPIList.put("�����ӿ� - UserQueryApi",    "UserQuertAPIActivity");
        sAPIList.put("�ղؼнӿ� - UserQueryApi",    "UserQuertAPIActivity");
        sAPIList.put("���ѽӿ� - UserQueryApi",    "UserQuertAPIActivity");
        sAPIList.put("Widget�ӿ�  - UserQueryApi",    "UserQuertAPIActivity");
        sAPIList.put("�������ӿ� - UserQueryApi",    "UserQuertAPIActivity");
        sAPIList.put("ͶƱ�ӿ� - UserQueryApi",    "UserQuertAPIActivity");
    }
    
    private ListView mApiListView;
    
    /**
     * @see {@link Activity#onCreate}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_openapi);
        
        mApiListView = (ListView)findViewById(R.id.api_list);
        mApiListView.setAdapter(new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, getAPINameList()));
        mApiListView.setOnItemClickListener(this);
    }
    
    /**
     * @see {@link AdapterView.OnItemClickListener#onItemClick}
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (view instanceof TextView) {
            String className = sAPIList.get(((TextView)view).getText().toString());
            
            Intent intent = new Intent();
            intent.setClassName(getPackageName(), ACTIVITY_PACKAGE_NAME + "." + className);
            try {
                startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * ��ȡ OpenAPI �����б�
     * @return OpenAPI �����б�
     */
    private ArrayList<String> getAPINameList() {
        ArrayList<String> nameList = new ArrayList<String>();
        Set<String> nameSet = sAPIList.keySet();
        for (String name : nameSet) {
            nameList.add(name);
        }
        return nameList;
    }	

}
