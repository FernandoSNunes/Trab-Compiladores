lexer grammar Lexers;

PALAVRA_CHAVE 
	:	'algoritmo' | 'declare' | 'literal' | 'inteiro' | 'leia' | 'escreva' | 'fim_algoritmo' 
| 'real' | '<-' | 'logico' | 'se' | 'entao' | 'senao' | 'fim_se' | 'caso' 
| 'seja' | '..' | 'fim_caso' | 'para' | 'ate' | 'faca' | 'fim_para' | 'enquanto' | 'fim_enquanto' 
| '%' | '^' | 'registro' | 'fim_registro' | '.' | 'tipo' | 'procedimento' | 'var' | 'fim_procedimento' 
| 'funcao' | 'retorne' | 'fim_funcao' | 'constante' | 'falso' | 'verdadeiro'
    ;

NUM_INT	: ('0'..'9')+
    ;

NUM_REAL	: ('0'..'9')+ ('.' ('0'..'9')+)?
    ;

CADEIA 	: '"' ( ESC_SEQ | ~('"'|'\\'|'\n') )* '"'
    ;
fragment /*parte da cadeia, permite o uso de aspas duplas dentro de uma cadeia usando \" */
ESC_SEQ	: '\\"'
    ;   

ERRO_CADEIA : 
    '"' | '"' /*por algum motivo, se tiver apenas uma coisa o Lexers.VOCABULARY.getDisplayName(t.getType()) retorna o '{' ao inves de ERRO_CADEIA */
    ;

OP_REL	:	'>' | '>=' | '<' | '<=' | '<>' | '='
	;
OP_COMPR:  'e' | 'ou' | 'nao' | '&'
    ;
OP_ARIT :	'+' | '-' | '*' | '/'
	;
DELIM	:	':'
	;
ABREPAR :	'('
	;
FECHAPAR:	')'
	;
ABRECOL :   '['
    ;
FECHACOL:   ']'
    ;
VIRGULA :   ','
    ;

IDENT : ('a'..'z'|'A'..'Z') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*
    ;

COMENTARIO  /*Comentarios precisam iniciar com { e fechar com }. Nao pode haver uma {} antes de fechar */
    :   '{' ~('\n' | '}' | '{')* '\r'?  '}'  {skip();}
    ;

ERRO_COMENTARIO /* Caso nao feche a chave do comentario. so ira entrar nesse caso, caso nao entre no anterior entao nao e necessario checar mais nada*/
    : '{' | '{'  /*por algum motivo, se tiver apenas uma coisa o Lexers.VOCABULARY.getDisplayName(t.getType()) retorna o '{' ao inves de ERRO_COMENTARIO */
    ;

/*ignora os espacos em branco*/
WS  :   ( ' '
        | '\t'
        | '\r'
        | '\n'
        ) {skip();}
    ;

/*todo o que nao tiver classificado em um dos acima ira cair nesse caso*/
ERRO: . ;