#ifndef STATIC_LINK
#define IMPLEMENT_API
#endif

#if defined(HX_WINDOWS) || defined(HX_MACOS) || defined(HX_LINUX)
#define NEKO_COMPATIBLE
#endif

#include <hx/CFFI.h>
#include "Utils.h"

using namespace diskspace;

static value diskspace_is_available(int requiredSpace) 
{
	return alloc_bool(GetIsAvailable(requiredSpace));
}
DEFINE_PRIM(diskspace_is_available, 1);

extern "C" void diskspace_main() 
{
	val_int(0); // Fix Neko init
}
DEFINE_ENTRY_POINT(diskspace_main);

extern "C" int diskspace_register_prims () { return 0; }
