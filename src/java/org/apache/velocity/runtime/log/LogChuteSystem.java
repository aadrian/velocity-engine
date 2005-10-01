/*
 * Copyright 2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.velocity.runtime.log;

import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.util.StringUtils;

/**
 * Wrapper to make user's custom LogSystem implementations work
 * with the new LogChute setup.
 *
 * @author <a href="mailto:nbubna@apache.org">Nathan Bubna</a>
 * @version $Id: LogChute.java 291585 2005-09-26 08:56:23Z henning $
 */
public class LogChuteSystem implements LogChute
{

    private LogSystem logSystem;

    /**
     * Only classes in this package should be creating this.
     * Users should not have to mess with this class.
     */
    protected LogChuteSystem(LogSystem wrapMe)
    {
        this.logSystem = wrapMe;
    }

    public void init(RuntimeServices rs) throws Exception
    {
        logSystem.init(rs);
    }

    public void log(int level, String message)
    {
        logSystem.logVelocityMessage(level, message);
    }

    /**
     * First passes off the message at the specified level,
     * then passes off stack trace of the Throwable as a 
     * 2nd message at the same level.
     */
    public void log(int level, String message, Throwable t)
    {
        logSystem.logVelocityMessage(level, message);
        logSystem.logVelocityMessage(level, StringUtils.stackTrace(t));
    }

    /**
     * Always returns true.
     */
    public boolean isLevelEnabled(int level)
    {
        return true;
    }

}