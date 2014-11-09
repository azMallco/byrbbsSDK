package cn.byrbbs.sdk.api;

import android.text.TextUtils;
import cn.byrbbs.sdk.auth.Oauth2AccessToken;
import cn.byrbbs.sdk.net.BBSParameters;
import cn.byrbbs.sdk.net.RequestListener;

/**
 * VOTE API
 * @author ALSO
 *
 */
public class VoteApi extends BaseApi {

	private String VOTE_URL = BASE_URL + "/vote/";
	public VoteApi(Oauth2AccessToken accessToken) {
		super(accessToken);
	}
	
	/**
	 * get vote list
	 * @param category must be one of below 5
	 * 		<li> "me"  => current user's vote list</li>
	 * 		<li> "join" => list of current user's joined </li>
	 * 		<li> "new" => votes(still open) in reverse<b>(chronological)</b> order list</li>
	 * 		<li> "hot" => votes(still open) in reverse<b>(number of participants)</b> order list</li>
	 * 		<li> "all" => votes(all) in reverse chronological order list</li>
	 * @param listener
	 */
	public void voteList(String category, RequestListener listener){
		if(!(category.equals("me") 
				|| category.equals("join")
				|| category.equals("new")
				|| category.equals("hot")
				|| category.equals("all"))){
			return;
		}
		
		String url = VOTE_URL + "category/" + category;
		asyncRequest(url, HTTP_GET, null, listener);
	}
	
	/**
	 * query sb's vote history
	 * @param userid
	 * @param listener
	 */
	public void queryVoteList(String userid, RequestListener listener){
		if(TextUtils.isEmpty(userid)){ return; }
		String url = VOTE_URL + "category/list";
		
		BBSParameters param = new BBSParameters();
		param.put("u", userid);
		asyncRequest(url, HTTP_GET, param, listener);
		
	}
	
	/**
	 * Vote
	 * @param vid
	 * @param viid array of vote options
	 * @param isMulti true=>multiple choice
	 * @param listener
	 */
	public void vote(int vid, int[] viid, boolean isMulti, RequestListener listener){
		if(viid.length == 0){ return; }
		BBSParameters param = new BBSParameters();
		if(!isMulti){
			param.put("vote", viid[0]);
		}else if(viid.length > 0){
			for(int i = 0; i < viid.length; i++){
				param.put("vote[" + i + "]", viid[i] + "");
			}
		} else { return; } 
		String url = VOTE_URL + vid;
		asyncRequest(url, HTTP_POST, param, listener);
		
	}

}
