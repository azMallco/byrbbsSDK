/*
 * Copyright (C) 2010-2014 dss886
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.byrbbs.sdk.api.model;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 鐢ㄦ埛淇＄缁撴瀯浣�?
 * @author dss886
 * @since 2014-9-7
 */
public class Mailbox {
	
	/** 鏄惁鏈夋柊閭�? */
	public boolean new_mail;
	/** 淇＄鏄惁宸叉�? */
	public boolean full_mail;
	/** 淇＄宸茬敤绌洪�? */
	public String space_used;
	/** 褰撳墠鐢ㄦ埛鏄惁鑳藉彂淇� */
	public boolean can_send;
	/** 
	 * 淇＄绫诲�?�鎻忚堪锛屽寘鎷細�?朵欢绠憋紝鍙戜欢绠憋紝搴熺焊绡擄�?
	 * 浠呭瓨鍦ㄤ簬/mail/:box涓�
	 *  */
	public String description;
	/** 
	 * 褰撳墠淇＄鎵�鍖呭惈鐨勪俊浠跺厓鏁版嵁鏁扮粍锛�?
	 * 浠呭瓨鍦ㄤ簬/mail/:box涓�
	 *  */
	public List<Mail> mails;
	/** 
	 * 褰撳墠淇＄鐨勫垎椤典俊鎭�?
	 * 浠呭瓨鍦ㄤ簬/mail/:box涓�
	 *  */
	public Pagination pagination;
	
	public static Mailbox parse(String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            return Mailbox.parse(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        
        return null;
    }
	
	public static Mailbox parse(JSONObject jsonObject) {
        if (null == jsonObject) {
            return null;
        }
        Mailbox mailbox = new Mailbox();
        mailbox.new_mail = jsonObject.optBoolean("new_mail", false);
        mailbox.full_mail = jsonObject.optBoolean("full_mail", false);
        mailbox.space_used = jsonObject.optString("space_used", "");
        mailbox.can_send = jsonObject.optBoolean("can_send", true);
        mailbox.description = jsonObject.optString("description", "");
        JSONArray jsonMails = jsonObject.optJSONArray("article");
        for(int i = 0; i < jsonMails.length(); i++){
        	mailbox.mails.add(Mail.parse(jsonMails.optJSONObject(i)));
		}
        mailbox.pagination = Pagination.parse(jsonObject.optJSONObject("pagination"));
        return mailbox;
	}
}
