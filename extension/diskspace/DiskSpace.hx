package extension.diskspace;

#if cpp
import cpp.Lib;
#elseif neko
import neko.Lib;
#end

#if android
import openfl.utils.JNI;
#end


class DiskSpace 
{
	public static function isAvailable(requiredSpace:Int):Bool 
	{
#if android
		var result:Bool = DiskSpace_android_isAvailable(requiredSpace);
		return result;
#elseif ios
		var result:Bool = DiskSpace_ios_isAvailable(requiredSpace);
		return result;
#else
		return true;
#end
	}
	
	public static function isInternal():Bool 
	{
#if android
		var result:Bool = DiskSpace_android_isInternal();
		return result;
#else
		return true;
#end
	}
	
	#if ios
	private static var DiskSpace_ios_isAvailable = Lib.load("diskspace", "diskspace_is_available", 1);
	#end
	
	#if android
	private static var DiskSpace_android_isAvailable = JNI.createStaticMethod("com.diskspace.DiskSpace", "isAvailable", "(I)Z");
	private static var DiskSpace_android_isInternal = JNI.createStaticMethod("com.diskspace.DiskSpace", "isInternal", "()Z");
	#end
}
