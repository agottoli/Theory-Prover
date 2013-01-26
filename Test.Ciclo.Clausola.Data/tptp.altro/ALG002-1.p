%--------------------------------------------------------------------------
% File     : ALG002-1 : TPTP v5.3.0. Released v1.0.0.
% Domain   : General Algebra
% Problem  : In an ordered field, if X > 0 then X^-1 > 0
% Version  : [FL+74] axioms.
% English  :

% Refs     : [FL+74] Fleisig et al. (1974), An Implementation of the Model
%          : [WM76]  Wilson & Minker (1976), Resolution, Refinements, and S
% Source   : [SPRFN]
% Names    : Example 5 [FL+74]
%          : EX5-T? [WM76]
%          : ex5.lop [SETHEO]
%          : FEX5 [SPRFN]

% Status   : Unsatisfiable
% Rating   : 0.00 v2.1.0, 0.00 v2.0.0
% Syntax   : Number of clauses     :   14 (   2 non-Horn;   4 unit;  11 RR)
%            Number of atoms       :   28 (   0 equality)
%            Maximal clause size   :    4 (   2 average)
%            Number of predicates  :    2 (   0 propositional; 1-3 arity)
%            Number of functors    :    5 (   3 constant; 0-1 arity)
%            Number of variables   :   23 (   1 singleton)
%            Maximal term depth    :    2 (   1 average)
% SPC      : CNF_UNS_RFO_NEQ_NHN

% Comments :
%--------------------------------------------------------------------------
cnf(right_identity,axiom,
    ( product(X,multiplicative_identity,X) )).

cnf(not_abelian,axiom,
    ( ~ product(multiplicative_identity,multiplicative_identity,additive_identity) )).

cnf(product_of_inverses1,axiom,
    ( ~ product(X,Y,Z)
    | product(additive_inverse(X),additive_inverse(Y),Z) )).

cnf(product_of_inverses2,axiom,
    ( product(X,Y,Z)
    | ~ product(additive_inverse(X),additive_inverse(Y),Z) )).

cnf(product_to_inverse,axiom,
    ( ~ product(X,Y,Z)
    | product(X,additive_inverse(Y),additive_inverse(Z)) )).

cnf(inverse_and_identity,axiom,
    ( product(X,multiplicative_inverse(X),multiplicative_identity)
    | product(X,X,additive_identity) )).

cnf(inverse_greater_than_0,axiom,
    ( ~ greater_than_0(X)
    | ~ greater_than_0(additive_inverse(X)) )).

cnf(greater_than_0_square,axiom,
    ( ~ greater_than_0(X)
    | ~ product(X,X,additive_identity) )).

cnf(commutativity_of_product,axiom,
    ( ~ product(X,Y,Z)
    | product(Y,X,Z) )).

cnf(product_and_inverse,axiom,
    ( greater_than_0(X)
    | product(X,X,additive_identity)
    | greater_than_0(additive_inverse(X)) )).

cnf(square_to_0,axiom,
    ( ~ product(Y,Z,X)
    | ~ product(Y,Y,additive_identity)
    | product(X,X,additive_identity) )).

cnf(product_and_greater_than_0,axiom,
    ( ~ product(Y,Z,X)
    | ~ greater_than_0(Y)
    | ~ greater_than_0(Z)
    | greater_than_0(X) )).

cnf(a_greater_than_0,hypothesis,
    ( greater_than_0(a) )).

cnf(prove_a_inverse_greater_than_0,negated_conjecture,
    ( ~ greater_than_0(multiplicative_inverse(a)) )).

%--------------------------------------------------------------------------
