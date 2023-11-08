from pyVmomi import vmodl, vim
from pyVim.connect import SmartConnect
import ssl

c = ssl._create_unverified_context()
si = SmartConnect(host='10.218.139.196', user='Administrator@vsphere.local', pwd='Artvm@123', sslContext=c)

vms=si.content.viewManager.CreateContainerView(si.content.rootFolder, [vim.VirtualMachine], True)

l_of_vm=vms.view
for vm in l_of_vm:
  print(vm.summary.config.name)