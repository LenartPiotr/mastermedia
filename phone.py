import os
import re

import pythoncom
from win32com.shell import shell, shellcon

desktop = shell.SHGetDesktopFolder()
cwd_pidl, _ = shell.SHParseDisplayName(os.getcwd(), 0)
cwd_shellfolder = desktop.BindToObject(cwd_pidl, None, shell.IID_IShellFolder)

for pidl in desktop.EnumObjects(0, shellcon.SHCONTF_FOLDERS):
    name = desktop.GetDisplayNameOf(pidl, shellcon.SHGDN_NORMAL)
    print(name)
    shell_folder = desktop.BindToObject(pidl, None, shell.IID_IShellFolder)
    break

for pidl in shell_folder:
    attrs = shell_folder.GetAttributesOf([pidl], shellcon.SFGAO_STORAGE)
    if not attrs:
        print(shell_folder.GetDisplayNameOf(pidl, shellcon.SHGDN_NORMAL))
        device = shell_folder.BindToObject(pidl, None, shell.IID_IShellFolder)
        break

for pidl in device:
    storage = device.BindToObject(pidl, None, shell.IID_IShellFolder)
    for pidl in storage.EnumObjects(0, shellcon.SHCONTF_FOLDERS):
        if storage.GetDisplayNameOf(pidl, shellcon.SHGDN_NORMAL) in ('DCIM', 'Pictures'):
            image_folder = storage.BindToObject(pidl, None, shell.IID_IShellFolder)
            for pidl in image_folder.EnumObjects(0, shellcon.SHCONTF_NONFOLDERS):
                name = image_folder.GetDisplayNameOf(pidl, shellcon.SHGDN_FORADDRESSBAR).replace('\u200e', '').replace('\u200f', '')
                image_name = image_folder.GetDisplayNameOf(pidl, shellcon.SHGDN_NORMAL)

                source_idl = shell.SHGetIDListFromObject(image_folder)
                dest_idl = shell.SHGetIDListFromObject(cwd_shellfolder)

                source_item = shell.SHCreateShellItem(source_idl, None, pidl)
                destination = shell.SHCreateItemFromIDList(dest_idl)

                pfo = pythoncom.CoCreateInstance(shell.CLSID_FileOperation, None, pythoncom.CLSCTX_ALL, shell.IID_IFileOperation)
                pfo.SetOperationFlags(shellcon.FOF_NOCONFIRMATION | shellcon.FOF_NO_UI)
                pfo.CopyItem(source_item, destination, image_name)
                pfo.PerformOperations()