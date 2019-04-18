%{
	#include<stdio.h>
	#include "y.tab.h"
%}

ws	[ \t\n]+
noun	[a-zA-z]+
pronoun	I|he|she|it|they|we|you|this|This|We|He|She|It|They|We|You
verb	am|is|are|have|has|will|shall|eat|go|play|sing|goes|does|doing|going
adverb	very|simply|gently
conjunction	and|but|if|then|also|or
adjective	good|best|better|honest|fast
preposition	a|an|the|A|An|The
fullstop	\.
comma		,
invertedcomma	\"
questionmark	\?

%%
{ws}	{}
{pronoun}	{	printf("pronoun\n");	return PRONOUN;}
{verb}	{	printf("verb\n");		return VERB;}
{adjective}	{	printf("adjective\n");	return ADJECTIVE;}
{adverb}	{	printf("adverb\n");	return ADVERB;}
{conjunction}	{	printf("conjunction\n");	return CONJUNCTION;	}
{preposition}	{	printf("preposition\n");	return PREPOSITION;}
{fullstop}	{	printf("fullstop\n");	return FULLSTOP;}
{comma}	{	printf("comma\n");	return COMMA;}
{invertedcomma}	{	printf("invertedcomma\n");	return INVERTEDCOMMA;}
{questionmark}	{	printf("questionmark\n");	return QUESTIONMARK;}
{noun}	{	printf("noun\n");	return NOUN;}
%%
int yywrap(){
	return 1;
}
