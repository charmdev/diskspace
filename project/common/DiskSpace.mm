#include <hx/CFFI.h>
#import "../include/Utils.h"
#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>

namespace diskspace 
{	
	bool GetIsAvailable(int requiredSpace) 
	{
		uint64_t totalFreeSpace = 0;
		NSError *error = nil;  
		NSArray *paths = NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES);  
		NSDictionary *dictionary = [[NSFileManager defaultManager] attributesOfFileSystemForPath:[paths lastObject] error: &error];  

		if (dictionary) 
		{  
			NSNumber *fileSystemSizeInBytes = [dictionary objectForKey: NSFileSystemSize];  
			NSNumber *freeFileSystemSizeInBytes = [dictionary objectForKey:NSFileSystemFreeSize];
			totalFreeSpace = [freeFileSystemSizeInBytes unsignedLongLongValue];
		} 
		else 
		{  
			NSLog(@"Error Obtaining System Memory Info: Domain = %@, Code = %ld", [error domain], (long)[error code]);
		}  

		return (totalFreeSpace > requiredSpace);
	}
}
