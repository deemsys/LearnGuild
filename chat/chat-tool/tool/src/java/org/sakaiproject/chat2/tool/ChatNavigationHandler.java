/**********************************************************************************
* $URL: https://source.sakaiproject.org/svn/chat/tags/sakai-2.9.2/chat-tool/tool/src/java/org/sakaiproject/chat2/tool/ChatNavigationHandler.java $
* $Id: ChatNavigationHandler.java 59673 2009-04-03 23:02:03Z arwhyte@umich.edu $
***********************************************************************************
*
 * Copyright (c) 2007, 2008 The Sakai Foundation
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

/**
 * 
 */
package org.sakaiproject.chat2.tool;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;

/**
 * @author chrismaurer
 *
 */
public class ChatNavigationHandler extends NavigationHandler {
   private NavigationHandler navbase;
   
   /**
    * Constructs an instance of FmNavigationHandler.
    */
   public ChatNavigationHandler(NavigationHandler base)
   {
       super();
       navbase = base;
   }   // End Constructor

   /**
    * Perform navigation processing based on the state information in the
    * specified FacesContext, plus the outcome string returned by an executed
    * application action.
    * 
    * @param facesContext
    * @param fromAction
    * @param outcome
    */
   public void handleNavigation(FacesContext facesContext, String fromAction, String outcome)
   {
       // Workaround a JSF RI bug that handleNavigation
       // (including redirect) when the response has been committed.
       // This fix checks for responseComplete before handling navigation.
       if (!facesContext.getResponseComplete())
       {
          navbase.handleNavigation(facesContext, fromAction, outcome);
       }
   }
}
