/**********************************************************************************
*
* $Id: SimpleDependencyInjectionTest.java 59688 2009-04-03 23:45:02Z arwhyte@umich.edu $
*
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

package org.sakaiproject.test;

import junit.framework.Assert;

import org.sakaiproject.site.api.SiteService;

/**
 *
 */
public class SimpleDependencyInjectionTest extends SakaiDependencyInjectionTests {
	private SiteService siteService;

	public void testSimpleInjection() throws Exception {
		Assert.assertNotNull(siteService);
	}

	public void setSiteService(SiteService siteService) {
		this.siteService = siteService;
	}
}
