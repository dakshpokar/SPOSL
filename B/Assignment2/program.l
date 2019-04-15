%{
	/*	Lexical Analyzer	*/
	FILE *yyin;
#include<stdio.h>
%}

delim [ \n\t]
ws {delim}+
letter [A-Za-z]
digit [0-9]
datatypes	"int"|"void"
keyword	"public"|"private"|"protected"|"static"|"class"
id {letter}({letter}|{digit})*

number {digit}+

%%
.	{}
{keyword}	{	printf("keyword - %s", yytext);}
{datatypes}	{	printf("datatypes - %s", yytext);	}
{id}	{printf("ID - %s", yytext); }

%%
int yywrap(){
	return 1;
}
main(){
	yyin = fopen("Sample.java", "r");
	yylex();
	return 0;
}