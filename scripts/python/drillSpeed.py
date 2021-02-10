print("Calc by NickName73\nUsed for math drillTime(Mindustry) V-0.1-Bugs+")
def invert(sec,items, out, out2, out3, type):
	out = float(sec) / float(items)
	out2 = 1 / float(out)
	out3 = float(sec) * 60
	out3 = float(out3) / float(items)
	if type == 2:
		return(out2)
	elif type == 3:
		return(out3)
	else:
		return(out)
def outvert(itempersec, out):
	itempersec = 1 / float(itempersec)
	out = float(itempersec) * 60
	return(out)
###--------------------------###
category = input("Select [I|S to SB]-1 or [I/S to SB]-2: ")
if category == str(1):
	sec = input("Input Second: ")
	items = input("Input Items: ")
	print(str("Second for one item: ") + str(invert(sec,items,1,1,1,1)))
	print(str("Items per second: ") + str(invert(sec,items,1,1,1,2)))
	print(str("Ticks for 1SB: ") + str(invert(sec,items,1,1,1,3)))
elif category == str(2):
	itempersec = input("Input item per second {0.2}:")
	print(str("Ticks for 1SB: ")+str(outvert(itempersec,1)))
else:
	print("Only 1 or 2")