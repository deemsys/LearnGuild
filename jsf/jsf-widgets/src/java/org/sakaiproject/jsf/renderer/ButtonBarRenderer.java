/**********************************************************************************
* $URL: https://source.sakaiproject.org/svn/jsf/tags/jsf-2.9.2/jsf-widgets/src/java/org/sakaiproject/jsf/renderer/ButtonBarRenderer.java $
* $Id: ButtonBarRenderer.java 68846 2009-11-13 12:27:32Z arwhyte@umich.edu $
***********************************************************************************
*
 * Copyright (c) 2003, 2004, 2005, 2006, 2008 The Sakai Foundation
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


package org.sakaiproject.jsf.renderer;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;

public class ButtonBarRenderer extends Renderer
{
  public boolean supportsComponentType(UIComponent component)
  {
    return (component instanceof org.sakaiproject.jsf.component.ButtonBarComponent);
  }

  public void encodeBegin(FacesContext context, UIComponent component) throws IOException
  {
  	if (!component.isRendered()) return;

    ResponseWriter writer = context.getResponseWriter();
    writer.write("<p class=\"act\">\n");

    return;
  }

  public void encodeEnd(FacesContext context, UIComponent component) throws IOException
  {
  	if (!component.isRendered()) return;

    ResponseWriter writer = context.getResponseWriter();
    writer.write("</p>\n");
  }
}



