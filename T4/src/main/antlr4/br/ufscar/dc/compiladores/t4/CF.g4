grammar CF;

PALAVRA_CHAVE 
    :    'PRODUTOS' | 'IMPOSTOS' | 'TAXAS_EXTRAS' | 'LOJA' | 'NOTA' | 'CPF' | 'NOME' | 'CNPJ'
    ;


CNPJ: ('0'..'9')('0'..'9') '.' ('0'..'9')('0'..'9')('0'..'9') '.' ('0'..'9')('0'..'9')('0'..'9') '/' ('0'..'9')('0'..'9')('0'..'9')('0'..'9') '-' ('0'..'9')('0'..'9')
    ;

CPF: ('0'..'9')('0'..'9')('0'..'9') '.' ('0'..'9')('0'..'9')('0'..'9') '.' ('0'..'9')('0'..'9')('0'..'9') '-' ('0'..'9')('0'..'9')
    ;


NUM_INT    : ('0'..'9')+
    ;

NUM_REAL: ('0'..'9')+ ('.' ('0'..'9')+)?
    ;

COMENTARIO: '//' ~('\n')* {skip();}
;

DELIM    :      ':' 
    ;
VIRGULA :   ','
    ;
PORCENTAGEM : '%'
    ;
BARRA: '/'
    ;
ATRIBUICAO: '='
    ;
NEGATIVO: '-'
    ;

UNIDADE_MEDIDA: 'kg' | 'un'
    ;

IDENT : ('a'..'z'|'A'..'Z') ('a'..'z'|'A'..'Z'|'0'..'9')*
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

/* ---------------------------------------------------------------------------------------*/
/* ---------------------------------------------------------------------------------------*/

programa: produtos impostos (taxas)? loja nota+ EOF;

produtos: 'PRODUTOS' DELIM precos+;

precos: nome (',' nome)* '=' (NUM_INT | NUM_REAL) BARRA UNIDADE_MEDIDA;

nome: (IDENT)+ ;

impostos: 'IMPOSTOS' DELIM porcentagens+;

porcentagens: nome (',' nome)* '=' (NEGATIVO)? (NUM_INT | NUM_REAL) PORCENTAGEM;

taxas: 'TAXAS_EXTRAS' DELIM porcentagens*;

loja: 'LOJA' DELIM 'NOME' '=' (nome) 'CNPJ' '=' CNPJ | 'LOJA' DELIM 'CNPJ' '=' CNPJ 'LOJA' DELIM 'NOME' '=' nome;

nota: 'NOTA' DELIM itens+ (infos)?;

itens : (NUM_INT | NUM_REAL) nome;

infos: 'CPF' CPF;

