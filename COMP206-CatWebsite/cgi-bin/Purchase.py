#!/usr/bin/python

import os
import sys
import cgi
import cgitb


def isValidUserPassword(name, pwd) :
	try:
		ref_member = open("../Members.csv","r")
	except IOError , (errno, strerror):
		errorPromptPage ( "I/O error(" + errno + "):" + strerror )
	except :
		errorPromptPage ( "Unexpected error:(1)")
	else : 
		members = ref_member.readlines()
		for member in members :
			spmember = member.split(",")
			if len(spmember)==3 and name==spmember[0] and pwd== spmember[1] :
				#print spmember, spmember[0] , spmember[1]
				#print name, pwd
				return 1
		return 0

def checkItemAmountAvailability(item,amount) :
	try:
		ref_inventory = open ("../Inventory.csv","r")
	except IOError , (errno, strerror):
		errorPromptPage ( "I/O error(" + errno + "):" + strerror )
	except :
		errorPromptPage ( "Unexpected error:(1)")
	else : 
		inventories = ref_inventory.readlines()
		ref_inventory.close()
		for inventory in inventories :
			spl_inventory = inventory.split(",")
			#print spl_inventory
			if len(spl_inventory)==5 and spl_inventory[1]==item and int(spl_inventory[3])>=amount :
				spl_inventory[3] = str (int(spl_inventory[3])-amount)
				inventories[inventories.index(inventory)]=",".join (["%s" %(k) for k in spl_inventory])
				ref_inventory = open("../Inventory.csv","w")
				for s in inventories :
					ref_inventory.write(s)
				ref_inventory.close()
				return int (spl_inventory[4])
		return (0)

	
def appendBill (bill) :
	try:
		ref_bill = open ("../Log.csv","a+")
	except IOError , (errno, strerror):
		errorPromptPage ( "I/O error(" + errno + "):" + strerror )
	except :
		errorPromptPage ( "Unexpected error:(1)")
	else : 
		ref_bill.write(bill)
		ref_bill.close()
		return 1


def errorPromptPage (errorStr) :
	print "Content-type: text/html\n\n"
	print "<html> <body> <h2> "
	print errorStr
	print """click</h2>
	<a href="http://cs.mcgill.ca/~mweiss17/catalogue.html"> <h2>here to go back</h2></a>
	<a href="http://cs.mcgill.ca/~mweiss17/home.html" > <h2> Go back to homepage </h2> </a>   
	</body>
	</html>"""	
					
def totalCostPage (items, subtotal) :
	print "Content-type: text/html\n\n"
	print "<html> <body> <br> "
	print items + "<br>"
	print "Subtotal:" + "$"+str(subtotal) + "<br>"
	print "GST:" + "$"+str (subtotal*.05) + "<br>"
	print "PST:" + "$"+str (subtotal*.095) + "<br>"
	print "Total:" + "$"+str (subtotal*1.145) + "<br>"
	print """
	<a href="http://cs.mcgill.ca/~mweiss17/catalogue.html"> <h2>here to go back</h2></a>		<a href="http://cs.mcgill.ca/~mweiss17/home.html" > <h2> Go back to homepage </h2> </a>   
	</body>
	</html>"""	

					
		
#print isValidUserPassword("mklock","hammel")
#print checkItemAmountAvailable("cream",11)
#print appendBill("mklock, hammel, total amount=100")



form=cgi.FieldStorage()
#print form

if form.has_key("user") and form.has_key("password"):
	if not (isValidUserPassword(form["user"].value, form["password"].value)) :
		errorPromptPage ("Username and password don't match, please ") 
	else :
		subtotal=0
		selectedItems="Purchase Items:"
	
		if form.has_key("oreo") : 
			cost = checkItemAmountAvailability("oreo",1)	
			if cost==0 :
				errorPromptPage ("oreo is not available, please click") 
			else :
				subtotal += cost
				selectedItems += "oreo "
			
			
		if form.has_key("skittles") :
			cost = checkItemAmountAvailability("skittles",1)	
			if cost==0 :
				errorPromptPage ("skittles is not available, please click") 
			else :
				subtotal += cost
				selectedItems += "skittles "

		if form.has_key("cream") :
			cost = checkItemAmountAvailability("cream",1)	
			if cost==0 :
				errorPromptPage ("cream is not available, please click") 
			else :
				subtotal += cost
				selectedItems += "cream "

		if form.has_key("kittenFood") :
			cost = checkItemAmountAvailability("kittenFood",1)	
			if cost==0 :
				errorPromptPage ("kittenFood is not available, please click") 
			else :
				subtotal += cost
				selectedItems += "kittenFood "

		if form.has_key("catHarness") :
			cost = checkItemAmountAvailability("catHarness",1)	
			if cost==0 :
				errorPromptPage ("catHarness is not available, please click") 
			else :
				subtotal += cost
				selectedItems += "catHarness "

		if form.has_key("catToy") :
			cost = checkItemAmountAvailability("catToy",1)	
			if cost==0 :
				errorPromptPage ("catToy is not available, please click") 
			else :
				subtotal += cost
				selectedItems += "catToy "

		if subtotal==0 :
			errorPromptPage ("No item has been selected, please click") 
		else :
			appendBill(form["user"].value+";"+selectedItems+";"+str(subtotal) + "\n")
			totalCostPage (selectedItems,subtotal)


	


