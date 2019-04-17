%{
	#include<stdio.h>
	void yyerror(char*);
	int yylex();
%}
%token	ID, INT, FLOAT, DOUBLE, CHAR, BOOLEAN, ID, SC, NUM, COMMA, NL, AS, REAL, BOOL, CHVAL

%%
	s:intType|floatType|doubleType|boolType|charType
	;
	intType:INT vlist1 SC NL	{	printf("Valid Int declaration");	return 0;}
	;
	floatType:FLOAT vlist2 SC NL	{	printf("Valid Float declaration");	return 0;}
	;
	doubleType:DOUBLE vlist3 SC NL	{	printf("Valid Double declaration");	return 0;}
	;
	boolType:BOOLEAN vlist4 SC NL	{	printf("Valid Bool declaration");	return 0;}
	;
	charType:CHAR vlist5 SC NL	{	printf("Valid Char declaration");	return 0;}
	;
	vlist1:ID | ID AS NUM | ID AS NUM COMMA vlist1 |
	; 
	vlist2:ID | ID AS REAL | ID AS REAL COMMA vlist2 |
	; 
	vlist3:ID | ID AS REAL | ID AS REAL COMMA vlist3 |
	; 
	vlist4:ID | ID AS BOOL | ID AS BOOL COMMA vlist4 |
	; 
	vlist5:ID | ID AS CHVAL | ID AS CHVAL COMMA vlist5 |
	;
	
%%
void yyerror(char *s){
	fprintf(stderr, "Error: %s\n", s);
}
int main(){
	yyparse();
	return 0;
}
