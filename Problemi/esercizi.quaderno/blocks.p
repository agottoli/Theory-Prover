###########################################################
#                                                         #
# esercizio dei blocchi fatto in classe                   #
#                                                         #
#                                                         #
###########################################################

const: a,b,c
prec: ON>GREEN ; a>b>c
weightVars: 1
weights: ON = 1; GREEN = 1; a = 1; b = 1; c = 1
sos: ~GREEN(x) | GREEN(y) | ~ON(x,y)
clauses: ON(a,b) ; ON(b,c) ; GREEN(a) ; ~GREEN(c)
