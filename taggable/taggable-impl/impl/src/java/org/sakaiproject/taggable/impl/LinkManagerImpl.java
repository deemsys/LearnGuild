/**********************************************************************************
 * $URL: https://source.sakaiproject.org/contrib/syracuse/taggable/branches/oncourse_osp_enhancements/taggable-impl/impl/src/java/org/sakaiproject/taggable/impl/LinkManagerImpl.java $
 * $Id: LinkManagerImpl.java 46822 2008-03-17 16:19:47Z chmaurer@iupui.edu $
 ***********************************************************************************
 *
 * Copyright (c) 2008 The Sakai Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 **********************************************************************************/
package org.sakaiproject.taggable.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.sakaiproject.exception.IdUnusedException;
import org.sakaiproject.exception.PermissionException;
import org.sakaiproject.taggable.impl.LinkImpl;
import org.sakaiproject.taggable.api.Link;
import org.sakaiproject.taggable.api.LinkManager;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class LinkManagerImpl extends HibernateDaoSupport implements LinkManager
{
	
	private static final String NULL_ARG = "Null Argument";
	protected static final String CONTEXT = "context",
			ACTIVITY_REF = "activityRef",
			TAG_CRITERIA_REF = "tagCriteriaRef",
			VISIBLE = "visible",
			QUERY_LINKS_BY_ACTIVITY_CONTEXT = "findLinksByActivityRefContext",
			QUERY_LINKS_BY_ACTIVITY_CONTEXT_VISIBLE = "findLinksByActivityRefContextVisible",
			QUERY_LINKS_BY_CRITERIA = "findLinksByCriteriaRef",
			QUERY_LINKS_BY_CRITERIA_VISIBLE = "findLinksByCriteriaRefVisible", 
			QUERY_DELETE_LINKS_BY_ACTIVITY_REF = "deleteLinksByActivityRef";

	public Link persistLink(String activityRef, String tagCriteriaRef, String rationale,
			String rubric, boolean visible, boolean locked) {
		if ((activityRef == null) || (tagCriteriaRef == null)) {
			throw new IllegalArgumentException(NULL_ARG);
		}

		LinkImpl link = new LinkImpl(activityRef, tagCriteriaRef, rationale, rubric,
				visible, locked);
		getHibernateTemplate().save(link);
		return link;
	}
	
	public Link lookupLink(List<Link> links, String tagCriteriaRef) {
		for (Link link : links) {
			if (link.getTagCriteriaRef().equalsIgnoreCase(tagCriteriaRef))
				return link;
		}
		return null;
	}

	public Link getLink(String ref) throws IdUnusedException, PermissionException
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Link getLink(final String activityRef, final String tagCriteriaRef)
			throws PermissionException
	{
		if ((activityRef == null) || (tagCriteriaRef == null)) {
			throw new IllegalArgumentException(NULL_ARG);
		}

		return (Link) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				return session.createCriteria(LinkImpl.class).add(
						Restrictions.eq(ACTIVITY_REF, activityRef)).add(
						Restrictions.eq(TAG_CRITERIA_REF, tagCriteriaRef)).uniqueResult();
			}
		});
	}

	public List<Link> getLinks(final String activityRef, final boolean any,
			final String context) {
		if ((activityRef == null) || (context == null)) {
			throw new IllegalArgumentException(NULL_ARG);
		}

		return (List) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String likeContext = "%/"+context+"/%";
				
				Query q = session.getNamedQuery(QUERY_LINKS_BY_ACTIVITY_CONTEXT);
				if (!any) {
					q = session.getNamedQuery(QUERY_LINKS_BY_ACTIVITY_CONTEXT_VISIBLE);
					q.setParameter(VISIBLE, true);
				}
				
				q.setParameter(ACTIVITY_REF, activityRef, Hibernate.STRING);
				q.setParameter(CONTEXT, likeContext, Hibernate.STRING);
				
				return q.list();
				
			}
		});
	}
	
	public List<Link> getLinks(final String criteriaRef, final boolean any) {
		if (criteriaRef == null) {
			throw new IllegalArgumentException(NULL_ARG);
		}

		return (List) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				
				Query q = session.getNamedQuery(QUERY_LINKS_BY_CRITERIA);
				if (!any) {
					q = session.getNamedQuery(QUERY_LINKS_BY_CRITERIA_VISIBLE);
					q.setParameter(VISIBLE, true);
				}
				
				q.setParameter(TAG_CRITERIA_REF, criteriaRef, Hibernate.STRING);
				
				return q.list();
				
			}
		});
	}
	
	public void removeLink(Link link) {
		if (link == null) {
			throw new IllegalArgumentException(NULL_ARG);
		}

		getHibernateTemplate().delete(link);
	}
	
	public void removeLinks(final String activityRef) {
		if (activityRef == null) {
			throw new IllegalArgumentException(NULL_ARG);
		}

		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query q = session.getNamedQuery(QUERY_DELETE_LINKS_BY_ACTIVITY_REF);
				q.setParameter(ACTIVITY_REF, activityRef);
				q.executeUpdate();
				
				return null;
			}
		});
	}
	
}
