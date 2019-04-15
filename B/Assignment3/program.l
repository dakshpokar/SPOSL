%{
	/*	Lexical Analyzer	*/
#include<stdio.h>
unsigned charcount=0;
unsigned wordcount=0, linecount=0;
%}

delim [ \n\t]
ws {delim}+

%%

\n	{	linecount++; }
{ws}	{	wordcount++; }
.	{	charcount++;	}

%%
int yywrap(){
	return 1;
}
main(){
	yyin = fopen("Sample.java", "r");
	yylex();
	printf("\nNumber of Lines in code: %d", linecount);
	printf("\nNumber of Words in code: %d", wordcount);
	printf("\nNumber of Characters in code: %d", charcount);
	return 0;
}