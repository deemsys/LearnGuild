/**********************************************************************************
 * $URL: https://source.sakaiproject.org/svn/rwiki/tags/sakai-2.9.2/rwiki-impl/impl/src/java/uk/ac/cam/caret/sakai/rwiki/component/dao/impl/RWikiHistoryObjectContentDaoImpl.java $
 * $Id: RWikiHistoryObjectContentDaoImpl.java 9108 2006-05-08 14:30:57Z ian@caret.cam.ac.uk $
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
package uk.ac.cam.caret.sakai.rwiki.component.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import uk.ac.cam.caret.sakai.rwiki.model.RWikiHistoryObjectContentImpl;
import uk.ac.cam.caret.sakai.rwiki.service.api.dao.RWikiObjectContentDao;
import uk.ac.cam.caret.sakai.rwiki.service.api.model.RWikiHistoryObjectContent;
import uk.ac.cam.caret.sakai.rwiki.service.api.model.RWikiObject;
import uk.ac.cam.caret.sakai.rwiki.service.api.model.RWikiObjectContent;
import uk.ac.cam.caret.sakai.rwiki.utils.TimeLogger;

// FIXME: Component

public class RWikiHistoryObjectContentDaoImpl extends HibernateDaoSupport
		implements RWikiObjectContentDao
{
	private static Log log = LogFactory
			.getLog(RWikiHistoryObjectContentDaoImpl.class);

	public RWikiObjectContent getContentObject(final RWikiObject parent)
	{
		long start = System.currentTimeMillis();
		try
		{
			HibernateCallback callback = new HibernateCallback()
			{
				public Object doInHibernate(Session session)
						throws HibernateException
				{
					return session.createCriteria(
							RWikiHistoryObjectContent.class).add(
							Expression.eq("rwikiid", parent.getId())).list();
				}

			};
			List found = (List) getHibernateTemplate().execute(callback);
			if (found.size() == 0)
			{
				if (log.isDebugEnabled())
				{
					log.debug("Found " + found.size() + " objects with id "
							+ parent.getId());
				}
				return null;
			}
			if (log.isDebugEnabled())
			{
				log.debug("Found " + found.size() + " objects with name "
						+ parent.getId() + " returning most recent one.");
			}
			return (RWikiObjectContent) found.get(0);
		}
		finally
		{
			long finish = System.currentTimeMillis();
			TimeLogger.printTimer(
					"RWikiHistroyObjectContentDaoImpl.getContentObject: "
							+ parent.getId(), start, finish);
		}
	}

	public RWikiObjectContent createContentObject(RWikiObject parent)
	{
		RWikiHistoryObjectContent rwco = new RWikiHistoryObjectContentImpl();
		rwco.setRwikiid(parent.getId());
		return rwco;
	}

	public void update(RWikiObjectContent content)
	{
		RWikiHistoryObjectContentImpl impl = (RWikiHistoryObjectContentImpl) content;
		getHibernateTemplate().saveOrUpdate(impl);
	}

}
