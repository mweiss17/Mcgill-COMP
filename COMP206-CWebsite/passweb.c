#include <stdio.h>
#include <stdlib.h>
#include <string.h>
//#include </Users/martinweiss/Desktop/Coding/C/a3q1/src/menu.c>
//#include </Users/martinweiss/Desktop/Coding/C/a3q1/src/cipher.c>

#define EXIT_FAILURE 1
#define EXIT_SUCCESS 0

void add(char* , char*, char*);



int main(int argc, char *argv[])
{
    
    
    
    //variable to describe if we should jump to the menu
    int menuExists = 0;
    
    //the menu argument exists if the argument at 1 exists, and is equal to menu. Then we set menuExists to 1 (true);
    if(argv[1] && !strcmp(argv[1], "-menu"))
    {
        menuExists = 1;
        printf("%d", menuExists);
    }

    
    //if the parameter at 1 exists, and is -add, go to the add function
    else if(argv[1] && !strcmp(argv[1], "-add"))
    {
        
        
        char* username1;
        char* password1;
        char* type1;
       
        //parse the username
        username1 = (char*)malloc(sizeof(char) * strlen(argv[2]));
        strcpy(username1, argv[2]);
        
        
        //parse the password
        password1 = (char*)malloc(sizeof(char) * strlen(argv[3]));
        strcpy(password1, argv[3]);
        
        
        //parse the type
        type1 = (char*)malloc(sizeof(char) * strlen(argv[4]));
        strcpy(type1, argv[4]);

        
        //call the add function and pass strings
        add(username1, password1, type1);
         
        return 0;
    }
    
    //else quit in error
    else
    {
        printf("error will robinson");
        return 0;
    }
    return 0;
}


void add(char *username1, char *password1, char *type1)
{

    //declare a filepointer and point it to the password.csv file. append and read are the modes.
    FILE *file_ptr;
    file_ptr = fopen("/Users/martinweiss/Desktop/Coding/C/a3q1/src/tester.csv", "ar");

    //error checking
    if((strlen(username1) + strlen(password1) + strlen(type1)) > 50)
    {
        printf ("This is an error. We cannot allow for records of greater than 50 characters./n");
        printf ("Please shorten your username or password, mr. longname-opolis-jerk-man");
    }
       
    //if file ptr == null i.e. there is no file
    
    else if(1)
    {
        
        //This inserts a new line before appending the new username.
        putc(10, file_ptr);
        printf("%s", type1);

        
        //add the username to the file
        int i;
        for(i=0;i<strlen(username1);i++)
        {
            putc(username1[i], file_ptr);
        }
        putc(44 , file_ptr);
        
        
        //add the password to the file
        int j;
        for(j=0;j<strlen(password1);j++)
        {
            putc(password1[j], file_ptr);
        }
        putc(44, file_ptr);
        
        
        //add the type to the file

        int l;
        for(l=0;l<strlen(type1);l++)
        {
            putc(type1[l], file_ptr);
        }

        fclose(file_ptr);
    }
  
}




