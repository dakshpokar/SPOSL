%{
	#include<stdio.h>
	#include "y.tab.h"
%}
ws		[ \t]+
digit 		[0-9]+
real 		{digit}+[.]{digit}*
identifier	[_a-zA-z]+[_a-zA-z0-9]*

%%

{ws};
\n	{	printf("New line\n");	return NL;	}
int	{	printf("Datatype INT\n");	return INT;	
}
float	{	printf("Datatype FLOAT\n");	return FLOAT;	
}
double	{	printf("Datatype DOUBLE\n");	return DOUBLE;	
}
char	{	printf("Datatype CHAR\n");	return CHAR;	
}

boolean	{	printf("Datatype BOOLEAN\n");	return BOOLEAN;	
}
";"	{	printf("SemiColon\n");		return SC;	
}
","	{	printf("Comma\n");	return COMMA;	
}
=	{	printf("Assign\n");		return AS;	
}

{digit}+	{	printf("Found digits!\n");	return NUM;	}
{real}		{	printf("Found real\n");		return REAL;	}
true|false		{	printf("Found bool\n");		return BOOL;	}
['][^\t\n][']	{	printf("Found char\n");		return CHVAL;	}
{identifier}	{	printf("Identifier %s\n", yytext);	return ID;	
}


%%
int yywrap(){
	return 1;
}

