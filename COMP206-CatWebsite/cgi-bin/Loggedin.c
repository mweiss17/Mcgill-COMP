#include <stdlib.h>
#include <stdio.h>
#include <string.h>

main ()
{
	char name[50], pwd[50];
	char fName[50],fPwd[50], fType[50];
	char line[50];
	char *p;
	char *data;
	FILE *fpr;
	char c;
	int i = 0;

	data = getenv("QUERY_STRING");
	p = strchr(data,'&');
	
	//this should copy the name and pwd from the data string
	strncpy (name, data, p-data);
	strcpy (pwd, p+5);
	


	fpr = fopen("../Members.csv","r");
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
		strcpy (fName, strtok(line,",")); 
		strcpy (fPwd, strtok(NULL,","));
		strcpy (fType, strtok(NULL,","));

//printf ("fName String=%s %s ** %s %s \n",fName, fPwd,name, pwd );
		if (!strcmp(fName,name) && !strcmp(fPwd,pwd))
		{
			showWebMenu(fType);
			return 1;
		}
		else
			fgets(line, 49, fpr);
	}
	fclose (fpr);
	showErrorPage();
	return 1;
}

showWebMenu(char *type)
{
	printf ("Content-type: text/html\n\n");
	printf ("<html> <body>\n");
	printf ("<title>Menu System</title>\n");
	printf ("<h2 align=\"center\"> Menu for System User</h2>\n");
	printf ("<form name=\"webMenu_sys\" action=\"./Menu\" method=\"get\"><br>");
	printf ("<input type=\"radio\" name=\"group1\" value=\"system_AddNewUser\"> Add New USers<br>");
	printf ("<input type=\"radio\" name=\"group1\" value=\"system_ChangeCurrentUser\"> Edit A User<br>");
	printf ("<input type=\"radio\" name=\"group1\" value=\"system_ListInventory\"> List of Inventory<br>");
	printf ("<a href = http://cs.mcgill.ca/~mweiss17/home.html> Back to home page</a><br>");
	printf ("<input type=\"submit\" name = \"Log In\">");
	printf ("</form>");
	printf ("</body>");
	printf ("</html>");
}

showErrorPage()
{
	printf ("Content-type: text/html\n\n");
	printf ("<html> <body> ");
	printf ("<h2> username and password don't match, please click</h2>");
	printf ("<a href=\"http://cs.mcgill.ca/~mweiss17/login.html\"> <h2>here to go back</h2></a>");
	printf ("</body> </html>");

}

printMessage(char *string)
{
	printf ("Content-type: text/html\n\n");
	printf ("<html> <body> ");
	printf ("%s\n",string);
	printf ("</body> </html>");
}



