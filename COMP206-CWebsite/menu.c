/*
 ============================================================================
 Name        : menu.c
 Author      :
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */




void menu()
{
//	printf("%s", "1. Add\n");
//	printf("%s", "2. Delete\n");
//	printf("%s", "3. Edit\n");
//	printf("%s", "4. Verify\n");

	int selection;
	scanf("%d", &selection);

	if(selection == 1)
	{
		Add();
	}
	else if(selection == 2)
	{
	    Delete();
    }
	else if (selection == 3)
	 {
	    Edit();
	 }
	 else if (selection == 4)
	 {
	    Verify();
	 }
	 else
	 {
	    printf("%s", "please make your selection\n");
     }
     return;
}

void Add()
{

}

void Delete()
{

}

void Edit()
{

}

void Verify()
{

}
