%{
	#include<stdio.h>
	void yyerror(char *);
	int yylex();
%}
%token CONJUNCTION, VERB, ADVERB, ADJECTIVE, PREPOSITION, FULLSTOP, COMMA, INVERTEDCOMMA, NOUN, PRONOUN, QUESTIONMARK
%start S
%%
	S:	compound{	printf("\nThe sentence is a compound sentence.\n");} | simple	{	printf("\nThe sentence is a simple sentence.\n"); };
		
	subject:	NOUN|PRONOUN;
	object:		NOUN|ADJECTIVE NOUN|ADVERB NOUN|PREPOSITION NOUN|ADJECTIVE; 
	simple:		subject VERB object FULLSTOP;
	compound:	subject VERB object CONJUNCTION subject VERB object FULLSTOP;
%%
void yyerror(char *x){
	printf("Error: %s", x);
}
int main(){
	yyparse();
	return 0;
}
