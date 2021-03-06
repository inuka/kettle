/*
 * Copyright (c) 2010 Pentaho Corporation.  All rights reserved. 
 * This software was developed by Pentaho Corporation and is provided under the terms 
 * of the GNU Lesser General Public License, Version 2.1. You may not use 
 * this file except in compliance with the license. If you need a copy of the license, 
 * please go to http://www.gnu.org/licenses/lgpl-2.1.txt. The Original Code is Pentaho 
 * Data Integration.  The Initial Developer is Pentaho Corporation.
 *
 * Software distributed under the GNU Lesser Public License is distributed on an "AS IS" 
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or  implied. Please refer to 
 * the license for the specific language governing your rights and limitations.
 */
package org.pentaho.di.core.logging;


public class LogChannel implements LogChannelInterface {
	
	public static LogChannelInterface GENERAL  = new LogChannel("General");
	public static LogChannelInterface METADATA = new LogChannel("Metadata");
	public static LogChannelInterface UI       = new LogChannel("GUI");
	
	private static LogWriter log = LogWriter.getInstance();
	private String logChannelId;
	
	private LogLevel logLevel;
	
	private String containerObjectId;

	 public LogChannel(Object subject) {
	   logLevel = DefaultLogLevel.getLogLevel();
	    logChannelId = LoggingRegistry.getInstance().registerLoggingSource(subject);
	  }

	public LogChannel(Object subject, LoggingObjectInterface parentObject) {
	  if (parentObject!=null) {
	    this.logLevel = parentObject.getLogLevel();
	    this.containerObjectId = parentObject.getContainerObjectId();
	  } else {
	    this.logLevel = DefaultLogLevel.getLogLevel();
      this.containerObjectId = null;
	  }
		logChannelId = LoggingRegistry.getInstance().registerLoggingSource(subject);
	}
	
	@Override
	public String toString() {
		return logChannelId;
	}
	
	public String getLogChannelId() {
		return logChannelId;
	}
		
    public void logMinimal(String s)
    {
        log.println(new LogMessage(s, logChannelId, LogLevel.MINIMAL), logLevel); //$NON-NLS-1$
    }

    public void logBasic(String s)
    {
        log.println(new LogMessage(s, logChannelId, LogLevel.BASIC), logLevel); //$NON-NLS-1$
    }

    public void logError(String s)
    {
        log.println(new LogMessage(s, logChannelId, LogLevel.ERROR), logLevel); //$NON-NLS-1$
    }

    public void logError(String s, Throwable e)
    {
    	log.println(new LogMessage(s, logChannelId, LogLevel.ERROR), e, logLevel); //$NON-NLS-1$
    }

    public void logBasic(String s, Object...arguments) 
    {
    	log.println(new LogMessage(s, logChannelId, arguments, LogLevel.BASIC), logLevel);
    }

    public void logDetailed(String s, Object...arguments) 
    {
    	log.println(new LogMessage(s, logChannelId, arguments, LogLevel.DETAILED), logLevel);
    }

    public void logError(String s, Object...arguments) 
    {
    	log.println(new LogMessage(s, logChannelId, arguments, LogLevel.ERROR), logLevel);
    }

    public void logDetailed(String s)
    {
        log.println(new LogMessage(s, logChannelId, LogLevel.DETAILED), logLevel);
    }

    public void logDebug(String s)
    {
        log.println(new LogMessage(s, logChannelId, LogLevel.DEBUG), logLevel);
    }
    
	public void logDebug(String message, Object... arguments) {
        log.println(new LogMessage(message, logChannelId, arguments, LogLevel.DEBUG), logLevel);
	}

    public void logRowlevel(String s)
    {
        log.println(new LogMessage(s, logChannelId, LogLevel.ROWLEVEL), logLevel);
    }

	public void logMinimal(String message, Object... arguments) {
		log.println(new LogMessage(message, logChannelId, arguments, LogLevel.MINIMAL), logLevel);
	}

	public void logRowlevel(String message, Object... arguments) {
		log.println(new LogMessage(message, logChannelId, arguments, LogLevel.ROWLEVEL), logLevel);
	}

	public boolean isBasic() {
		return logLevel.isBasic();
	}

	public boolean isDebug() {
		return logLevel.isDebug();
	}

	public boolean isDetailed() {
	  try {
	    return logLevel.isDetailed();
	  } catch(NullPointerException ex) {
	    System.out.println("Oops!");
	    return false;
	  }
	}

	public boolean isRowLevel() {
		return logLevel.isRowlevel();
	}

	 public boolean isError() {
	    return logLevel.isError();
	  }

	public LogLevel getLogLevel() {
	  return logLevel;
	}
	
	public void setLogLevel(LogLevel logLevel) {
	  this.logLevel = logLevel;
	}

  /**
   * @return the containerObjectId
   */
  public String getContainerObjectId() {
    return containerObjectId;
  }

  /**
   * @param containerObjectId the containerObjectId to set
   */
  public void setContainerObjectId(String containerObjectId) {
    this.containerObjectId = containerObjectId;
  }
}
