###########################################################
#                                                         #
# esercizio dei blocchi con definita la stessa precedenza #
# implementata in Ordering                                #
# (lanciare con -stP deve dare lo stesso output)          #
#                                                         #
###########################################################

const: a,b,c
prec: GREEN>ON>a>b>c
weightVars: 1
weights: ON = 1; GREEN = 1; a = 1; b = 1; c = 1
sos: ~GREEN(x) | GREEN(y) | ~ON(x,y)
clauses: ON(a,b) ; ON(b,c) ; GREEN(a) ; ~GREEN(c)