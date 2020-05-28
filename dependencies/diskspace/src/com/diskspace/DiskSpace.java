package com.diskspace;

import org.haxe.extension.Extension;
import android.content.pm.ApplicationInfo;
import android.os.StatFs;
import android.os.Environment;
import java.io.File;

/* 
	You can use the Android Extension class in order to hook
	into the Android activity lifecycle. This is not required
	for standard Java code, this is designed for when you need
	deeper integration.
	
	You can access additional references from the Extension class,
	depending on your needs:
	
	- Extension.assetManager (android.content.res.AssetManager)
	- Extension.callbackHandler (android.os.Handler)
	- Extension.mainActivity (android.app.Activity)
	- Extension.mainContext (android.content.Context)
	- Extension.mainView (android.view.View)
	
	You can also make references to static or instance methods
	and properties on Java classes. These classes can be included 
	as single files using <java path="to/File.java" /> within your
	project, or use the full Android Library Project format (such
	as this example) in order to include your own AndroidManifest
	data, additional dependencies, etc.
	
	These are also optional, though this example shows a static
	function for performing a single task, like returning a value
	back to Haxe from Java.
*/
public class DiskSpace extends Extension
{
	public static boolean isInternal() 
	{
		boolean isOnInternalStorage = true;
		ApplicationInfo applicationInfo = mainActivity.getApplicationContext().getApplicationInfo();
		
		if ((applicationInfo.flags & ApplicationInfo.FLAG_EXTERNAL_STORAGE) != 0) 
		{
			// installed on sdcard
			isOnInternalStorage = false;
		}
		
		return isOnInternalStorage;
	}
	
	public static boolean isAvailable(int requiredSpace) 
	{
		boolean isOnInternalStorage = true;
		ApplicationInfo applicationInfo = mainActivity.getApplicationContext().getApplicationInfo();
		
		if ((applicationInfo.flags & ApplicationInfo.FLAG_EXTERNAL_STORAGE) != 0) 
		{
			// installed on sdcard
			isOnInternalStorage = false;
		}
		
		long freeMemory = 0;
		if (isOnInternalStorage)
		{
			freeMemory = getFreeInternalMemory();
		}
		else
		{
			freeMemory = getFreeExternalMemory();
		}
		
		return (freeMemory >= requiredSpace);
	}
	
	// Get internal (data partition) free space
	// This will match what's shown in System Settings > Storage for 
	// Internal Space, when you subtract Total - Used
	private static long getFreeInternalMemory()
	{
		return getFreeMemory(Environment.getDataDirectory());
	}

	// Get external (SDCARD) free space
	private static long getFreeExternalMemory()
	{
		return getFreeMemory(Environment.getExternalStorageDirectory());
	}

	// Get Android OS (system partition) free space
	private static long getFreeSystemMemory()
	{
		return getFreeMemory(Environment.getRootDirectory());
	}

	// Get free space for provided path
	// Note that this will throw IllegalArgumentException for invalid paths
	private static long getFreeMemory(File path)
	{
		StatFs stats = new StatFs(path.getAbsolutePath());
		return stats.getAvailableBytes();
	}
	
}
