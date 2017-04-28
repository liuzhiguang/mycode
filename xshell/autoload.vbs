Sub Main
		xsh.Screen.Send "ssh wb-lzg228465@100.69.203.116"
		xsh.Screen.Send VbCr
		xsh.Screen.WaitForString "password:"
		xsh.Screen.Send "19931004danfeng+"
		xsh.Screen.Send VbCr
End Sub