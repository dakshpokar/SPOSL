%{
	/*	Lexical Analyzer	*/
	FILE *yyin;
#include<stdio.h>
%}

delim 		[ \n\t]
ws 		{delim}+
letter 		[A-Za-z]
digit 		[0-9]
datatypes	int|void
keyword		public|private|protected|static|class
id 		{letter}({letter}|{digit})*
number 		{digit}+
import		import
string		[a-zA-Z0-9\?\*!@#\$&]*
%%
.	{}
{keyword}	{	printf("(keyword - %s)", yytext);}
{datatypes}	{	printf("(datatypes - %s)", yytext);	}
{import}	{	printf("(import - %s)", yytext);	}
{id}	{printf("(ID - %s)", yytext); }
{string}	{	printf("(string - %s)", yytext);	}

%%
int yywrap(){
	return 1;
}
int main(){
	yyin = fopen("Sample.java", "r");
	yylex();
	return 0;
}
