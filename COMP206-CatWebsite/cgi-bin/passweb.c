#include <stdlib.h>
#include <string.h>
#include <stdio.h>



char userInfo[50][50]; 
FILE *pFile;
int numUser;

int main(int argc, char *argv[])
{
	if(check_switch(argc, argv, "-menu"))
	{
		//menu();
	}

	else if(check_switch(argc, argv, "-add"))
	{
		if(check_switch(argc, argv, "-edit")||check_switch(argc,argv,"-del")||check_switch(argc,argv,"-verify"))
		{
			//printf ("Can not have other switches with -add switch\n");
			exit(0);
		}
		if (argc<5)
		{
			//printf ("Not enough arguments provided\n");
			exit(0);
		}
		if (!add(argv[2], argv[3], argv[4]))
		{
			//printf("Error: Username exists\n");
			exit(0);
		}
		else
		{
			//printf("the user %s has been added\n", argv[2]);
			exit(1);
		}
	}
	else if(check_switch(argc, argv, "-edit"))
	{
		if (argc<8)
		{
			//printf ("Not enough arguments provided\n");
			exit(0);
		}
		if (!edit(argv[2],argv[3],argv[4],argv[5],argv[6],argv[7]))
		{
			//printf ("Error: no Username and password match\n");
			exit(0);
		}
		else
		{
			//printf("editing succesful\n");
			exit(1);
		}
	}
	else if(check_switch(argc, argv, "-del"))
	{
		if (argc<3)
		{
			//printf ("Not enough arguments provided\n");
			exit(0);
		}
		if (!del(argv[2]))
		{	
			//printf("Error: No such user in existence\n");
			exit(0);
		}
		else
		{
			//printf("Deletion succesful\n");
			exit(1);
		}
	}
	else if (check_switch(argc, argv, "-verify"))
	{
		if (argc<4)
		{
			//printf ("Not enough arguments provided\n");
			exit(0);
		}
		if(!verify(argv[2],argv[3]))
		{
			//printf("Error: User and password combination does not exist\n");
			return 0;
		}
		else
		{
			//printf("User is in existence\n");
			return 1;
		}
	}
	else
	{
		//printf ("the command is not correct, it should be:\n"); 
		//printf ("passweb -menu -add -del -edit -verify username password type user_2 pass_2 type_2\n");
		exit (0);
	}
}

int check_switch(int argc, char *argv[], char *command)
{
	int i;

	for(i=0; i<argc; i++)
	{
		if(!strcmp(argv[i], command))
		{
			return 1;
		}
	}
	return 0;
}

int add(char *name, char *pwd, char *type)
{
	int i;
	char currName[50];
	char currPwd[50];
	char currType[50];	

	readFileToBuffer();
printBuffer();
	// decryption();

	for(i=0; i<numUser; i++)
	{
		getNameFromString(userInfo[i],currName);
		if(!strcmp(currName,name))
		{
			return 0;
		}
	}
	sprintf (userInfo[numUser], "%s,%s,%s\n",name,pwd,type);  
 	numUser++;
	//encryption();

printBuffer();

	writeBufferToFile();
	return 1;
}

del(char *name)
{
	int i;
	char currName[50];

	readFileToBuffer();
	// decryption();

	for(i=0; i<numUser; i++)
	{
		getNameFromString(userInfo[i],currName);
		if(!strcmp(currName,name))
		{
			break;
		}
	}
	
	if(i==numUser)
	{
		return 0;
	}

	else
	{
		int j;
		for(j=i; j<numUser-1; j++)
		{
			strcpy(userInfo[j],userInfo[j+1]);
		}
		userInfo[numUser][0] = '\0';
	
		numUser--;
	}

	//encryption();

	writeBufferToFile();
	return 1;
}

edit(char *name, char *pwd, char *type, char *newName, char *newPwd, char *newType)
{
	int i;
	char currName[50];
	char currPwd[50];
	char currType[50];	

	readFileToBuffer();
	// decryption();

	for(i=0; i<numUser; i++)
	{
		getNameFromString(userInfo[i],currName);
		getPwdFromString(userInfo[i], currPwd);
		if(!strcmp(currName,name)&&!strcmp(currPwd, pwd))
		{
			break;
		}
	}
	if (i<numUser)
	{
		sprintf (userInfo[i], "%s,%s,%s\n",newName, newPwd, newType);  
		//encryption();
		writeBufferToFile();
		return 1;
	}
	else
	{
		return 0;
	}
}	

verify( char *name, char *pwd)
{
	int i;
	char currName[50];
	char currPwd[50];

//printf ("Enter Verification.....\n");

	readFileToBuffer();
	// decryption();

	for(i=0; i<numUser; i++)
	{
		getNameFromString(userInfo[i],currName);
		getPwdFromString(userInfo[i], currPwd);
		if(!strcmp(currName,name)&&!strcmp(currPwd, pwd))
		{
			break;
		}
	}
	if (i<numUser)
	{
		return 1;
	}

	return 0;
}

getNameFromString(char *string, char *name)
{
	int i;
	for (i=0; *(string+i)!=','; i++)
		*(name+i) = *(string+i);
	*(name+i)='\0';
	return 1;
}

getPwdFromString(char *string, char *pwd)
{
	int i;

	i=0;

	while(*string!=',')
		string++;
	string++;

	for(i=0; *(string+i)!=','; i++)
	{
		*(pwd+i) = *(string+i);
	}
	*(pwd+i)= '\0';
	return 1;
}

readFileToBuffer()
{
	int i;
	int j;
	
//printf ("----readFileToBuffer-------\n");

	for(i=0; i<50; i++)
		userInfo[i][0]='\0';
	numUser=0;

	pFile = fopen("../Members.csv", "r");
	
	if(pFile==NULL)
	{
		return;
	}

	
	fgets(userInfo[numUser], 49, pFile);	
	while(!feof(pFile))
	{
		numUser++;
		fgets(userInfo[numUser], 49, pFile);
		if (userInfo[numUser][0]=='\n')
			break;	
	}

	fclose(pFile);

	// Decrpt buffer
	//for (i=0; i<numUser; i++)
		//decryptString(userInfo[i]);
printBuffer();
}

writeBufferToFile()
{
	int i;

	// Encrypt Buffer
//printf ("===== enter writeBufferToFile =========\n");
printBuffer();
//	for (i=0; i<numUser; i++)
//		encryptString(userInfo[i]);

	pFile = fopen("../Members.csv", "w");
	
	for(i=0; i<numUser; i++)
	{
		fputs(strcat(userInfo[i],""), pFile);
		//fputs(strcat(userInfo[i],"\n"), pFile);
	}
	fclose(pFile);	
}

int printBuffer()
{
	int i;
	return 1;
	printf ("\n-----------ENter Print Buff.......\n");
	printf ("Number of Users = %d\n", numUser);
	for (i=0; i< numUser; i++)
		printf ("userInfo[%d]=%s\n",i, userInfo[i]);
	printf ("===========================\n");
}
