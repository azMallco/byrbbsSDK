package cn.byr.bbs.sdkdemo.openAPI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Set;

import cn.byr.bbs.sdkdemo.R;


public class OpenAPIActivity extends Activity implements OnItemClickListener {

    private static final String ACTIVITY_PACKAGE_NAME = "cn.byr.bbs.sdkdemo.openAPI";

    /**
     * 该 MAP 用于存放 OpenAPI 名称以及对应的 DEMO Activity 名
     */
    private static final LinkedHashMap<String, String> sAPIList =
            new LinkedHashMap<String, String>();

    /**
     * 初始化用于存放 OpenAPI 名称以及对应的 DEMO Activity 名的 MAP。
     */
    static {
        sAPIList.put("用户接口 - UserQueryApi", "UserAPIActivity");
        sAPIList.put("分区接口- SectionApi", "SectionAPIActivity");
        sAPIList.put("版面接口 - BoardApi", "BoardAPIActivity");
        sAPIList.put("文章接口 - ArticleApi", "ArticleAPIActivity");
        sAPIList.put("附件接口 - AttachmentApi", "AttachmentAPIActivity");
        sAPIList.put("信件接口 - MailApi", "MailAPIActivity");
        sAPIList.put("收藏夹接口 - FavApi", "FavAPIActivity");
        sAPIList.put("搜索接口 - SearchApi", "SearchAPIActivity");
        sAPIList.put("提醒接口 - ReferApi", "ReferAPIActivity");
        sAPIList.put("Widget接口  - WidgetApi", "WidgetAPIActivity");
        sAPIList.put("黑名单接口 - BlacklistApi", "BlacklistAPIActivity");
        sAPIList.put("投票接口 - VoteApi", "VoteAPIActivity");
    }

    private ListView mApiListView;

    /**
     * @see {@link android.app.Activity#onCreate}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_openapi);

        mApiListView = (ListView) findViewById(R.id.api_list);
        mApiListView.setAdapter(new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, getAPINameList()));
        mApiListView.setOnItemClickListener(this);
    }

    /**
     * @see {@link android.widget.AdapterView.OnItemClickListener#onItemClick}
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (view instanceof TextView) {
            String className = sAPIList.get(((TextView) view).getText().toString());

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
     * 获取 OpenAPI 名称列表。
     *
     * @return OpenAPI 名称列表
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
