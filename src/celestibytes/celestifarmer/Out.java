package celestibytes.celestifarmer;

public class Out {
	private static void print(String tag, Object text) {
		System.out.println("[" + tag + "]: " + text.toString());
	}
	
	public static void err(Object text) {
		print("ERROR", text);
	}
	
	public static void out(Object text) {
		print("OUT", text);
	}
	
	public static void warn(Object text) {
		print("WARNING", text);
	}
	
	public static void debug(Object text) {
		if(!Version.DEBUG_ENABLED) {return;}
		print("DEBUG", text);
	}
	
	public static void info(Object text) {
		print("INFO", text);
	}
}
