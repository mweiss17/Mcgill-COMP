#include <stdio.h>
#include <stdlib.h>




//Confession: I still have some bugs going on with my encrypt and decrypt methods.
//wasn't able to get through all of it, but errors usually crop up when wrapping text around
//from the end of the alphabet to the beginning. I tried to make my code as clear as possible. Thanks!
//SOMETIMES IT WORKS THOUGH!!!! yea.
//The main loop, option 4, and option 1 work fine.
//the encryption and decryption are CLOSE but a little wonky.


void print();
void input();
void encrypt();
void decrypt();

char textArray[50][50];



void encrypt()
{
/*******HARDCODED ENCRYPTION KEY HERE!!!************/
        int encrypt = 1;
/***************************************************/
    
        int ASCIIwrap = (encrypt%26);
        int i = 0;

        while(i<50)
        {
                int j = 0;

                while(j<50)
                {
                        char cur = textArray[i][j];
                                                                                //capital letters
                        if(cur <= 90 && cur >= 65)
                        {
                                if((ASCIIwrap+textArray[i][j])>90)                              //if constant + letter > 90
                                {
                                        textArray[i][j] = (ASCIIwrap+textArray[i][j]-26);       //wrap around by subtracting 26
                                }
                                else if((ASCIIwrap+textArray[i][j])>=65 && (ASCIIwrap+textArray[i][j])<=90)     //if between A and Z
                                {
                                        textArray[i][j] = (ASCIIwrap+textArray[i][j]);                          //add the constant
                                }
                        }
                                                                                //lowercase letters
                        else if(cur<=122 && cur>= 97)
                        {
                                if((ASCIIwrap+textArray[i][j])>122)
                                {
                                        textArray[i][j] = (ASCIIwrap+textArray[i][j]-26);
                                }
                                else if((ASCIIwrap+textArray[i][j])>=97 && (ASCIIwrap+textArray[i][j])<=122)
                                {
                                        textArray[i][j] = (ASCIIwrap+textArray[i][j]);
                                }
                                else
                                {
                                        textArray[i][j] = (122+ASCIIwrap);
                                }
                        }
                        j++;
                }
        i++;
        }

        //grid cipher

        int x;
        int y;

        char tempArray[50][50];

        for(x=0;x<50;x++)
        {
                for(y=0;y<50;y++)
                {
                        tempArray[x][y]=textArray[x][y];
                }
        }

        char printArray[2500];

        int z=0;
        int p=0;
        for(z=0;z<50;z++)
        {
                int q=0;
                for(q=0;q<50;q++)
                {
                        printArray[p]=textArray[z][q];
                        p++;
                }
        }

        int w=0;
        for(w=0;w<2500;w++)
        {
                printf("%c", printArray[w]);
        }

        print("\n");
}


void decrypt()
{

        //transpose back
        int a;
        int b;

        char tempArray[50][50];

        for(a=0;a<50;a++)
        {
                for(b=0;b<50;b++)
                {
                        tempArray[a][b]=textArray[a][b];
                }
        }

        for(a=0;a<50;a++)
        {
                for(b=0;b<50;b++)
                {
                        textArray[a][b]=tempArray[a][b];
                }
        }

        int decrypt;

        //ceaser cipher

        printf("Please enter decryption key\n");
        scanf("%i", &decrypt);

        int ASCIIwrap = ((decrypt%26)*(-1));

        int c=0;
        while(c<50)
        {
                int d=0;
                while(d<50)
                {
                        char cur=textArray[c][d];

                        if(cur<=90 && cur>=65)
                        {
                                if((ASCIIwrap+textArray[c][d])>90)
                                {
                                        textArray[c][d] = (ASCIIwrap+textArray[c][d]-26);
                                }
                                else if((ASCIIwrap+textArray[c][d])>=65 && (ASCIIwrap+textArray[c][d])<=90)
                                {
                                        textArray[c][d] = (ASCIIwrap+textArray[c][d]);
                                }
                                else
                                {
                                        textArray[c][d] = (90+ASCIIwrap);
                                }
                        }
 		     else if(cur<=122 && cur>=97)
                        {
                                if((ASCIIwrap+textArray[c][d])>122)
                                {
                                        textArray[c][d] = (ASCIIwrap+textArray[c][d]-26);
                                }
                                else if((ASCIIwrap+textArray[c][d])>=97 && (ASCIIwrap+textArray[c][d])<=122)
                                {
                                        textArray[c][d] = (ASCIIwrap+textArray[c][d]);
                                }
                                else
                                {
                                        textArray[c][d] = (122+ASCIIwrap);
                                }
                        }
                        d++;
                }
        c++;
        }
        print();
}

/*
void input()
{
    getchar();                              //this clears the extra '1' from your selection
    int a=1;
    int i=0;
    int j, k;
    
    for(j=0;j<50;j++)
    {
        for(k=0; k<50; k++)
        {
            textArray[j][k]=' ';
        }
    }
    
    while(a==1)
    {
        printf("Input your message:\n");
        char textLine[50];
        fgets(textLine, 50, stdin);
        
        if(textLine[0]=='\0' || textLine[0]=='\n')
        {
            a=0;
            break;
        }
        
        else
        {
            int l = 0;
            
            while(textLine[l]!='\0' && textLine[l] != '\r' && textLine[l] != '\n')
            {
                textArray[i][l] = textLine[l];
                l++;
            }
            i++;
        }
    }
    print();
}

void print()
{
    int a;
    int b;
    for(a=0; a<50; a++)
    {
        for(b=0;b<50;b++)
        {
            printf("%c", textArray[a][b]);
        }
        printf("\n");
    }
}




//this part gets complicated. some of my shifting is still buggy
//I think most of the errors are in the decrypt method
//to be frank, I didn't get as much time to test it as I would have liked
//sorry I can't be of more help!!
*/
