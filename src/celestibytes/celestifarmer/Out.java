package celestibytes.celestifarmer;

import celestibytes.celestifarmer.Version;
import celestibytes.ctiengine.logging.ILogger;

public class Out implements ILogger {
	
	public static ILogger INSTANCE = new Out();
	
	private String loggerPrefix = null;
	
	public static void serr(Object text) {
		INSTANCE.err(text);
	}
	
	public static void sout(Object text) {
		INSTANCE.out(text);
	}
	
	public static void swarn(Object text) {
		INSTANCE.warn(text);
	}
	
	public static void sdebug(Object text) {
		INSTANCE.debug(text);
	}
	
	public static void sinfo(Object text) {
		INSTANCE.info(text);
	}
	
	private void print(String tag, Object text) {
		System.out.println((this.loggerPrefix != null ? "[" + this.loggerPrefix + "] " : "") + "[" + tag + "]: " + text.toString());
	}
	
	@Override
	public void err(Object text) {
		print("ERROR", text);		
	}

	@Override
	public void out(Object text) {
		print("OUT", text);
	}

	@Override
	public void warn(Object text) {
		print("WARNING", text);
	}

	@Override
	public void debug(Object text) {
		if(!Version.DEBUG_ENABLED) {return;}
		print("DEBUG", text);
	}

	@Override
	public void info(Object text) {
		print("INFO", text);
	}

	@Override
	public void setLoggerPrefix(String prefix) {
		this.loggerPrefix = prefix;
	}
}
