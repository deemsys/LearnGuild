/*
 * This file is part of "SnipSnap Radeox Rendering Engine".
 *
 * Copyright (c) 2002 Stephan J. Schmidt, Matthias L. Jugel
 * All Rights Reserved.
 *
 * Please visit http://radeox.org/ for updates and contact.
 *
 * --LICENSE NOTICE--
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * --LICENSE NOTICE--
 */

package org.radeox.api.engine;

import org.radeox.api.engine.context.RenderContext;

/**
 * Interface for RenderEngines that allow to include content like wiki pages or
 * snips, e.g. with {!includeWiki} in MacroFilter
 * 
 * @author Stephan J. Schmidt
 * @version $Id: IncludeRenderEngine.java 7707 2006-04-12 17:30:19Z
 *          ian@caret.cam.ac.uk $
 */

public interface IncludeRenderEngine
{
	/**
	 * Include an object in the input. This could be a wiki page, snips,
	 * comments.
	 * 
	 * @param name
	 *        Name of the object to include, e.g. wiki page name
	 * @param context 
	 * @return result A string representation of the included object
	 */
	public String include(String name, RenderContext context);
}