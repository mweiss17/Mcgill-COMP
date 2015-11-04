#include <stdlib.h>
#include <stdio.h>
#include <string.h>

main()
{
	char *data = getenv("QUERY_STRING");
	char choice[50];
	//char *data ="add_username=5555&add_password=2222&add_usertype=3333&Submit=Submit+Query";
	char *p1,*p2;
	char user[50], password[50], type[50];
	char newUser[50], newPassword[50], newType[50];
	char buffer[500];

	
	if (strstr(data, "group1=system_AddNewUser")!=NULL)
	{
		addNewUserForm();
	}
	else if (strstr(data, "group1=system_ChangeCurrentUser")!=NULL)
	{
		editForm();
	}
	else if (strstr(data, "group1=system_ListInventory")!=NULL)
	{
		listInventory();
	}
	else if (strstr(data, "add_")!=NULL)
	{
		p1 = strstr(data, "add_username=");
		p2 = strchr(p1,'&');
		strncpy(user,p1+13,p2-p1-13);
		user[p2-p1-13] = '\0';

		p1 = strstr(data, "add_password=");
		p2 = strchr(p1,'&');
		strncpy(password,p1+13,p2-p1-13);
		password[p2-p1-13] = '\0';

		p1 = strstr(data, "add_usertype=");
		p2 = strchr(p1,'&');
		strncpy(type,p1+13,p2-p1-13);
		type[p2-p1-13] = '\0';
//printf ("user=%s, password=%s, type=%s\n",user, password, type);
//return;
		addNewUser(user,password,type);
	}
	else if (strstr(data, "edit_")!=NULL)
	{
		p1 = strstr(data, "edit_oldusername=");
		p2 = strchr(p1,'&');
		strncpy(user,p1+17,p2-p1-17);
		user[p2-p1-17] = '\0';

		p1 = strstr(data, "edit_oldpassword=");
		p2 = strchr(p1,'&');
		strncpy(password,p1+17,p2-p1-17);
		password[p2-p1-17] = '\0';

		p1 = strstr(data, "edit_oldtype=");
		p2 = strchr(p1,'&');
		strncpy(type,p1+13,p2-p1-13);
		type[p2-p1-13] = '\0';

		p1 = strstr(data, "edit_newusername=");
		p2 = strchr(p1,'&');
		strncpy(newUser,p1+17,p2-p1-17);
		newUser[p2-p1-17] = '\0';

		p1 = strstr(data, "edit_newpassword=");
		p2 = strchr(p1,'&');
		strncpy(newPassword,p1+17,p2-p1-17);
		newPassword[p2-p1-17] = '\0';

		p1 = strstr(data, "edit_newtype=");
		p2 = strchr(p1,'&');
		strncpy(newType,p1+13,p2-p1-13);
		newType[p2-p1-13] = '\0';
//printf ("user=%s, password=%s, type=%s\n",user, password, type);
//printf ("newuser=%s, newpassword=%s, newtype=%s\n",newUser, newPassword, newType);
//return;
		editUser(user,password, type, newUser,newPassword, newType);
	}

	else
	{
		sprintf (buffer,"Query String: %s is not correct", data);
		showMessagePage(buffer); 
	}
}

addNewUserForm()
{
	printf ("Content-type: text/html\n\n");
	printf ("<html><body>\n");
	printf ("<title>Add New User</title>\n");
	printf ("<h2 align=\"center\">Add New User</h2>\n");
	printf ("<form name=\"addnewuser\" action=\"./Menu\" method=\"get\"><br>\n");
	printf ("User Name:<input type=\"text\" name=\"add_username\" > <br>\n");
	printf ("Password:<input type=\"password\" name=\"add_password\" > <br>\n");
	printf ("User Type<input type=\"text\" name=\"add_usertype\" > <br>\n");
	printf ("<a href = http://127.0.1.1/~bill/home.html> Back to home page</a><br>\n");
	printf ("<input type=\"submit\" name = \"Submit\">\n");
	printf ("</form></body></html>\n");

}

