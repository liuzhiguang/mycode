Sub Main
		xsh.Screen.Send "ssh wb-lzg228465@xxx.xxx.xxx.xxx"
		xsh.Screen.Send VbCr
		xsh.Screen.WaitForString "password:"
		xsh.Screen.Send "*******"
		xsh.Screen.Send VbCr
End Sub
