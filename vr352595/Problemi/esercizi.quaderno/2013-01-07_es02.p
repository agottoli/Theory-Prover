###################################################
#
# esercizio 02 del 07-gennaio-2013
#
###################################################

const: a
prec: P > C > S > E > V > f > a
clauses: ~E(x) | V(x) | S(x,f(x))
         ~E(x) | V(x) | C(f(x))
         P(a)
         E(a)
         ~S(a,y) | P(y)
         ~P(x) | ~V(x)
         ~P(x) | ~C(x)
