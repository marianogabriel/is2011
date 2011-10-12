package ar.kennedy.is2011.social;

import java.net.URLEncoder;

/**
 * @author mlabarinas
 */
public class ShareTwitter implements ShareItf {

	private final String URL_BASE = "https://twitter.com/share?original_referer=##URL_TO_SHARE##&source=tweetbutton&text=##TITLE##&url=##URL_TO_SHARE##";
	
	public String addLink(String url, String title) throws Exception {
		return getLInkTemplate().replace("##URL##", URL_BASE.replace("##URL_TO_SHARE##", URLEncoder.encode(url,"UTF-8")).replace("##TITLE##", title));
	}

	private String getLInkTemplate() {
		return "<a href=\"##URL##\" target=\"_BLANK\"><img src=\"/images/share_twitter.jpeg\"></a>";
	}
	
}