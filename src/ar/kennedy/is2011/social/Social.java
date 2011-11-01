package ar.kennedy.is2011.social;

import java.util.List;

import ar.kennedy.is2011.spring.SpringContext;

/**
 * @author mlabarinas
 */
public class Social {
	
	private Social() { }
	
	@SuppressWarnings("unchecked")
	public static String addLinks(String url, String title) throws Exception {
		StringBuilder links = new StringBuilder();
		
		for(ShareItf share : (List<ShareItf>) SpringContext.getBean("socials")) {
			links.append(share.addLink(url, title));
		}
		
		return links.toString();
	}
	
}