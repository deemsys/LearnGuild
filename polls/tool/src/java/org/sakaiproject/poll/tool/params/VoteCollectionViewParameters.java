/**********************************************************************************
 * $URL: https://source.sakaiproject.org/svn/polls/tags/polls-1.5.2/tool/src/java/org/sakaiproject/poll/tool/params/VoteCollectionViewParameters.java $
 * $Id: VoteCollectionViewParameters.java 103685 2012-01-26 22:21:06Z bkirschn@umich.edu $
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

package org.sakaiproject.poll.tool.params;

import uk.org.ponder.rsf.viewstate.SimpleViewParameters;

public class VoteCollectionViewParameters extends SimpleViewParameters {
	
	public String id;
	
	public VoteCollectionViewParameters() {
		
	}
	
	public VoteCollectionViewParameters(String viewId) {
		this.viewID = viewId;
	}
	
	public VoteCollectionViewParameters(String viewId, String id) {
		this.viewID = viewId;
		this.id = id;
	}

}
