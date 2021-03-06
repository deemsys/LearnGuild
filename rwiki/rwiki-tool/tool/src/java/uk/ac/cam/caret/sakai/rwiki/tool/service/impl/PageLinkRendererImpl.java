/**********************************************************************************
 * $URL: https://source.sakaiproject.org/svn/rwiki/tags/sakai-2.9.2/rwiki-tool/tool/src/java/uk/ac/cam/caret/sakai/rwiki/tool/service/impl/PageLinkRendererImpl.java $
 * $Id: PageLinkRendererImpl.java 20354 2007-01-17 10:30:57Z ian@caret.cam.ac.uk $
 ***********************************************************************************
 *
 * Copyright (c) 2003, 2004, 2005, 2006 The Sakai Foundation.
 *
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.opensource.org/licenses/ecl1.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 **********************************************************************************/

package uk.ac.cam.caret.sakai.rwiki.tool.service.impl;

import uk.ac.cam.caret.sakai.rwiki.service.api.PageLinkRenderer;
import uk.ac.cam.caret.sakai.rwiki.tool.bean.ViewBean;
import uk.ac.cam.caret.sakai.rwiki.utils.NameHelper;
import uk.ac.cam.caret.sakai.rwiki.utils.XmlEscaper;

/**
 * Worksite page link rendered that renders links with create steps
 * 
 * @author andrew
 */
public class PageLinkRendererImpl implements PageLinkRenderer
{

	private boolean cacheable = true;

	private boolean useCache = true;

	public String localRealm;

	public String localSpace;

	public PageLinkRendererImpl(String localRealm)
	{
		this(localRealm, localRealm);
	}

	public PageLinkRendererImpl(String localSpace, String localRealm)
	{
		this.localSpace = localSpace;
		this.localRealm = localRealm;
	}

	public void appendLink(StringBuffer buffer, String name, String view)
	{	
		this.appendLink(buffer, name, view, null);
	}

	public void appendLink(StringBuffer buffer, String name, String view,
			String anchor)
	{
		name = NameHelper.globaliseName(name, localSpace);
		ViewBean vb = new ViewBean(name, localRealm);
		if (anchor != null && !"".equals(anchor)) 
		{
			vb.setAnchor(anchor);
		}
		buffer.append("<a href=\"" + XmlEscaper.xmlEscape(vb.getViewUrl())
				+ "\">" + XmlEscaper.xmlEscape(view) + "</a>");
	}

	public void appendCreateLink(StringBuffer buffer, String name, String view)
	{
		cacheable = false;
		name = NameHelper.globaliseName(name, localSpace);
		ViewBean vb = new ViewBean(name, localRealm);
		buffer.append("<a href=\"" + XmlEscaper.xmlEscape(vb.getViewUrl())
				+ "\">" + XmlEscaper.xmlEscape(view) + "?</a>");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see uk.ac.cam.caret.sakai.rwiki.service.api.PageLinkRenderer#isCachable()
	 */
	public boolean isCachable()
	{
		return cacheable;
	}

	public boolean canUseCache()
	{
		return useCache;
	}

	public void setCachable(boolean cachable)
	{
		this.cacheable = cachable;
	}

	public void setUseCache(boolean b)
	{
		useCache = b;
	}

	public void appendLink(StringBuffer buffer, String name, String view, String anchor, boolean autoGenerated)
	{
		this.appendLink(buffer, name, view, anchor);
	}

}
