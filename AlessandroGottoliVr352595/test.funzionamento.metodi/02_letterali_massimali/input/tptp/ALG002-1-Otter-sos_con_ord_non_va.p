%######################################################################
%#
%# Otter con sos da insoddisfacibile (ok)
%# Otter con sos e ordinamento non fa neanche una risoluzione
%#                 per cominciare deve dare greater_than_0(additive_inverse(X_9))
%#                 come massimale
%#
%#
%######################################################################
% con prec: greater_than_0 > product > additive_identity OK
%######################################################################

cnf(product_and_inverse,axiom,
    ( greater_than_0(X)
    | product(X,X,additive_identity)
    | greater_than_0(additive_inverse(X)) )).

