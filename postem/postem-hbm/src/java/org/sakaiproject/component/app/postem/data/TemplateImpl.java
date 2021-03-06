/**********************************************************************************
 * $URL: https://source.sakaiproject.org/svn/postem/tags/sakai-2.9.2/postem-hbm/src/java/org/sakaiproject/component/app/postem/data/TemplateImpl.java $
 * $Id: TemplateImpl.java 59680 2009-04-03 23:28:39Z arwhyte@umich.edu $
 ***********************************************************************************
 *
 * Copyright (c) 2003, 2004, 2005, 2006, 2007, 2008 The Sakai Foundation
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

package org.sakaiproject.component.app.postem.data;

import java.io.Serializable;
import java.util.ListIterator;

import org.sakaiproject.api.app.postem.data.StudentGrades;
import org.sakaiproject.api.app.postem.data.Template;

public class TemplateImpl implements Template, Serializable {
	protected String templateCode;

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	public String fillGrades(StudentGrades student) {
		String output = new String(templateCode);
		output = output.replaceAll("\\$0\\$", student.getUsername());
		ListIterator grades = student.getGrades().listIterator();
		while (grades.hasNext()) {
			int index = grades.nextIndex();
			String grade = (String) grades.next();
			output = output.replaceAll("\\$" + (index + 1) + "\\" +
					"$", grade);
		}
		return output;
	}
}
