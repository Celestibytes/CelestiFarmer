package celestibytes.celestifarmer.util;

import java.awt.SystemTray;
import java.awt.TrayIcon;

// TODO!
public class TaskbarIntegration {
	
	private static SystemTray systray = null;
	private static TrayIcon trayicon = null;
	
	public static void init() {
		if(isEnabled()) {
			systray = SystemTray.getSystemTray();
		}
	}
	
	public static boolean isEnabled() {
		return SystemTray.isSupported();
	}
	
	public void info(Object text) {
		
	}
	
	public void alert(Object text) {
		
	}
	
}
