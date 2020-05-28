Disk Space
==========

Simple extension for OpenFL/NME that tells whether there is enough free space on the device to store new files. Supporting currently Android target (contribution for desktop targets is welcome)

How to use
==========

First of all, install the lib:

```bash
haxelib install diskspace
```

Once this is done, you just need to add this to your project.xml
```xml
<haxelib name="diskspace" />
```
then from haxe, just do:

```haxe
// This example show a simple use case.

import extension.diskspace.DiskSpace;

// if there is at least 1 Mb of free space available
var isAvailable:Bool = DiskSpace.isAvailable(1024 * 1024);

```