editForm()
{
	printf ("Content-type: text/html\n\n");
	printf ("<html><body>\n");

	printf ("<title>Edit User Account</title>\n");

	printf ("<h2 align=\"center\">Edit User Account</h2>\n");
	printf ("<form name=\"edit\" action=\"./Menu\" method=\"get\"><br>\n");
	printf ("Old User Name:<input type=\"text\" name=\"edit_oldusername\" > <br>\n");
	printf ("Old Password:<input type=\"password\" name=\"edit_oldpassword\" > <br>\n");
	printf ("Old type:<input type=\"text\" name=\"edit_oldtype\" > <br>\n");
	printf ("New User Name:<input type=\"text\" name=\"edit_newusername\" > <br>\n");
	printf ("New Password:<input type=\"password\" name=\"edit_newpassword\" > <br>\n");
	printf ("New type:<input type=\"text\" name=\"edit_newtype\" > <br>\n");
	printf ("<a href = http://127.0.1.1/~bill/home.html> Back to home page</a><br>\n");
	printf ("<input type=\"submit\" name = \"Submit\">\n");
	printf ("</form></body></html>\n");

}

listInventory()
{
	char no[20],item[30],description[30],amount[30], price[30];
	char line[50];
	FILE *fpr;

	printf ("Content-type: text/html\n\n");
	printf ("<html><body>\n");
	printf ("<title>List Inventory</title>\n");
	printf ("<h2 align=\"center\">List Inventory</h2>\n");
	printf ("<table border=\"1\">\n");
	printf ("<tr>\n");
	printf ("<td align=\"center\"> No.</td>\n");
	printf ("<td align=\"center\"> Item</td>\n");
	printf ("<td align=\"center\"> Description</td>\n");
	printf ("<td align=\"center\"> Stock Amount</td>\n");
	printf ("<td align=\"center\"> Unit Price</td>\n");
	printf ("</tr> \n");

	fpr = fopen("../Inventory.csv","r");
	if (fpr==NULL)
	{
		return 0;
	}
	fgets(line, 49, fpr);
	while (!feof(fpr))
	{
//printf ("line=%s***", line);	
		if (strlen(line)<5)
			break;
		strcpy (no, strtok(line,",")); 
		strcpy (item, strtok(NULL,","));
		strcpy (description, strtok(NULL,","));
		strcpy (amount, strtok(NULL,","));
		strcpy (price, strtok(NULL,","));

		printf ("<tr>\n");
		printf ("<td align=\"center\">  %s</td>\n",no);
		printf ("<td align=\"center\"> %s </td>\n",item);
		printf ("<td align=\"center\"> %s </td>\n",description);
		printf ("<td align=\"center\"> %s </td>\n", amount);
		printf ("<td align=\"center\"> %s </td>\n",price);
		printf ("</tr> \n");
		fgets(line, 49, fpr);
	}
	
	printf ("</body> </html>\n");
}

addNewUser(char *name, char *password, char *type)
{
	char cmd[100];
	//printf ("Content-type: text/html\n\n");
	sprintf(cmd, "./passweb -add %s %s %s", name, password, type);
	if (system(cmd)==0)
		showMessagePage("Adding New User Failed");
	else
		showMessagePage("The New User is Added Successfully");
	return 1;
}

editUser(char *user,char *password, char *type, char *newUser,char *newPassword, char *newType)
{
	char cmd[100];
	sprintf(cmd, "./passweb -edit %s %s %s %s %s %s", user, password, type,newUser,newPassword,newType);
	if (system(cmd)==0)
		showMessagePage("Editing Failed");
	else
		showMessagePage("Editing Successfully");
	return 1;
}

showMessagePage(char *message)
{
	printf ("Content-type: text/html\n\n");
	printf ("<html> <body> ");
	printf ("<h2> %s </h2>", message);
	printf ("<a href=\"http://cs.mcgill.ca/~mweiss17/login.html\"> <h2>here to go back</h2></a>");
	printf ("</body> </html>");

}




